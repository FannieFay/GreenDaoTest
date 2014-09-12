package com.example.testactionbar;

import com.example.testactionbar.modle.BookInfo;

public interface IBookIntroduceView
{
    public void getBookInfoSuccess(BookInfo bookInfo);

    public void getBookInfoFailure();
}
