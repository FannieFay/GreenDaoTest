package com.example.testactionbar.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.testactionbar.IBookListView;
import com.example.testactionbar.R;
import com.example.testactionbar.common.IntentKey;
import com.example.testactionbar.modle.BookInfo;
import com.example.testactionbar.modle.BookInfoExpand;
import com.example.testactionbar.presenter.BookListPresenter;
import com.example.testactionbar.view.adapter.BookListAdapter;
import com.example.testactionbar.widget.PullToRefreshListViewState;
import com.example.testactionbar.widget.PullToRefreshListViewState.RefreshState;

public class BookListActivity extends Activity implements IBookListView, OnItemClickListener
{
    PullToRefreshListViewState mPullToRefreshListViewState;

    String url = null;

    BookListPresenter mPresenter;
    ListView mBookListView;
    BookListAdapter mBookListAdapter;
    int maxIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        mPresenter = new BookListPresenter(this, this);
        initView();
    }

    private void initView()
    {
        mPullToRefreshListViewState = (PullToRefreshListViewState) findViewById(R.id.booklistListView);

        mBookListView = mPullToRefreshListViewState.getListView();
        mBookListView.setOnItemClickListener(this);
        url = getIntent().getStringExtra(IntentKey.INTENT_URL_KEY);
        loadData();
    }

    void loadData()
    {
        // mPullToRefreshListViewState.showViewByRefreshState(RefreshState.LOADING);
        // mPresenter.getBookListByType(url);
        // mPullToRefreshListViewState.setStatePullToRefreshListener(new
        // StatePullToRefreshListener()
        // {
        //
        // @Override
        // public void onStatePullDownToRefresh()
        // {
        // mPresenter.getBookListByType(url);
        // }
        //
        // @Override
        // public void onStateLoadMore()
        // {
        //
        // }
        // });
    }

    Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case 1:
                    mPullToRefreshListViewState.showViewByRefreshState(RefreshState.DATA_FULL);
                    ArrayList<BookInfo> arrayList = (ArrayList<BookInfo>) msg.obj;
                    if (mBookListAdapter == null)
                    {
                        mBookListAdapter = new BookListAdapter(BookListActivity.this, arrayList);
                        mBookListView.setAdapter(mBookListAdapter);
                    }
                    else
                    {
                        mBookListAdapter.setList(arrayList);
                    }
                    break;

                case 2:
                    mPullToRefreshListViewState.showViewByRefreshState(RefreshState.DATA_FULL);
                    ArrayList<BookInfo> arrayList1 = (ArrayList<BookInfo>) msg.obj;
                    ArrayList<BookInfo> arrayList2 = mBookListAdapter.getList();
                    arrayList2.addAll(arrayList1);
                    mBookListAdapter.setList(arrayList2);
                    break;
                default:
                    break;
            }
        };
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        BookInfo bookInfo = (BookInfo) parent.getAdapter().getItem(position);
        Intent intent = new Intent();
        intent.setClass(BookListActivity.this, BookIntroduceActivity.class);
        intent.putExtra(IntentKey.INTENT_BOOKINFO_KEY, bookInfo);
        intent.putExtra(IntentKey.INTENT_URL_KEY, bookInfo.getUrl());
        startActivity(intent);
    }

    @Override
    public void getBookListSuccess(BookInfoExpand bookInfoExpand)
    {
        Message message = new Message();
        message.what = 1;
        message.obj = bookInfoExpand.getaBookInfos();
        mHandler.sendMessage(message);
        maxIndex = bookInfoExpand.getMaxIndex();
        mPullToRefreshListViewState.setCurrentPageIndex(1);
        mPullToRefreshListViewState.setMaxPageIndex(maxIndex);
    }

    @Override
    public void getBookListFailure()
    {

    }

    @Override
    public void getMoreBookListSuccess(BookInfoExpand bookInfoExpand)
    {
        mPullToRefreshListViewState.setCurrentPageIndex(mPullToRefreshListViewState
                .getCurrentPageIndex() + 1);
        Message message = new Message();
        message.what = 2;
        message.obj = bookInfoExpand.getaBookInfos();
        mHandler.sendMessage(message);
    }
}
