package com.example.testactionbar.view;

import android.util.Log;

import com.example.testactionbar.widget.PullToRefreshListViewState.RefreshState;
import com.example.testactionbar.widget.PullToRefreshListViewState.StatePullToRefreshListener;

public class BookListByTypeActivity extends BookListActivity
{

    String baseUrl;

    @Override
    void loadData()
    {
        String str[] = url.split("-");
        baseUrl = str[0] + "-";

        mPullToRefreshListViewState.showViewByRefreshState(RefreshState.LOADING);
        Log.e("url", url);
        mPresenter.getBookListByType(url, false);
        mPullToRefreshListViewState.setStatePullToRefreshListener(new StatePullToRefreshListener()
        {

            @Override
            public void onStatePullDownToRefresh()
            {
                url = baseUrl + 1 + ".html";
                mPresenter.getBookListByType(url, false);
                Log.e("url_re", url);
            }

            @Override
            public void onStateLoadMore()
            {
                url = baseUrl + (mPullToRefreshListViewState.getCurrentPageIndex() + 1) + ".html";
                mPresenter.getBookListByType(url, true);
                Log.e("url_more", url);
            }
        });
    }
}
