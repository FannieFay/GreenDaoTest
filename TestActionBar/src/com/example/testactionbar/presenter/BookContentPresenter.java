package com.example.testactionbar.presenter;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.content.Context;

import com.example.testactionbar.IBookContentView;
import com.example.testactionbar.modle.BookInfo;

public class BookContentPresenter
{
    IBookContentView mView;
    Context mContext;

    public BookContentPresenter(Context mContext, IBookContentView mView)
    {
        this.mContext = mContext;
        this.mView = mView;
    }

    public void getBookListByType(BookInfo bookInfo)
    {
        final BookInfo bookInfo2 = bookInfo;

        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Document doc = null;
                try
                {
                    doc = Jsoup.connect(bookInfo2.getUrl()).get();
                    BookInfo bookInfo3 = BookInfo.getDetailbookInfo(bookInfo2, doc);

                    mView.getIntroduceSuccess(bookInfo3);
                } catch (IOException e)
                {
                    mView.getIntroduceFailure();
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
