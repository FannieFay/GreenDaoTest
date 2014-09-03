package com.example.testactionbar.presenter.modle;

import java.util.ArrayList;

public class BookInfoExpand
{
    ArrayList<BookInfo> aBookInfos;
    int maxIndex = 0;

    public ArrayList<BookInfo> getaBookInfos()
    {
        if (aBookInfos == null)
        {
            aBookInfos = new ArrayList<BookInfo>(0);
        }
        return aBookInfos;
    }

    public void setaBookInfos(ArrayList<BookInfo> aBookInfos)
    {
        this.aBookInfos = aBookInfos;
    }

    public int getMaxIndex()
    {
        return maxIndex;
    }

    public void setMaxIndex(int maxIndex)
    {
        this.maxIndex = maxIndex;
    }
}
