//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package uk.ac.wlv.sentistrength;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 分类选项类，用来存取与分类相关的选项和预处理代码
 * @author ruohao.zhang
 **/
public class ClassificationOptions {
    public boolean bgTensiStrength = false;
    public String sgProgramName = "SentiStrength";
    public String sgProgramMeasuring = "sentiment";
    public String sgProgramPos = "positive sentiment";
    public String sgProgramNeg = "negative sentiment";
    public boolean bgScaleMode = false;
    public boolean bgTrinaryMode = false;
    public boolean bgBinaryVersionOfTrinaryMode = false;
    public int igDefaultBinaryClassification = 1;
    public int igEmotionParagraphCombineMethod = 0;
    final int igCombineMax = 0;
    final int igCombineAverage = 1;
    final int igCombineTotal = 2;
    public int igEmotionSentenceCombineMethod = 0;
    public float fgNegativeSentimentMultiplier = 1.5F;
    public boolean bgReduceNegativeEmotionInQuestionSentences = false;
    public boolean bgMissCountsAsPlus2 = true;
    public boolean bgYouOrYourIsPlus2UnlessSentenceNegative = false;
    public boolean bgExclamationInNeutralSentenceCountsAsPlus2 = false;
    public int igMinPunctuationWithExclamationToChangeSentenceSentiment = 0;
    public boolean bgUseIdiomLookupTable = true;
    public boolean bgUseObjectEvaluationTable = false;
    public boolean bgCountNeutralEmotionsAsPositiveForEmphasis1 = true;
    public int igMoodToInterpretNeutralEmphasis = 1;
    public boolean bgAllowMultiplePositiveWordsToIncreasePositiveEmotion = true;
    public boolean bgAllowMultipleNegativeWordsToIncreaseNegativeEmotion = true;
    public boolean bgIgnoreBoosterWordsAfterNegatives = true;
    public boolean bgCorrectSpellingsUsingDictionary = true;
    public boolean bgCorrectExtraLetterSpellingErrors = true;
    public String sgIllegalDoubleLettersInWordMiddle = "ahijkquvxyz";
    public String sgIllegalDoubleLettersAtWordEnd = "achijkmnpqruvwxyz";
    public boolean bgMultipleLettersBoostSentiment = true;
    public boolean bgBoosterWordsChangeEmotion = true;
    public boolean bgAlwaysSplitWordsAtApostrophes = false;
    public boolean bgNegatingWordsOccurBeforeSentiment = true;
    public int igMaxWordsBeforeSentimentToNegate = 0;
    public boolean bgNegatingWordsOccurAfterSentiment = false;
    public int igMaxWordsAfterSentimentToNegate = 0;
    public boolean bgNegatingPositiveFlipsEmotion = true;
    public boolean bgNegatingNegativeNeutralisesEmotion = true;
    public boolean bgNegatingWordsFlipEmotion = false;
    public float fgStrengthMultiplierForNegatedWords = 0.5F;
    public boolean bgCorrectSpellingsWithRepeatedLetter = true;
    public boolean bgUseEmoticons = true;
    public boolean bgCapitalsBoostTermSentiment = false;
    public int igMinRepeatedLettersForBoost = 2;
    public String[] sgSentimentKeyWords = null;
    public boolean bgIgnoreSentencesWithoutKeywords = false;
    public int igWordsToIncludeBeforeKeyword = 4;
    public int igWordsToIncludeAfterKeyword = 4;
    public boolean bgExplainClassification = false;
    public boolean bgEchoText = false;
    public boolean bgForceUTF8 = false;
    public boolean bgUseLemmatisation = false;
    public int igMinSentencePosForQuotesIrony = 10;
    public int igMinSentencePosForPunctuationIrony = 10;
    public int igMinSentencePosForTermsIrony = 10;
    
    /**
     * 构造函数
     * @author ruohao.zhang
     */
    public ClassificationOptions() {
    }

    /**
     * 传递keywordlist（要求格式为用","隔开），随后的处理中要忽略没有关键词的句子
     * @param sKeywordList 由keyword和","组成的字符串
     * @author ruohao.zhang
     */
    public void parseKeywordList(String sKeywordList) {
        this.sgSentimentKeyWords = sKeywordList.split(",");
        this.bgIgnoreSentencesWithoutKeywords = true;
    }

    /**
     * 此方法将一组分类选项写入指定的BufferedWriter对象。这些选项包括控制情绪分析算法工作原理的各种布尔值和整数值。
     * @param wWriter 用于写入分类选项信息的输出流
     * @param iMinImprovement 使用分类器对情感分类时的最小改进值
     * @param bUseTotalDifference 是否使用总差异而不是精确计数来计算情感分类结果
     * @param iMultiOptimisations 情感分类器中应用的优化级别
     * @return boolean 指示选项是否已成功写入BufferedWriter。如果有IOException，它将打印堆栈跟踪并返回false。
     * @author ruohao.zhang
     */
    public boolean printClassificationOptions(BufferedWriter wWriter, int iMinImprovement, boolean bUseTotalDifference, int iMultiOptimisations) {
        try {
            if (this.igEmotionParagraphCombineMethod == 0) {
                wWriter.write("Max");
            } else if (this.igEmotionParagraphCombineMethod == 1) {
                wWriter.write("Av");
            } else {
                wWriter.write("Tot");
            }

            if (this.igEmotionSentenceCombineMethod == 0) {
                wWriter.write("\tMax");
            } else if (this.igEmotionSentenceCombineMethod == 1) {
                wWriter.write("\tAv");
            } else {
                wWriter.write("\tTot");
            }

            if (bUseTotalDifference) {
                wWriter.write("\tTotDiff");
            } else {
                wWriter.write("\tExactCount");
            }

            wWriter.write("\t" + iMultiOptimisations + "\t" + this.bgReduceNegativeEmotionInQuestionSentences + "\t" + this.bgMissCountsAsPlus2 + "\t" + this.bgYouOrYourIsPlus2UnlessSentenceNegative + "\t" + this.bgExclamationInNeutralSentenceCountsAsPlus2 + "\t" + this.bgUseIdiomLookupTable + "\t" + this.igMoodToInterpretNeutralEmphasis + "\t" + this.bgAllowMultiplePositiveWordsToIncreasePositiveEmotion + "\t" + this.bgAllowMultipleNegativeWordsToIncreaseNegativeEmotion + "\t" + this.bgIgnoreBoosterWordsAfterNegatives + "\t" + this.bgMultipleLettersBoostSentiment + "\t" + this.bgBoosterWordsChangeEmotion + "\t" + this.bgNegatingWordsFlipEmotion + "\t" + this.bgNegatingPositiveFlipsEmotion + "\t" + this.bgNegatingNegativeNeutralisesEmotion + "\t" + this.bgCorrectSpellingsWithRepeatedLetter + "\t" + this.bgUseEmoticons + "\t" + this.bgCapitalsBoostTermSentiment + "\t" + this.igMinRepeatedLettersForBoost + "\t" + this.igMaxWordsBeforeSentimentToNegate + "\t" + iMinImprovement);
            return true;
        } catch (IOException var6) {
            var6.printStackTrace();
            return false;
        }
    }

    /**
     * 用于打印空白的分类选项，目的是在输出中保持格式的一致性
     * @param wWriter 写入文本文件的输出流
     * @return boolean 如果正常写入就返回true，有异常就返回false
     * @author ruohao.zhang
     */
    public boolean printBlankClassificationOptions(BufferedWriter wWriter) {
        try {
            wWriter.write("~");
            wWriter.write("\t~");
            wWriter.write("\tBaselineMajorityClass");
            wWriter.write("\t~\t~\t~\t~\t~\t~\t~\t~\t~\t~\t~\t~\t~\t~\t~\t~\t~\t~\t~\t~\t~");
            return true;
        } catch (IOException var3) {
            var3.printStackTrace();
            return false;
        }
    }

    /**
     * 为打印的参数编写标题或标签
     * @param wWriter 写入文本文件的输出流
     * @return boolean 如果正常写入就返回true，有异常就返回false
     * @author ruohao.zhang
     */
    public boolean printClassificationOptionsHeadings(BufferedWriter wWriter) {
        try {
            wWriter.write("EmotionParagraphCombineMethod\tEmotionSentenceCombineMethod\tDifferenceCalculationMethodForTermWeightAdjustments\tMultiOptimisations\tReduceNegativeEmotionInQuestionSentences\tMissCountsAsPlus2\tYouOrYourIsPlus2UnlessSentenceNegative\tExclamationCountsAsPlus2\tUseIdiomLookupTable\tMoodToInterpretNeutralEmphasis\tAllowMultiplePositiveWordsToIncreasePositiveEmotion\tAllowMultipleNegativeWordsToIncreaseNegativeEmotion\tIgnoreBoosterWordsAfterNegatives\tMultipleLettersBoostSentiment\tBoosterWordsChangeEmotion\tNegatingWordsFlipEmotion\tNegatingPositiveFlipsEmotion\tNegatingNegativeNeutralisesEmotion\tCorrectSpellingsWithRepeatedLetter\tUseEmoticons\tCapitalsBoostTermSentiment\tMinRepeatedLettersForBoost\tWordsBeforeSentimentToNegate\tMinImprovement");
            return true;
        } catch (IOException var3) {
            var3.printStackTrace();
            return false;
        }
    }

    /**
     * 读取文件的内容，解析文件中指定的选项，并在对象（即自己）中设置相应的变量（成员）
     * @param sFilename 要读取的文件名
     * @return boolean 遇到它无法识别的选项名称，它将返回false，表明解析文件时出错。否则，该方法返回true
     * @author ruohao.zhang
     */
    public boolean setClassificationOptions(String sFilename) {
        try {
            BufferedReader rReader = new BufferedReader(new FileReader(sFilename));

            while(rReader.ready()) {
                String sLine = rReader.readLine();
                int iTabPos = sLine.indexOf("\t");
                if (iTabPos > 0) {
                    String[] sData = sLine.split("\t");
                    if (sData[0] == "EmotionParagraphCombineMethod") {
                        if (sData[1].indexOf("Max") >= 0) {
                            this.igEmotionParagraphCombineMethod = 0;
                        }

                        if (sData[1].indexOf("Av") >= 0) {
                            this.igEmotionParagraphCombineMethod = 1;
                        }

                        if (sData[1].indexOf("Tot") >= 0) {
                            this.igEmotionParagraphCombineMethod = 2;
                        }
                    } else if (sData[0] == "EmotionSentenceCombineMethod") {
                        if (sData[1].indexOf("Max") >= 0) {
                            this.igEmotionSentenceCombineMethod = 0;
                        }

                        if (sData[1].indexOf("Av") >= 0) {
                            this.igEmotionSentenceCombineMethod = 1;
                        }

                        if (sData[1].indexOf("Tot") >= 0) {
                            this.igEmotionSentenceCombineMethod = 2;
                        }
                    } else if (sData[0] == "IgnoreNegativeEmotionInQuestionSentences") {
                        this.bgReduceNegativeEmotionInQuestionSentences = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "MissCountsAsPlus2") {
                        this.bgMissCountsAsPlus2 = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "YouOrYourIsPlus2UnlessSentenceNegative") {
                        this.bgYouOrYourIsPlus2UnlessSentenceNegative = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "ExclamationCountsAsPlus2") {
                        this.bgExclamationInNeutralSentenceCountsAsPlus2 = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "UseIdiomLookupTable") {
                        this.bgUseIdiomLookupTable = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "Mood") {
                        this.igMoodToInterpretNeutralEmphasis = Integer.parseInt(sData[1]);
                    } else if (sData[0] == "AllowMultiplePositiveWordsToIncreasePositiveEmotion") {
                        this.bgAllowMultiplePositiveWordsToIncreasePositiveEmotion = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "AllowMultipleNegativeWordsToIncreaseNegativeEmotion") {
                        this.bgAllowMultipleNegativeWordsToIncreaseNegativeEmotion = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "IgnoreBoosterWordsAfterNegatives") {
                        this.bgIgnoreBoosterWordsAfterNegatives = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "MultipleLettersBoostSentiment") {
                        this.bgMultipleLettersBoostSentiment = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "BoosterWordsChangeEmotion") {
                        this.bgBoosterWordsChangeEmotion = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "NegatingWordsFlipEmotion") {
                        this.bgNegatingWordsFlipEmotion = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "NegatingWordsFlipEmotion") {
                        this.bgNegatingPositiveFlipsEmotion = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "NegatingWordsFlipEmotion") {
                        this.bgNegatingNegativeNeutralisesEmotion = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "CorrectSpellingsWithRepeatedLetter") {
                        this.bgCorrectSpellingsWithRepeatedLetter = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "UseEmoticons") {
                        this.bgUseEmoticons = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "CapitalsAreSentimentBoosters") {
                        this.bgCapitalsBoostTermSentiment = Boolean.parseBoolean(sData[1]);
                    } else if (sData[0] == "MinRepeatedLettersForBoost") {
                        this.igMinRepeatedLettersForBoost = Integer.parseInt(sData[1]);
                    } else if (sData[0] == "WordsBeforeSentimentToNegate") {
                        this.igMaxWordsBeforeSentimentToNegate = Integer.parseInt(sData[1]);
                    } else if (sData[0] == "Trinary") {
                        this.bgTrinaryMode = true;
                    } else if (sData[0] == "Binary") {
                        this.bgTrinaryMode = true;
                        this.bgBinaryVersionOfTrinaryMode = true;
                    } else {
                        if (sData[0] != "Scale") {
                            rReader.close();
                            return false;
                        }

                        this.bgScaleMode = true;
                    }
                }
            }

            rReader.close();
            return true;
        } catch (FileNotFoundException var7) {
            var7.printStackTrace();
            return false;
        } catch (IOException var8) {
            var8.printStackTrace();
            return false;
        }
    }
    
    /**
     * 根据bTensiStrength选择不同处理方法，其中一种与压力放松有关，还有一种与情绪有关
     * @param bTensiStrength 如果为true，与压力放松有关；如果为false则与情绪有关
     * @author ruohao.zhang
     */
    public void nameProgram(boolean bTensiStrength) {
        this.bgTensiStrength = bTensiStrength;
        if (bTensiStrength) {
            this.sgProgramName = "TensiStrength";
            this.sgProgramMeasuring = "stress and relaxation";
            this.sgProgramPos = "relaxation";
            this.sgProgramNeg = "stress";
        } else {
            this.sgProgramName = "SentiStrength";
            this.sgProgramMeasuring = "sentiment";
            this.sgProgramPos = "positive sentiment";
            this.sgProgramNeg = "negative sentiment";
        }

    }
}
