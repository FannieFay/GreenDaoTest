package com.example.testactionbar.presenter;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.content.Context;

import com.example.testactionbar.IBookListView;
import com.example.testactionbar.presenter.modle.BookInfo;

public class BookListPresenter
{
    IBookListView mView;
    Context mContext;

    public BookListPresenter(Context mContext, IBookListView mView)
    {
        this.mContext = mContext;
        this.mView = mView;
    }

    /**
     * @Title: searchBookList
     * @Description: TODO(搜索)
     * @param url
     * @throws
     */
    public void searchBookList(final String url)
    {

    }

    /**
     * @Title: searchBookList
     * @Description: TODO(主题菜单)
     * @param url
     * @throws
     */
    public void getBookListByType(final String url)
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
                    ArrayList<BookInfo> arrayList = BookInfo.getBookInfoByType(doc);

                    mView.getBookListByTypeSuccess(arrayList);
                } catch (IOException e)
                {
                    mView.getBookListByTypeFailure();
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    /**
     * @Title: searchBookList
     * @Description: TODO(排行榜)
     * @param url
     * @throws
     */
    public void getBookListByRank(final String url)
    {

    }

}
