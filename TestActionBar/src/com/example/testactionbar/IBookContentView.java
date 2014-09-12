package com.example.testactionbar;

import com.example.testactionbar.modle.BookInfo;

public interface IBookContentView
{
    public void getIntroduceSuccess(BookInfo bookInfo);

    public void getIntroduceFailure();
}
