// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst 
// Source File Name:   TextParsingOptions.java

package uk.ac.wlv.sentistrength;

/**
 * 文本转化选项类，用来存取与文本相关的选项
 * @author ruohao.zhang
 **/
public class TextParsingOptions
{

    public boolean bgIncludePunctuation;
    public int igNgramSize;
    public boolean bgUseTranslations;
    public boolean bgAddEmphasisCode;

    /**
     * TextParsingOptions（文本转化选项）的构造器
     * 分别有包括分隔，N-Gram模型中的N值，使用翻译，强调代码
     * @author ruohao.zhang
     */
    public TextParsingOptions()
    {
        bgIncludePunctuation = true;
        igNgramSize = 1;
        bgUseTranslations = true;
        bgAddEmphasisCode = false;
    }
}
