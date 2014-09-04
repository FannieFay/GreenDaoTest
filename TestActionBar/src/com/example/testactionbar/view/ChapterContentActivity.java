package com.example.testactionbar.view;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

import com.example.testactionbar.IChapterContentView;
import com.example.testactionbar.R;
import com.example.testactionbar.adapter.ViewPageFragmentAdapter;
import com.example.testactionbar.common.IntentKey;
import com.example.testactionbar.presenter.ChapterContentPresenter;
import com.example.testactionbar.presenter.modle.Chapter;
import com.example.testactionbar.presenter.modle.StartAndEnd;
import com.example.testactionbar.util.BookPageConfiguration;
import com.example.testactionbar.util.BookPageFactory;

public class ChapterContentActivity extends FragmentActivity implements IChapterContentView,
        OnTouchListener, OnPageChangeListener
{
    ViewPager mViewPager;
    TextView mTvchapterContent;

    ArrayList<String> mList;
    // List<Fragment> mListFragment;
    View loadingView;

    ArrayList<StartAndEnd> aStartAndEnds;

    ArrayList<Chapter> chapters;
    int position;
    int pagePosition;
    int maxPageNum;
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

        mTvchapterContent = (TextView) findViewById(R.id.chapterContentTv);

        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mViewPager.setOnTouchListener(this);
        mViewPager.setOnPageChangeListener(this);
        loadingView = findViewById(R.id.loading);
        aStartAndEnds = new ArrayList<StartAndEnd>();

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
        Log.e("string", string);
        mViewPager.setCurrentItem(0);
        // mListFragment.clear();
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
            StartAndEnd startAndEnd = new StartAndEnd();
            startAndEnd.setStart(start);
            startAndEnd.setEnd(end);
            aStartAndEnds.add(startAndEnd);

            // Fragment fragment = new PageFragment();
            // Bundle bundle = new Bundle();
            // bundle.putStringArrayList(IntentKey.INTENT_LIST_KEY, mList);
            // bundle.putInt(IntentKey.INTENT_START_KEY, start);
            // bundle.putInt(IntentKey.INTENT_END_KEY, end);
            // fragment.setArguments(bundle);
            // mListFragment.add(fragment);
        }

        if (mAdapter == null)
        {
            mAdapter = new ViewPageFragmentAdapter(getSupportFragmentManager(), mList,
                    aStartAndEnds);
            mViewPager.setAdapter(mAdapter);
        }
        else
        {
            mAdapter.setList(mListFragment);
        }
        maxPageNum = mListFragment.size();

        mTvchapterContent.setText(chapters.get(position).getName() + "     " + (pagePosition + 1)
                + "/" + maxPageNum);
        mViewPager.invalidate();
    }

    @Override
    public void getChapterContentSuccess(String string)
    {
        initList(string);
        showContentView();
    }

    @Override
    public void getChapterContentFailure()
    {

    }

    float x_tmp1 = 0, y_tmp1 = 0, x_tmp2, y_tmp2 = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {

        if (pagePosition == mListFragment.size() - 1)
        {
            // 获取当前坐标
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    x_tmp1 = x;
                    y_tmp1 = y;
                    break;
                case MotionEvent.ACTION_UP:
                    x_tmp2 = x;
                    y_tmp2 = y;
                    Log.e("tag", "滑动参值 x1=" + x_tmp1 + "; x2=" + x_tmp2);
                    if (x_tmp1 != 0 && y_tmp1 != 0)
                    {
                        if (x_tmp1 - x_tmp2 > 8)
                        {

                            if (position >= 1)
                            {
                                showLoadingView();
                                position--;
                                mTvchapterContent.setText(chapters.get(this.position).getName()
                                        + "     " + (pagePosition + 1) + "/" + maxPageNum);
                                mPresenter.getChapterContent(chapters.get(position).getUrl());
                                Log.i("aaa", "向左滑动");
                            }
                        }
                        if (x_tmp2 - x_tmp1 > 8)
                        {
                            Log.i("aaa", "向右滑动");
                        }
                    }
                    break;
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
        mTvchapterContent.setText(chapters.get(this.position).getName() + "     "
                + (pagePosition + 1) + "/" + maxPageNum);
    }

}
