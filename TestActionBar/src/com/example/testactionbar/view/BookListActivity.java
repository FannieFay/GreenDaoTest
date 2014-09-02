package com.example.testactionbar.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.testactionbar.IBookListView;
import com.example.testactionbar.R;
import com.example.testactionbar.adapter.BookListAdapter;
import com.example.testactionbar.presenter.BookListPresenter;
import com.example.testactionbar.presenter.modle.BookInfo;
import com.example.testactionbar.widget.PullToRefreshListViewState;
import com.example.testactionbar.widget.PullToRefreshListViewState.RefreshState;
import com.example.testactionbar.widget.PullToRefreshListViewState.StatePullToRefreshListener;

public class BookListActivity extends Activity implements IBookListView, OnItemClickListener
{
    public static final String BOOKLISTURL = "BookListUrl";
    public static final String BOOKLIST_TYPE = "BookList_type";
    public static final String type_rank = "type_rank";
    public static final String type_search = "type_search";
    public static final String type_type = "type_type";

    PullToRefreshListViewState mPullToRefreshListViewState;

    String url = null;
    String type = null;

    BookListPresenter mPresenter;
    ListView mBookListView;
    BookListAdapter mBookListAdapter;

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
        url = getIntent().getStringExtra(BOOKLISTURL);
        type = getIntent().getStringExtra(BOOKLIST_TYPE);
        Log.e("BookListUrl:", url);
        if (type.equals(type_type))
        {
            mPullToRefreshListViewState.showViewByRefreshState(RefreshState.LOADING);
            mPresenter.getBookListByType(url);
        }
        else if (type.equals(type_search))
        {

        }
        else if (type.equals(type_rank))
        {
        }

        mPullToRefreshListViewState.setStatePullToRefreshListener(new StatePullToRefreshListener()
        {

            @Override
            public void onStatePullDownToRefresh()
            {
                mPresenter.getBookListByType(url);
            }

            @Override
            public void onStateLoadMore()
            {

            }
        });
    }

    @Override
    public void searchBookListSuccess()
    {

    }

    @Override
    public void searchBookListFailure()
    {

    }

    @Override
    public void getBookListByTypeSuccess(ArrayList<BookInfo> arrayList)
    {
        Message message = new Message();
        message.what = 1;
        message.obj = arrayList;
        mHandler.sendMessage(message);
    }

    @Override
    public void getBookListByTypeFailure()
    {

    }

    @Override
    public void getBookListByRankSuccess()
    {

    }

    @Override
    public void getBookListByRankFailure()
    {

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
        intent.setClass(BookListActivity.this, BookContent.class);
        startActivity(intent);
    }
}
