// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst 
// Source File Name:   ClassificationStatistics.java

package uk.ac.wlv.sentistrength;

import java.io.PrintStream;

/**
 * 用于进行数据统计相关的类
 */
public class ClassificationStatistics
{

    /**
     * 构造函数
     */
    public ClassificationStatistics()
    {
    }

    /**
     * 用于计算绝对值关联性
     * @param iCorrect 每一行正确的情感标签
     * @param iPredicted 每一行的预测
     * @param iCount 行数
     * @return 返回Pearson相关系数，该值越接近1or-1则说明越近似，即预测结果越接近正确，关联性越大。
     */
    public static double correlationAbs(int iCorrect[], int iPredicted[], int iCount)
    {
        double fMeanC = 0.0D;
        double fMeanP = 0.0D;
        double fProdCP = 0.0D;
        double fSumCSq = 0.0D;
        double fSumPSq = 0.0D;
        //遍历
        for(int iRow = 1; iRow <= iCount; iRow++)
        {
            fMeanC += Math.abs(iCorrect[iRow]);
            fMeanP += Math.abs(iPredicted[iRow]);
        }

        fMeanC /= iCount;//计算平均正确率
        fMeanP /= iCount;//计算平均预测率.
        for(int iRow = 1; iRow <= iCount; iRow++)
        {
            fProdCP += ((double)Math.abs(iCorrect[iRow]) - fMeanC) * ((double)Math.abs(iPredicted[iRow]) - fMeanP);
            fSumPSq += Math.pow((double)Math.abs(iPredicted[iRow]) - fMeanP, 2D);
            fSumCSq += Math.pow((double)Math.abs(iCorrect[iRow]) - fMeanC, 2D);
        }

        return fProdCP / (Math.sqrt(fSumPSq) * Math.sqrt(fSumCSq));
    }

    /**
     * 用于计算关联性（含正负）
     * @param iCorrect 每一行正确的情感标签
     * @param iPredicted 每一行的预测
     * @param iCount 行数
     * @return 返回Pearson相关系数，该值越接近1or-1则说明越近似，即预测结果越接近正确，关联性越大。
     */
    public static double correlation(int iCorrect[], int iPredicted[], int iCount)
    {
        double fMeanC = 0.0D;
        double fMeanP = 0.0D;
        double fProdCP = 0.0D;
        double fSumCSq = 0.0D;
        double fSumPSq = 0.0D;
        for(int iRow = 1; iRow <= iCount; iRow++)
        {
            fMeanC += iCorrect[iRow];
            fMeanP += iPredicted[iRow];
        }

        fMeanC /= iCount;
        fMeanP /= iCount;
        for(int iRow = 1; iRow <= iCount; iRow++)
        {
            fProdCP += ((double)iCorrect[iRow] - fMeanC) * ((double)iPredicted[iRow] - fMeanP);//减平均数去中心化
            fSumPSq += Math.pow((double)iPredicted[iRow] - fMeanP, 2D);
            fSumCSq += Math.pow((double)iCorrect[iRow] - fMeanC, 2D);
        }
        //计算Pearson相关系数，越接近1则越相关。
        return fProdCP / (Math.sqrt(fSumPSq) * Math.sqrt(fSumCSq));
    }

    /**
     * binary包含positive和negative词汇，而trinary除此之外还包含neural类型词汇
     * @param iTrinaryEstimate 估计情绪值
     * @param iTrinaryCorrect 正确的情绪值
     * @param iDataCount 计数
     * @param estCorr
     */
    public static void TrinaryOrBinaryConfusionTable(int iTrinaryEstimate[], int iTrinaryCorrect[], int iDataCount, int estCorr[][])
    {
        for(int i = 0; i <= 2; i++)
        {
            for(int j = 0; j <= 2; j++)
                estCorr[i][j] = 0;

        }

        for(int i = 1; i <= iDataCount; i++)
            //情绪值需要为-1，0或1
            if(iTrinaryEstimate[i] > -2 && iTrinaryEstimate[i] < 2 && iTrinaryCorrect[i] > -2 && iTrinaryCorrect[i] < 2)
                estCorr[iTrinaryEstimate[i] + 1][iTrinaryCorrect[i] + 1]++;
            else
                System.out.println((new StringBuilder("Estimate or correct value ")).append(i).append(" out of range -1 to +1 (data count may be wrong): ").append(iTrinaryEstimate[i]).append(" ").append(iTrinaryCorrect[i]).toString());

    }

    /**
     * @param iCorrect 每一行正确的情感标签
     * @param iPredicted 每一行的预测
     * @param bSelected 是否选中，被选中才会参与运算
     * @param bInvert 是否是转换词（如：not）
     * @param iCount  行数
     * @return
     */
    public static double correlationAbs(int iCorrect[], int iPredicted[], boolean bSelected[], boolean bInvert, int iCount)
    {
        double fMeanC = 0.0D;
        double fMeanP = 0.0D;
        double fProdCP = 0.0D;
        double fSumCSq = 0.0D;
        double fSumPSq = 0.0D;
        int iDataCount = 0;//被选中的data数
        for(int iRow = 1; iRow <= iCount; iRow++)
            if(bSelected[iRow] && !bInvert || !bSelected[iRow] && bInvert)
            {
                fMeanC += Math.abs(iCorrect[iRow]);
                fMeanP += Math.abs(iPredicted[iRow]);
                iDataCount++;
            }

        fMeanC /= iDataCount;
        fMeanP /= iDataCount;
        for(int iRow = 1; iRow <= iCount; iRow++)
            if(bSelected[iRow] && !bInvert || !bSelected[iRow] && bInvert)
            {
                fProdCP += ((double)Math.abs(iCorrect[iRow]) - fMeanC) * ((double)Math.abs(iPredicted[iRow]) - fMeanP);
                fSumPSq += Math.pow((double)Math.abs(iPredicted[iRow]) - fMeanP, 2D);
                fSumCSq += Math.pow((double)Math.abs(iCorrect[iRow]) - fMeanC, 2D);
            }

        return fProdCP / (Math.sqrt(fSumPSq) * Math.sqrt(fSumCSq));
    }

    /**
     * @param iCorrect 经过修正后的情绪值
     * @param iPredicted 预测的情绪值（没做not转换时）
     * @param iCount 行数
     * @param bChangeSignOfOneArray 是否有转换符，如：not，有的话值的正负号需转换
     * @return 修正的情绪值行数
     */
    public static int accuracy(int iCorrect[], int iPredicted[], int iCount, boolean bChangeSignOfOneArray)
    {
        int iCorrectCount = 0;
        if(bChangeSignOfOneArray)
        {
            for(int iRow = 1; iRow <= iCount; iRow++)
                if(iCorrect[iRow] == -iPredicted[iRow])
                    iCorrectCount++;

        } else
        {
            for(int iRow = 1; iRow <= iCount; iRow++)
                if(iCorrect[iRow] == iPredicted[iRow])
                    iCorrectCount++;

        }
        return iCorrectCount;
    }

    /**
     * @param iCorrect 修正的每一行情绪值
     * @param iPredicted 原本预测的每一行的情绪值
     * @param bSelected 是否选中
     * @param bInvert 转换符（如：not），若出现则正负号转换
     * @param iCount 行数
     * @return 修正的情绪值行数
     */
    public static int accuracy(int iCorrect[], int iPredicted[], boolean bSelected[], boolean bInvert, int iCount)
    {
        int iCorrectCount = 0;
        for(int iRow = 1; iRow <= iCount; iRow++)
            if((bSelected[iRow] && !bInvert || !bSelected[iRow] && bInvert) && iCorrect[iRow] == iPredicted[iRow])
                iCorrectCount++;

        return iCorrectCount;
    }

    public static int accuracyWithin1(int iCorrect[], int iPredicted[], boolean bSelected[], boolean bInvert, int iCount)
    {
        int iCorrectCount = 0;
        for(int iRow = 1; iRow <= iCount; iRow++)
            if((bSelected[iRow] && !bInvert || !bSelected[iRow] && bInvert) && Math.abs(iCorrect[iRow] - iPredicted[iRow]) <= 1)
                iCorrectCount++;

        return iCorrectCount;
    }

    public static int accuracyWithin1(int iCorrect[], int iPredicted[], int iCount, boolean bChangeSignOfOneArray)
    {
        int iCorrectCount = 0;
        if(bChangeSignOfOneArray)
        {
            for(int iRow = 1; iRow <= iCount; iRow++)
                if(Math.abs(iCorrect[iRow] + iPredicted[iRow]) <= 1)
                    iCorrectCount++;

        } else
        {
            for(int iRow = 1; iRow <= iCount; iRow++)
                if(Math.abs(iCorrect[iRow] - iPredicted[iRow]) <= 1)
                    iCorrectCount++;

        }
        return iCorrectCount;
    }

    public static double absoluteMeanPercentageErrorNoDivision(int iCorrect[], int iPredicted[], boolean bSelected[], boolean bInvert, int iCount)
    {
        int iDataCount = 0;
        double fAMeanPE = 0.0D;
        for(int iRow = 1; iRow <= iCount; iRow++)
            if(bSelected[iRow] && !bInvert || !bSelected[iRow] && bInvert)
            {
                fAMeanPE += Math.abs(iPredicted[iRow] - iCorrect[iRow]);
                iDataCount++;
            }

        return fAMeanPE / (double)iDataCount;
    }

    public static double absoluteMeanPercentageError(int iCorrect[], int iPredicted[], boolean bSelected[], boolean bInvert, int iCount)
    {
        int iDataCount = 0;
        double fAMeanPE = 0.0D;
        for(int iRow = 1; iRow <= iCount; iRow++)
            if(bSelected[iRow] && !bInvert || !bSelected[iRow] && bInvert)
            {
                fAMeanPE += Math.abs((double)(iPredicted[iRow] - iCorrect[iRow]) / (double)iCorrect[iRow]);
                iDataCount++;
            }

        return fAMeanPE / (double)iDataCount;
    }

    public static double absoluteMeanPercentageErrorNoDivision(int iCorrect[], int iPredicted[], int iCount, boolean bChangeSignOfOneArray)
    {
        double fAMeanPE = 0.0D;
        if(bChangeSignOfOneArray)
        {
            for(int iRow = 1; iRow <= iCount; iRow++)
                fAMeanPE += Math.abs(iPredicted[iRow] + iCorrect[iRow]);

        } else
        {
            for(int iRow = 1; iRow <= iCount; iRow++)
                fAMeanPE += Math.abs(iPredicted[iRow] - iCorrect[iRow]);

        }
        return fAMeanPE / (double)iCount;
    }

    public static double baselineAccuracyMajorityClassProportion(int iCorrect[], int iCount)
    {
        if(iCount == 0)
            return 0.0D;
        int iClassCount[] = new int[100];
        int iMinClass = iCorrect[1];
        int iMaxClass = iCorrect[1];
        for(int i = 2; i <= iCount; i++)
        {
            if(iCorrect[i] < iMinClass)
                iMinClass = iCorrect[i];
            if(iCorrect[i] > iMaxClass)
                iMaxClass = iCorrect[i];
        }

        if(iMaxClass - iMinClass >= 100)
            return 0.0D;
        for(int i = 0; i <= iMaxClass - iMinClass; i++)
            iClassCount[i] = 0;

        for(int i = 1; i <= iCount; i++)
            iClassCount[iCorrect[i] - iMinClass]++;

        int iMaxClassCount = 0;
        for(int i = 0; i <= iMaxClass - iMinClass; i++)
            if(iClassCount[i] > iMaxClassCount)
                iMaxClassCount = iClassCount[i];

        return (double)iMaxClassCount / (double)iCount;
    }

    public static void baselineAccuracyMakeLargestClassPrediction(int iCorrect[], int iPredict[], int iCount, boolean bChangeSign)
    {
        if(iCount == 0)
            return;
        int iClassCount[] = new int[100];
        int iMinClass = iCorrect[1];
        int iMaxClass = iCorrect[1];
        for(int i = 2; i <= iCount; i++)
        {
            if(iCorrect[i] < iMinClass)
                iMinClass = iCorrect[i];
            if(iCorrect[i] > iMaxClass)
                iMaxClass = iCorrect[i];
        }

        if(iMaxClass - iMinClass >= 100)
            return;
        for(int i = 0; i <= iMaxClass - iMinClass; i++)
            iClassCount[i] = 0;

        for(int i = 1; i <= iCount; i++)
            iClassCount[iCorrect[i] - iMinClass]++;

        int iMaxClassCount = 0;
        int iLargestClass = 0;
        for(int i = 0; i <= iMaxClass - iMinClass; i++)
            if(iClassCount[i] > iMaxClassCount)
            {
                iMaxClassCount = iClassCount[i];
                iLargestClass = i + iMinClass;
            }

        if(bChangeSign)
        {
            for(int i = 1; i <= iCount; i++)
                iPredict[i] = -iLargestClass;

        } else
        {
            for(int i = 1; i <= iCount; i++)
                iPredict[i] = iLargestClass;

        }
    }

    public static double absoluteMeanPercentageError(int iCorrect[], int iPredicted[], int iCount, boolean bChangeSignOfOneArray)
    {
        double fAMeanPE = 0.0D;
        if(bChangeSignOfOneArray)
        {
            for(int iRow = 1; iRow <= iCount; iRow++)
                fAMeanPE += Math.abs((double)(iPredicted[iRow] + iCorrect[iRow]) / (double)iCorrect[iRow]);

        } else
        {
            for(int iRow = 1; iRow <= iCount; iRow++)
                fAMeanPE += Math.abs((double)(iPredicted[iRow] - iCorrect[iRow]) / (double)iCorrect[iRow]);

        }
        return fAMeanPE / (double)iCount;
    }
}
