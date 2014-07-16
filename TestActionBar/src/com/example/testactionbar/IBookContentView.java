package com.example.testactionbar;

import com.example.testactionbar.presenter.modle.BookInfo;

public interface IBookContentView
{
    public void getIntroduceSuccess(BookInfo bookInfo);

    public void getIntroduceFailure();
}
