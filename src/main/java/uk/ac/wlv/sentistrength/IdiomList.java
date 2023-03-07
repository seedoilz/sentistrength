// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst 
// Source File Name:   IdiomList.java

package uk.ac.wlv.sentistrength;

import java.io.*;


import uk.ac.wlv.utilities.FileOps;

/**
 * 该类表示习语列表，提供了用于初始化、添加额外习语以及将习语字符串转换为单词列表的方法
 * UC1,UC1
 *
 */
public class IdiomList
{
    /**
     * 习语字符串数组
     */
    public String sgIdioms[];

    /**
     * 习语强度数组
     */
    public int igIdiomStrength[];

    /**
     * 列表中习语数量
     */
    public int igIdiomCount;

    /**
     * 习语二维数组
     */
    public String sgIdiomWords[][];

    /**
     * 每个习语中的单词数数组
     */
    int igIdiomWordCount[];

    /**
     * 创建一个新习语列表
     */
    public IdiomList()
    {
        igIdiomCount = 0;
    }

    /**
     * 使用输入的文件名、分类选项和额外空白数组条目数量，初始化习语列表
     * @param sFilename 习语列表文件的文件名
     * @param options 一个ClassificationOptions类型的分类选项
     * @param iExtraBlankArrayEntriesToInclude 要包括的额外空白数组条目的数量
     * @return 如果初始化成功，则为 true，否则为 false。
     */
    public boolean initialise(String sFilename, ClassificationOptions options, int iExtraBlankArrayEntriesToInclude)
    {
        int iLinesInFile = 0;
        int iIdiomStrength = 0;
        if(sFilename == "")
            return false;
        File f = new File(sFilename);
        if(!f.exists())
        {
            System.out.println((new StringBuilder("Could not find idiom list file: ")).append(sFilename).toString());
            return false;
        }
        iLinesInFile = FileOps.i_CountLinesInTextFile(sFilename);
        sgIdioms = new String[iLinesInFile + 2 + iExtraBlankArrayEntriesToInclude];
        igIdiomStrength = new int[iLinesInFile + 2 + iExtraBlankArrayEntriesToInclude];
        igIdiomCount = 0;
        try
        {
            BufferedReader rReader;
            if(options.bgForceUTF8)
                rReader = new BufferedReader(new InputStreamReader(new FileInputStream(sFilename), "UTF8"));
            else
                rReader = new BufferedReader(new FileReader(sFilename));
            String sLine;
            while((sLine = rReader.readLine()) != null) 
                if(sLine != "")
                {
                    int iFirstTabLocation = sLine.indexOf("\t");
                    if(iFirstTabLocation >= 0)
                    {
                        int iSecondTabLocation = sLine.indexOf("\t", iFirstTabLocation + 1);
                        try
                        {
                            if(iSecondTabLocation > 0)
                                iIdiomStrength = Integer.parseInt(sLine.substring(iFirstTabLocation + 1, iSecondTabLocation).trim());
                            else
                                iIdiomStrength = Integer.parseInt(sLine.substring(iFirstTabLocation + 1).trim());
                            if(iIdiomStrength > 0)
                                iIdiomStrength--;
                            else
                            if(iIdiomStrength < 0)
                                iIdiomStrength++;
                        }
                        catch(NumberFormatException e)
                        {
                            System.out.println("Failed to identify integer weight for idiom! Ignoring idiom");
                            System.out.println((new StringBuilder("Line: ")).append(sLine).toString());
                            iIdiomStrength = 0;
                        }
                        sLine = sLine.substring(0, iFirstTabLocation);
                        if(sLine.indexOf(" ") >= 0)
                            sLine = sLine.trim();
                        if(sLine.indexOf("  ") > 0)
                            sLine = sLine.replace("  ", " ");
                        if(sLine.indexOf("  ") > 0)
                            sLine = sLine.replace("  ", " ");
                        if(sLine != "")
                        {
                            igIdiomCount++;
                            sgIdioms[igIdiomCount] = sLine;
                            igIdiomStrength[igIdiomCount] = iIdiomStrength;
                        }
                    }
                }
            rReader.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println((new StringBuilder("Could not find idiom list file: ")).append(sFilename).toString());
            e.printStackTrace();
            return false;
        }
        catch(IOException e)
        {
            System.out.println((new StringBuilder("Found idiom list file but could not read from it: ")).append(sFilename).toString());
            e.printStackTrace();
            return false;
        }
        convertIdiomStringsToWordLists();
        return true;
    }

    /**
     * 在习语列表中添加一个额外的习语
     * @param sIdiom 要添加的习语
     * @param iIdiomStrength 习语的强度（有正负）
     * @param bConvertIdiomStringsToWordListsAfterAddingIdiom 添加习语后是否将习语字符串转换为单词列表
     * @return 如果习语添加成功，则为 true，否则为 false。
     */
    public boolean addExtraIdiom(String sIdiom, int iIdiomStrength, boolean bConvertIdiomStringsToWordListsAfterAddingIdiom)
    {
        try
        {
            igIdiomCount++;
            sgIdioms[igIdiomCount] = sIdiom;
            if(iIdiomStrength > 0)
                iIdiomStrength--;
            else
            if(iIdiomStrength < 0)
                iIdiomStrength++;
            igIdiomStrength[igIdiomCount] = iIdiomStrength;
            if(bConvertIdiomStringsToWordListsAfterAddingIdiom)
                convertIdiomStringsToWordLists();
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder("Could not add extra idiom: ")).append(sIdiom).toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 将习语列表中的习语字符串转换为单词列表
     */
    public void convertIdiomStringsToWordLists()
    {
        sgIdiomWords = new String[igIdiomCount + 1][10];
        igIdiomWordCount = new int[igIdiomCount + 1];
        for(int iIdiom = 1; iIdiom <= igIdiomCount; iIdiom++)
        {
            String sWordList[] = sgIdioms[iIdiom].split(" ");
            if(sWordList.length >= 9)
            {
                System.out.println((new StringBuilder("Ignoring idiom! Too many words in it! (>9): ")).append(sgIdioms[iIdiom]).toString());
            } else
            {
                igIdiomWordCount[iIdiom] = sWordList.length;
                for(int iTerm = 0; iTerm < sWordList.length; iTerm++)
                    sgIdiomWords[iIdiom][iTerm] = sWordList[iTerm];

            }
        }

    }

    /**
     * @deprecated 输入需要获取强度的习语，返回其强度。此方法不使用，并标记为旧且无用
     * @param sPhrase 需要获取强度的习语
     */
    public int getIdiomStrength_oldNotUseful(String sPhrase)
    {
        sPhrase = sPhrase.toLowerCase();
        for(int i = 1; i <= igIdiomCount; i++)
            if(sPhrase.indexOf(sgIdioms[i]) >= 0)
                return igIdiomStrength[i];

        return 999;
    }

    /**
     * 通过输入习语id标识获取习语
     * @param iIdiomID 习语id标识
     */
    public String getIdiom(int iIdiomID)
    {
        if(iIdiomID > 0 && iIdiomID < igIdiomCount)
            return sgIdioms[iIdiomID];
        else
            return "";
    }
}
