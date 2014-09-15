package com.example.testactionbar.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.testactionbar.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class PullToRefreshListViewState extends RelativeLayout implements
        OnRefreshListener2<ListView>
{
    Context context;
    private boolean isRefresh;
    private int currentPageIndex;
    private int maxPageIndex;

    PullToRefreshListView mPullToRefreshListView;
    ListView mBookListView;
    View baseView, emptyView;
    Button btnReLoad;
    Handler mHandler = new Handler();

    private StatePullToRefreshListener statePullToRefreshListener;

    public void setStatePullToRefreshListener(StatePullToRefreshListener statePullToRefreshListener)
    {
        this.statePullToRefreshListener = statePullToRefreshListener;
    }

    public enum RefreshState
    {
        LOADING, NO_DATA, DATA_FULL;
    }

    public interface StatePullToRefreshListener
    {
        /**
         * 下拉刷新回调
         * 
         * @param refreshView
         */
        public void onStatePullDownToRefresh();

        /**
         * 加载更多回调
         */
        public void onStateLoadMore();
    }

    public PullToRefreshListViewState(Context context)
    {
        super(context);
        this.context = context;
        initView();
    }

    public PullToRefreshListViewState(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView()
    {
        baseView = LayoutInflater.from(context).inflate(R.layout.layoyut_pulltorefreshlist_state,
                this);
        mPullToRefreshListView = (PullToRefreshListView) baseView.findViewById(R.id.list);

        emptyView = baseView.findViewById(R.id.emptyView);
        btnReLoad = (Button) baseView.findViewById(R.id.reLoad);
        btnReLoad.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showViewByRefreshState(RefreshState.LOADING);
                statePullToRefreshListener.onStatePullDownToRefresh();
            }
        });
        mPullToRefreshListView.setMode(Mode.BOTH);
        mBookListView = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setOnRefreshListener(this);
    }

    public ListView getListView()
    {
        return mBookListView;
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
    {
        if (!isRefresh)
        {
            if (emptyView.getVisibility() == View.VISIBLE)
            {
                showViewByRefreshState(RefreshState.LOADING);
                statePullToRefreshListener.onStatePullDownToRefresh();
            }
            else
            {
                statePullToRefreshListener.onStatePullDownToRefresh();
            }
        }
        else
        {
            Delay();
        }
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
    {
        if (!isRefresh)
        {
            if (currentPageIndex >= maxPageIndex)
            {
                Delay();
                return;
            }
            statePullToRefreshListener.onStateLoadMore();
            isRefresh = true;
        }
        else
        {
            Delay();
        }
    }

    public void showViewByRefreshState(RefreshState refreshState)
    {
        if (refreshState == RefreshState.LOADING)
        {
            isRefresh = true;
            mPullToRefreshListView.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
            Delay();
        }

        if (refreshState == RefreshState.NO_DATA)
        {
            isRefresh = false;
            mPullToRefreshListView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            Delay();
        }
        if (refreshState == RefreshState.DATA_FULL)
        {
            isRefresh = false;
            mPullToRefreshListView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            Delay();
        }
    }

    void Delay()
    {
        mHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if (mPullToRefreshListView.isRefreshing())
                {
                    mPullToRefreshListView.onRefreshComplete();
                }
            }
        }, 500);
    }

    public int getCurrentPageIndex()
    {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(int currentPageIndex)
    {
        this.currentPageIndex = currentPageIndex;
    }

    public int getMaxPageIndex()
    {
        return maxPageIndex;
    }

    public void setMaxPageIndex(int maxPageIndex)
    {
        this.maxPageIndex = maxPageIndex;
    }
}
