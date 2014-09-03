package com.example.testactionbar.view;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.example.testactionbar.IChapterContentView;
import com.example.testactionbar.R;
import com.example.testactionbar.adapter.ViewPageFragmentAdapter;
import com.example.testactionbar.common.IntentKey;
import com.example.testactionbar.presenter.ChapterContentPresenter;
import com.example.testactionbar.presenter.modle.Chapter;
import com.example.testactionbar.util.BookPageConfiguration;
import com.example.testactionbar.util.BookPageFactory;
import com.example.testactionbar.view.fragment.PageFragment;

public class ChapterContentActivity extends FragmentActivity implements IChapterContentView,
        OnTouchListener, OnPageChangeListener
{
    ViewPager mViewPager;
    ArrayList<String> mList;
    List<Fragment> mListFragment;
    View loadingView;

    ArrayList<Chapter> chapters;
    int position;
    int pagePosition;
    ChapterContentPresenter mPresenter;
    BookPageFactory bookPageFactory;

    ViewPageFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookcontent);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;// 宽度
        int height = dm.heightPixels;// 高度
        /**
         * 初始化数据
         */
        bookPageFactory = BookPageFactory.getInstance();
        bookPageFactory.setBookPageConfiguration(new BookPageConfiguration().setHeight(height)
                .setWidth(width)
                .setLine_spacing((int) getResources().getDimension(R.dimen.line_spacing))
                .setMargin((int) getResources().getDimension(R.dimen.margin_left))
                .setMarginTop((int) getResources().getDimension(R.dimen.marginTop))
                .setSize((int) getResources().getDimension(R.dimen.word_size)));

        mPresenter = new ChapterContentPresenter(this, this);
        chapters = (ArrayList<Chapter>) getIntent().getSerializableExtra(
                IntentKey.INTENT_CHAPTER_LIST_KEY);
        position = getIntent().getIntExtra(IntentKey.INTENT_POSITION_KEY, -1);

        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mViewPager.setOnTouchListener(this);
        mViewPager.setOnPageChangeListener(this);
        loadingView = findViewById(R.id.loading);
        mListFragment = new ArrayList<Fragment>();

        showLoadingView();

        mPresenter.getChapterContent(chapters.get(position).getUrl());
    }

    private void showLoadingView()
    {
        loadingView.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.GONE);
    }

    private void showContentView()
    {
        loadingView.setVisibility(View.GONE);
        mViewPager.setVisibility(View.VISIBLE);
    }

    private void initList(String string)
    {

        mList = bookPageFactory.getArrayList(string);
        int size = mList.size() / bookPageFactory.getMaxLine();
        if (mList.size() % bookPageFactory.getMaxLine() != 0)
        {
            size++;
        }
        for (int i = 0; i < size; i++)
        {
            int start = i * bookPageFactory.getMaxLine();
            int end = start + bookPageFactory.getMaxLine() - 1;
            if (end >= mList.size() - 1)
            {
                end = mList.size() - 1;
            }
            Fragment fragment = new PageFragment();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(IntentKey.INTENT_LIST_KEY, mList);
            bundle.putInt(IntentKey.INTENT_START_KEY, start);
            bundle.putInt(IntentKey.INTENT_END_KEY, end);
            fragment.setArguments(bundle);
            mListFragment.add(fragment);
        }

        if (mAdapter == null)
        {
            mAdapter = new ViewPageFragmentAdapter(getSupportFragmentManager(), mListFragment);
            mViewPager.setAdapter(mAdapter);
        }
        else
        {
            mAdapter.setList(mListFragment);
        }

    }

    Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case 1:
                    String string = (String) msg.obj;
                    initList(string);
                    showContentView();
                    break;
                case 2:

                    break;
                default:
                    break;
            }
        };
    };

    @Override
    public void getChapterContentSuccess(String string)
    {
        Message message = new Message();
        message.what = 1;
        message.obj = string;
        mHandler.sendMessage(message);
    }

    @Override
    public void getChapterContentFailure()
    {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_MOVE)
        {
            if (pagePosition == mListFragment.size() - 1)
            {
                Log.e("move", "move");
            }
        }
        return false;
    }

    @Override
    public void onPageScrollStateChanged(int position)
    {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2)
    {

    }

    @Override
    public void onPageSelected(int position)
    {
        pagePosition = position;
    }

}
