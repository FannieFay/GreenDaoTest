package com.example.testactionbar.presenter;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.content.Context;

import com.example.testactionbar.IBookIntroduceView;
import com.example.testactionbar.presenter.modle.BookInfo;

public class BookIntroducePresenter
{
    IBookIntroduceView mView;
    Context mContext;

    public BookIntroducePresenter(Context mContext, IBookIntroduceView mView)
    {
        this.mContext = mContext;
        this.mView = mView;
    }

    public void getBookInfoDetail(final String url, final BookInfo bookInfo)
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Document doc = null;
                try
                {
                    doc = Jsoup.connect(url).get();
                    BookInfo bookInfo1 = BookInfo.getDetailbookInfo(bookInfo, doc);
                    mView.getBookInfoSuccess(bookInfo1);

                } catch (IOException e)
                {
                    mView.getBookInfoFailure();
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
