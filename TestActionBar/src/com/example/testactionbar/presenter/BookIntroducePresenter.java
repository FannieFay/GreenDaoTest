package com.example.testactionbar.presenter;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.content.Context;

import com.example.testactionbar.IBookIntroduceView;
import com.example.testactionbar.presenter.modle.BookInfo;
import com.example.testactionbar.presenter.request.HttpUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class BookIntroducePresenter
{
    IBookIntroduceView mView;
    Context mContext;
    HttpUtils httpUtils;

    public BookIntroducePresenter(Context mContext, IBookIntroduceView mView)
    {
        this.mContext = mContext;
        this.mView = mView;
        httpUtils = HttpUtils.getHttpUtils();
    }

    public void getBookInfoDetail(final String url, final BookInfo bookInfo)
    {
        httpUtils.get(url, new AsyncHttpResponseHandler()
        {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                String html;
                try
                {
                    html = new String(responseBody, "gb2312");
                    Document doc = Jsoup.parse(html);
                    BookInfo bookInfo1 = BookInfo.getDetailbookInfo(bookInfo, doc);
                    mView.getBookInfoSuccess(bookInfo1);
                } catch (UnsupportedEncodingException e)
                {
                    mView.getBookInfoFailure();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody,
                    Throwable error)
            {
                mView.getBookInfoFailure();
            }

        });
    }
}
