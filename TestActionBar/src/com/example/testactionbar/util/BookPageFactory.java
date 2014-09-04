package com.example.testactionbar.util;

import java.util.ArrayList;

import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.util.Log;

public class BookPageFactory
{
    /**
     * 字体大小
     */
    private int size;
    /**
     * 每一行的高度
     */
    private int wordPerHeight;
    /**
     * 行间距
     */
    private int line_spacing;
    /**
     * 段间距
     */
    private int paragraph_pacing;

    /**
     * 每行最大长度
     */
    private int maxLineWidth;

    /**
     * 页面的最大高度
     */
    private int maxHeight;

    /**
     * 边距 用不到
     */
    private int margin;

    /**
     * 每页的行数
     */
    private int maxLine;

    private int marginTop;

    Paint mPaint;

    public int getMaxLine()
    {
        return maxLine;
    }

    private static BookPageFactory bookPageFactory = null;

    public static BookPageFactory getInstance()
    {
        if (bookPageFactory == null)
        {
            bookPageFactory = new BookPageFactory();
        }
        return bookPageFactory;
    }

    public void setBookPageConfiguration(BookPageConfiguration bookPageConfiguration)
    {
        size = bookPageConfiguration.getSize();// 字体大小
        margin = bookPageConfiguration.getMargin();// 边距
        line_spacing = bookPageConfiguration.getLine_spacing();// 行间距
        marginTop = bookPageConfiguration.getMarginTop();// 距离顶部
        wordPerHeight = getStringHeight(size);// 每一行字体高度
        maxLineWidth = bookPageConfiguration.getWidth() - 2 * margin;// 每行最长的长度
        this.maxHeight = bookPageConfiguration.getHeight() - 2 * marginTop;
        maxLine = maxHeight / (line_spacing + wordPerHeight);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextAlign(Align.LEFT);
        mPaint.setTextSize(size);
        mPaint.setTypeface(null);
    }

    public int getMarginTop()
    {
        return marginTop;
    }

    /**
     * 4000个字大约要5秒
     * 
     * @param string
     * @return
     */
    public ArrayList<String> getArrayList(String string)
    {
        if (string == null)
        {
            return null;
        }
        String currentString = string;
        String strParagraph = "";
        ArrayList<String> mVectorStrings = new ArrayList<String>();
        boolean isHasLines = true;
        int j = 0;
        while (isHasLines)
        {
            j++;
            if (currentString.length() > 0)
            {
                int nSize = mPaint.breakText(currentString, true, getMaxLineWidth(), null);
                strParagraph = currentString.substring(0, nSize);
                int index = strParagraph.indexOf("\n");
                if (index == -1)
                {
                    currentString = currentString.substring(nSize);
                    mVectorStrings.add(strParagraph);
                }
                else
                {
                    strParagraph = currentString.substring(0, index);
                    currentString = currentString.substring(index + 1);
                    mVectorStrings.add(strParagraph);
                    mVectorStrings.add("");
                }
            }
            else
            {
                isHasLines = false;
            }
        }
        return mVectorStrings;
    }

    public ArrayList<String> getArrayList2(String string)
    {
        Log.e("string", string.length() + "");
        String currentString = string;
        String strParagraph = "";
        String[] str = string.split("\n");
        ArrayList<String> mVectorStrings = new ArrayList<String>();

        for (int i = 0; i < str.length; i++)
        {
            boolean isHasLines = true;
            while (isHasLines)
            {
                if (currentString.length() > 0)
                {
                    int nSize = mPaint.breakText(currentString, true, getMaxLineWidth(), null);
                    strParagraph = currentString.substring(0, nSize);
                    currentString = currentString.substring(nSize);
                    mVectorStrings.add(strParagraph);
                }
                else
                {
                    mVectorStrings.add("");
                    isHasLines = false;
                }
            }
        }
        return mVectorStrings;
    }

    /**
     * 获得字体的宽度
     * 
     * @param size
     * @param string
     * @return
     */
    private int getStringWidth(int size, String string)
    {
        Paint sPaint = new Paint();
        sPaint.setTextSize(size);
        return (int) sPaint.measureText(string);
    }

    /**
     * 获得字体的宽度
     * 
     * @param string
     * @return
     */
    public int getStringWidth(String string)
    {
        Paint sPaint = new Paint();
        sPaint.setTextSize(getSize());
        return (int) sPaint.measureText(string);
    }

    /**
     * 获得字体的高度
     * 
     * @param f
     * @return
     */
    private int getStringHeight(float f)
    {
        Paint sPaint = new Paint();
        sPaint.setTextSize(f);
        FontMetrics sF = sPaint.getFontMetrics();
        return (int) Math.ceil(sF.descent - sF.top) + 2;
    }

    public int getMaxHeight()
    {
        return maxHeight;
    }

    /**
     * 字体大小
     */
    public int getSize()
    {
        return size;
    }

    /**
     * 每一行高度
     */
    public int getWordPerHeight()
    {
        return wordPerHeight;
    }

    /**
     * 每一行间距
     * 
     * @return
     */
    public int getLine_spacing()
    {
        return line_spacing;
    }

    /**
     * 每一段间距
     * 
     * @return
     */
    public int getParagraph_pacing()
    {
        return paragraph_pacing;
    }

    /**
     * 最大的长度
     * 
     * @return
     */
    public int getMaxLineWidth()
    {
        return maxLineWidth;
    }

    /**
     * 边距
     * 
     * @return
     */
    public int getMargin()
    {
        return margin;
    }
}
