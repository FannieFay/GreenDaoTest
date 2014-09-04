package com.example.testactionbar.presenter;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.content.Context;

import com.example.testactionbar.IBookListView;
import com.example.testactionbar.presenter.modle.BookInfo;
import com.example.testactionbar.presenter.modle.BookInfoExpand;
import com.example.testactionbar.presenter.request.HttpUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class BookListPresenter
{
    IBookListView mView;
    Context mContext;
    HttpUtils httpUtils;

    public BookListPresenter(Context mContext, IBookListView mView)
    {
        this.mContext = mContext;
        this.mView = mView;
        httpUtils = HttpUtils.getHttpUtils();
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
    public void getBookListByType(final String url, final boolean isMore)
    {
        httpUtils.get(url, new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                try
                {
                    String html = new String(responseBody, "gb2312");
                    Document doc = Jsoup.parse(html);
                    BookInfoExpand bookInfoExpand = BookInfo.getBookInfoByType(doc);
                    if (isMore)
                    {
                        mView.getMoreBookListSuccess(bookInfoExpand);
                    }
                    else
                    {
                        mView.getBookListSuccess(bookInfoExpand);
                    }
                } catch (UnsupportedEncodingException e)
                {
                    mView.getBookListFailure();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody,
                    Throwable error)
            {
                mView.getBookListFailure();
            }
        });
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
