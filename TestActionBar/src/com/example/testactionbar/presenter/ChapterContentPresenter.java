package com.example.testactionbar.presenter;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.content.Context;
import android.util.Log;

import com.example.testactionbar.IChapterContentView;
import com.example.testactionbar.presenter.modle.Chapter;
import com.example.testactionbar.presenter.request.HttpUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ChapterContentPresenter
{
    IChapterContentView mView;
    Context mContext;

    HttpUtils httpUtils;

    public ChapterContentPresenter(Context mContext, IChapterContentView mView)
    {
        this.mContext = mContext;
        this.mView = mView;
        httpUtils = HttpUtils.getHttpUtils();
    }

    public void getChapterContent(final String url, final boolean isStart)
    {
        Log.e("contentUrl", url);
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
                    String string = Chapter.getChapterContent(doc);
                    mView.getChapterContentSuccess(string, isStart);
                } catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                    mView.getChapterContentFailure();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody,
                    Throwable error)
            {
                mView.getChapterContentFailure();
            }
        });
    }
}
