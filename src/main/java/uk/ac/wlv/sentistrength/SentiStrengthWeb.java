package uk.ac.wlv.sentistrength;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SentiStrengthWeb {

    public static void main(String args[]){
        String text = "hey witch wat cha been up too";
        Corpus corpus = new Corpus();
        corpus.initialise();
        corpus.options.bgExplainClassification=false;
        corpus.options.bgEchoText=false;
        analyzeText(corpus, text);
    }
    public static String analyzeText(Corpus corpus, String text) {
        int iPos = 1;
        int iNeg = 1;
        int iTrinary = 0;
        int iScale = 0;
        Paragraph paragraph = new Paragraph();
        paragraph.setParagraph(text, corpus.resources, corpus.options);
        iNeg = paragraph.getParagraphNegativeSentiment();
        iPos = paragraph.getParagraphPositiveSentiment();
        iTrinary = paragraph.getParagraphTrinarySentiment();
        iScale = paragraph.getParagraphScaleSentiment();
        String sRationale = "";
        if (corpus.options.bgEchoText) {
            sRationale = " " + text;
        }

        if (corpus.options.bgExplainClassification) {
            sRationale = " " + paragraph.getClassificationRationale();
        }

        String sOutput = "";
        if (corpus.options.bgTrinaryMode) {
            sOutput = iPos + " " + iNeg + " " + iTrinary + sRationale;
        } else if (corpus.options.bgScaleMode) {
            sOutput = iPos + " " + iNeg + " " + iScale + sRationale;
        } else {
            sOutput = iPos + " " + iNeg + sRationale;
        }

//        if (bURLEncodedOutput) {
//            try {
//                System.out.println(URLEncoder.encode(sOutput, "UTF-8"));
//            } catch (UnsupportedEncodingException var13) {
//                var13.printStackTrace();
//            }
//        } else if (corpus.options.bgForceUTF8) {
//            try {
//                System.out.println(new String(sOutput.getBytes("UTF-8"), "UTF-8"));
//            } catch (UnsupportedEncodingException var12) {
//                System.out.println("UTF-8 Not found on your system!");
//                var12.printStackTrace();
//            }
//        } else {
//            System.out.println(sOutput);
//        }
        System.out.println(sOutput);
        return sOutput;
    }
}
