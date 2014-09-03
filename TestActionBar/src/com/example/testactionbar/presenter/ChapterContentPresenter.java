package com.example.testactionbar.presenter;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.content.Context;
import android.util.Log;

import com.example.testactionbar.IChapterContentView;
import com.example.testactionbar.presenter.modle.Chapter;

public class ChapterContentPresenter
{
    IChapterContentView mView;
    Context mContext;

    public ChapterContentPresenter(Context mContext, IChapterContentView mView)
    {
        this.mContext = mContext;
        this.mView = mView;
    }

    public void getChapterContent(final String url)
    {
        Thread thread = new Thread(new Runnable()
        {
            // http://www.uukanshu.com/list/tongren-1.html
            @Override
            public void run()
            {
                Document doc = null;
                try
                {
                    doc = Jsoup.connect(url).get();
                    Log.e("url", url);
                    String string = Chapter.getChapterContent(doc);
                    mView.getChapterContentSuccess(string);

                } catch (IOException e)
                {
                    mView.getChapterContentFailure();
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
