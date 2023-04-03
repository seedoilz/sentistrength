package nju.sentistrength.project.service.impl;

import nju.sentistrength.project.core.Result;
import nju.sentistrength.project.core.ResultGenerator;
import nju.sentistrength.project.service.TextService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.wlv.sentistrength.classificationResource.ClassificationOptions;
import uk.ac.wlv.sentistrength.Corpus;
import uk.ac.wlv.sentistrength.SentiStrengthWeb;

import java.io.File;

@Service
@Transactional
public class TextServiceImpl implements TextService {

    private Corpus corpus = new Corpus();

    @Override
    public Result analyzeText(String text) {
        corpus.initialise();
        return ResultGenerator.genSuccessResult("success", SentiStrengthWeb.analyzeText(corpus, text));
//        return ResultGenerator.genSuccessResult("success", System.getProperty("user.dir"));
    }

    @Override
    public Result analyzeFile(File file) {
        corpus.initialise();
        return ResultGenerator.genSuccessResult("success", SentiStrengthWeb.analyzeFile(corpus, file));
    }

    public void parseOptionsForCorpus(String[] checkedOptions) {
        for (int i = 0; i < checkedOptions.length; ++i) {
            try {
                if (checkedOptions[i].equalsIgnoreCase("sentidata")) {
                    this.corpus.resources.sgSentiStrengthFolder = checkedOptions[i + 1];
                }

                if (checkedOptions[i].equalsIgnoreCase("emotionlookuptable")) {
                    this.corpus.resources.sgSentimentWordsFile = checkedOptions[i + 1];
                }

                if (checkedOptions[i].equalsIgnoreCase("additionalfile")) {
                    this.corpus.resources.sgAdditionalFile = checkedOptions[i + 1];
                }

                if (checkedOptions[i].equalsIgnoreCase("keywords")) {
                    this.corpus.options.parseKeywordList(checkedOptions[i + 1].toLowerCase());
                }

                if (checkedOptions[i].equalsIgnoreCase("wordsBeforeKeywords")) {
                    this.corpus.options.igWordsToIncludeBeforeKeyword = Integer.parseInt(checkedOptions[i + 1]);
                }

                if (checkedOptions[i].equalsIgnoreCase("wordsAfterKeywords")) {
                    this.corpus.options.igWordsToIncludeAfterKeyword = Integer.parseInt(checkedOptions[i + 1]);
                }

                if (checkedOptions[i].equalsIgnoreCase("sentiment")) {
                    this.corpus.options.nameProgram(false);
                }

                if (checkedOptions[i].equalsIgnoreCase("stress")) {
                    this.corpus.options.nameProgram(true);
                }

                if (checkedOptions[i].equalsIgnoreCase("trinary")) {
                    this.corpus.options.bgTrinaryMode = true;
                }

                if (checkedOptions[i].equalsIgnoreCase("binary")) {
                    this.corpus.options.bgBinaryVersionOfTrinaryMode = true;
                    this.corpus.options.bgTrinaryMode = true;
                }

                if (checkedOptions[i].equalsIgnoreCase("scale")) {
                    this.corpus.options.bgScaleMode = true;
                    if (this.corpus.options.bgTrinaryMode) {
//                        System.out.println("Must choose binary/trinary OR scale mode");
                        return;
                    }
                }

                ClassificationOptions var10000;
                if (checkedOptions[i].equalsIgnoreCase("sentenceCombineAv")) {
                    var10000 = this.corpus.options;
                    this.corpus.options.getClass();
                    var10000.igEmotionSentenceCombineMethod = 1;
                }

                if (checkedOptions[i].equalsIgnoreCase("sentenceCombineTot")) {
                    var10000 = this.corpus.options;
                    this.corpus.options.getClass();
                    var10000.igEmotionSentenceCombineMethod = 2;
                }

                if (checkedOptions[i].equalsIgnoreCase("paragraphCombineAv")) {
                    var10000 = this.corpus.options;
                    this.corpus.options.getClass();
                    var10000.igEmotionParagraphCombineMethod = 1;
                }

                if (checkedOptions[i].equalsIgnoreCase("paragraphCombineTot")) {
                    var10000 = this.corpus.options;
                    this.corpus.options.getClass();
                    var10000.igEmotionParagraphCombineMethod = 2;
                }

                if (checkedOptions[i].equalsIgnoreCase("negativeMultiplier")) {
                    this.corpus.options.fgNegativeSentimentMultiplier = Float.parseFloat(checkedOptions[i + 1]);
                }

                if (checkedOptions[i].equalsIgnoreCase("noBoosters")) {
                    this.corpus.options.bgBoosterWordsChangeEmotion = false;
                }

                if (checkedOptions[i].equalsIgnoreCase("noNegatingPositiveFlipsEmotion")) {
                    this.corpus.options.bgNegatingPositiveFlipsEmotion = false;
                }

                if (checkedOptions[i].equalsIgnoreCase("noNegatingNegativeNeutralisesEmotion")) {
                    this.corpus.options.bgNegatingNegativeNeutralisesEmotion = false;
                }

                if (checkedOptions[i].equalsIgnoreCase("noNegators")) {
                    this.corpus.options.bgNegatingWordsFlipEmotion = false;
                }

                if (checkedOptions[i].equalsIgnoreCase("noIdioms")) {
                    this.corpus.options.bgUseIdiomLookupTable = false;
                }

                if (checkedOptions[i].equalsIgnoreCase("questionsReduceNeg")) {
                    this.corpus.options.bgReduceNegativeEmotionInQuestionSentences = true;
                }

                if (checkedOptions[i].equalsIgnoreCase("noEmoticons")) {
                    this.corpus.options.bgUseEmoticons = false;
                }

                if (checkedOptions[i].equalsIgnoreCase("exclamations2")) {
                    this.corpus.options.bgExclamationInNeutralSentenceCountsAsPlus2 = true;
                }

                if (checkedOptions[i].equalsIgnoreCase("minPunctuationWithExclamation")) {
                    this.corpus.options.igMinPunctuationWithExclamationToChangeSentenceSentiment = Integer.parseInt(checkedOptions[i + 1]);
                }

                if (checkedOptions[i].equalsIgnoreCase("mood")) {
                    this.corpus.options.igMoodToInterpretNeutralEmphasis = Integer.parseInt(checkedOptions[i + 1]);
                }

                if (checkedOptions[i].equalsIgnoreCase("noMultiplePosWords")) {
                    this.corpus.options.bgAllowMultiplePositiveWordsToIncreasePositiveEmotion = false;
                }

                if (checkedOptions[i].equalsIgnoreCase("noMultipleNegWords")) {
                    this.corpus.options.bgAllowMultipleNegativeWordsToIncreaseNegativeEmotion = false;
                }

                if (checkedOptions[i].equalsIgnoreCase("noIgnoreBoosterWordsAfterNegatives")) {
                    this.corpus.options.bgIgnoreBoosterWordsAfterNegatives = false;
                }

                if (checkedOptions[i].equalsIgnoreCase("noDictionary")) {
                    this.corpus.options.bgCorrectSpellingsUsingDictionary = false;
                }

                if (checkedOptions[i].equalsIgnoreCase("noDeleteExtraDuplicateLetters")) {
                    this.corpus.options.bgCorrectExtraLetterSpellingErrors = false;
                }

                if (checkedOptions[i].equalsIgnoreCase("illegalDoubleLettersInWordMiddle")) {
                    this.corpus.options.sgIllegalDoubleLettersInWordMiddle = checkedOptions[i + 1].toLowerCase();
                }

                if (checkedOptions[i].equalsIgnoreCase("illegalDoubleLettersAtWordEnd")) {
                    this.corpus.options.sgIllegalDoubleLettersAtWordEnd = checkedOptions[i + 1].toLowerCase();
                }

                if (checkedOptions[i].equalsIgnoreCase("noMultipleLetters")) {
                    this.corpus.options.bgMultipleLettersBoostSentiment = false;
                }

                if (checkedOptions[i].equalsIgnoreCase("negatedWordStrengthMultiplier")) {
                    this.corpus.options.fgStrengthMultiplierForNegatedWords = Float.parseFloat(checkedOptions[i + 1]);
                }

                if (checkedOptions[i].equalsIgnoreCase("maxWordsBeforeSentimentToNegate")) {
                    this.corpus.options.igMaxWordsBeforeSentimentToNegate = Integer.parseInt(checkedOptions[i + 1]);
                }

                if (checkedOptions[i].equalsIgnoreCase("negatingWordsDontOccurBeforeSentiment")) {
                    this.corpus.options.bgNegatingWordsOccurBeforeSentiment = false;
                }

                if (checkedOptions[i].equalsIgnoreCase("maxWordsAfterSentimentToNegate")) {
                    this.corpus.options.igMaxWordsAfterSentimentToNegate = Integer.parseInt(checkedOptions[i + 1]);
                }

                if (checkedOptions[i].equalsIgnoreCase("negatingWordsOccurAfterSentiment")) {
                    this.corpus.options.bgNegatingWordsOccurAfterSentiment = true;
                }

                if (checkedOptions[i].equalsIgnoreCase("alwaysSplitWordsAtApostrophes")) {
                    this.corpus.options.bgAlwaysSplitWordsAtApostrophes = true;
                }

                if (checkedOptions[i].equalsIgnoreCase("capitalsBoostTermSentiment")) {
                    this.corpus.options.bgCapitalsBoostTermSentiment = true;
                }

                if (checkedOptions[i].equalsIgnoreCase("lemmaFile")) {
                    this.corpus.options.bgUseLemmatisation = true;
                    this.corpus.resources.sgLemmaFile = checkedOptions[i + 1];
                }

                if (checkedOptions[i].equalsIgnoreCase("MinSentencePosForQuotesIrony")) {
                    this.corpus.options.igMinSentencePosForQuotesIrony = Integer.parseInt(checkedOptions[i + 1]);
                }

                if (checkedOptions[i].equalsIgnoreCase("MinSentencePosForPunctuationIrony")) {
                    this.corpus.options.igMinSentencePosForPunctuationIrony = Integer.parseInt(checkedOptions[i + 1]);
                }

                if (checkedOptions[i].equalsIgnoreCase("MinSentencePosForTermsIrony")) {
                    this.corpus.options.igMinSentencePosForTermsIrony = Integer.parseInt(checkedOptions[i + 1]);
                }

                if (checkedOptions[i].equalsIgnoreCase("MinSentencePosForAllIrony")) {
                    this.corpus.options.igMinSentencePosForTermsIrony = Integer.parseInt(checkedOptions[i + 1]);
                    this.corpus.options.igMinSentencePosForPunctuationIrony = this.corpus.options.igMinSentencePosForTermsIrony;
                    this.corpus.options.igMinSentencePosForQuotesIrony = this.corpus.options.igMinSentencePosForTermsIrony;
                }

                if (checkedOptions[i].equalsIgnoreCase("explain")) {
                    this.corpus.options.bgExplainClassification = true;
                }

                if (checkedOptions[i].equalsIgnoreCase("echo")) {
                    this.corpus.options.bgEchoText = true;
                }

                if (checkedOptions[i].equalsIgnoreCase("UTF8")) {
                    this.corpus.options.bgForceUTF8 = true;
                    
                }

            } catch (NumberFormatException var5) {
//                System.out.println("Error in argument for " + checkedOptions[i] + ". Integer expected!");
                return;
            } catch (Exception var6) {
//                System.out.println("Error in argument for " + checkedOptions[i] + ". Argument missing?");
                return;
            }
        }
    }
}
