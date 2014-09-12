package com.example.testactionbar.view;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

import com.example.testactionbar.IChapterContentView;
import com.example.testactionbar.R;
import com.example.testactionbar.common.IntentKey;
import com.example.testactionbar.modle.Chapter;
import com.example.testactionbar.modle.StartAndEnd;
import com.example.testactionbar.presenter.ChapterContentPresenter;
import com.example.testactionbar.util.BookPageConfiguration;
import com.example.testactionbar.util.BookPageFactory;
import com.example.testactionbar.util.CalendarUtil;
import com.example.testactionbar.view.adapter.ViewPageFragmentAdapter;
import com.example.testactionbar.widget.MenuPopupWindow;
import com.example.testactionbar.widget.MenuPopupWindow.PopClickListener;

public class ChapterContentActivity extends FragmentActivity implements IChapterContentView,
        OnTouchListener, OnPageChangeListener
{
    ViewPager mViewPager;
    TextView mTvchapterContent, mTvTime;

    ArrayList<String> mList;
    View loadingView;

    ArrayList<StartAndEnd> aStartAndEnds;

    ArrayList<Chapter> chapters;
    int position;
    int pagePosition;
    int maxPageNum;
    ChapterContentPresenter mPresenter;
    BookPageFactory bookPageFactory;

    ViewPageFragmentAdapter mAdapter;

    boolean isTimer = true;

    Thread thread;

    MenuPopupWindow menuWindow;

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
        mTvTime = (TextView) findViewById(R.id.chapterContentTime);
        mViewPager.setOnTouchListener(this);
        mViewPager.setOnPageChangeListener(this);
        loadingView = findViewById(R.id.loading);
        aStartAndEnds = new ArrayList<StartAndEnd>();

        showLoadingView();

        startThread();

        mTvchapterContent.setText(chapters.get(position).getName());

        mPresenter.getChapterContent(chapters.get(position).getUrl(), true);

    }

    public void startThread()
    {
        thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (isTimer)
                {
                    try
                    {
                        Thread.sleep(1000);
                        String time = CalendarUtil
                                .getCurrentDateFormat(CalendarUtil.FORMAT_MM_DD_HH_MM);
                        Message message = new Message();
                        message.what = 1;
                        message.obj = time;
                        mHandler.sendMessage(message);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case 1:
                    String time = (String) msg.obj;
                    mTvTime.setText(time);
                    break;

                default:
                    break;
            }
        };
    };

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

    private void initList(String string, boolean isStart)
    {
        aStartAndEnds.clear();

        if (mList != null)
        {
            mList.clear();
        }
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
        }
        maxPageNum = aStartAndEnds.size();

        if (mAdapter == null)
        {
            mAdapter = new ViewPageFragmentAdapter(getSupportFragmentManager(), mList,
                    aStartAndEnds);
            mViewPager.setAdapter(mAdapter);
        }
        else
        {
            mAdapter.setList(mList, aStartAndEnds);
        }
        mTvchapterContent.setText(chapters.get(position).getName() + "     " + (pagePosition + 1)
                + "/" + maxPageNum);
        mViewPager.invalidate();

        if (!isStart)
        {
            mViewPager.setCurrentItem(maxPageNum - 1);

        }
        else
        {
            mViewPager.setCurrentItem(0);
        }
        new Handler().postDelayed(new Runnable()
        {

            @Override
            public void run()
            {
                showContentView();
            }
        }, 500);

    }

    @Override
    public void getChapterContentSuccess(String string, boolean isStart)
    {
        initList(string, isStart);
    }

    @Override
    public void getChapterContentFailure()
    {

    }

    float x_tmp1 = 0, y_tmp1 = 0, x_tmp2, y_tmp2 = 0;
    boolean isMove = false;

    @Override
    public boolean onTouch(View v, MotionEvent event)
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
                if (x_tmp1 != 0 && y_tmp1 != 0)
                {
                    Log.e("Math.abs(x_tmp1 - x_tmp2)", Math.abs(x_tmp1 - x_tmp2) + "");
                    if (Math.abs(x_tmp1 - x_tmp2) <= 5)
                    {
                        if (menuWindow == null)
                        {
                            menuWindow = new MenuPopupWindow(ChapterContentActivity.this,
                                    new PopClickListener()
                                    {
                                        @Override
                                        public void onClickListener(View view)
                                        {
                                            if (view.getId() == R.id.pop_content)
                                            {
                                                finish();
                                            }
                                        }
                                    });
                        }
                        if (!menuWindow.isShowing())
                        {
                            // 显示窗口
                            menuWindow.showAtLocation(
                                    ChapterContentActivity.this.findViewById(R.id.main),
                                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
                        }
                    }
                }
                break;
        }

        if (pagePosition == aStartAndEnds.size() - 1)
        {
            // 获取当前坐标
            float x1 = event.getX();
            float y1 = event.getY();

            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    x_tmp1 = x1;
                    y_tmp1 = y1;
                    break;
                case MotionEvent.ACTION_UP:
                    x_tmp2 = x1;
                    y_tmp2 = y1;
                    if (x_tmp1 != 0 && y_tmp1 != 0)
                    {
                        if (x_tmp1 - x_tmp2 > 30)
                        {

                            if (position >= 1)
                            {
                                showLoadingView();
                                position--;
                                mTvchapterContent.setText(chapters.get(this.position).getName());
                                mPresenter.getChapterContent(chapters.get(position).getUrl(), true);
                            }
                        }

                        if (pagePosition == 0)
                        {
                            if (x_tmp2 - x_tmp1 > 30)
                            {
                                if (position <= chapters.size() - 2)
                                {
                                    showLoadingView();
                                    position++;
                                    mTvchapterContent
                                            .setText(chapters.get(this.position).getName());
                                    mPresenter.getChapterContent(chapters.get(position).getUrl(),
                                            false);
                                }
                            }
                        }

                    }
                    break;
            }
        }
        else if (pagePosition == 0)
        {
            // 获取当前坐标
            float x1 = event.getX();
            float y1 = event.getY();

            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    x_tmp1 = x1;
                    y_tmp1 = y1;
                    break;
                case MotionEvent.ACTION_UP:
                    x_tmp2 = x1;
                    y_tmp2 = y1;
                    if (x_tmp1 != 0 && y_tmp1 != 0)
                    {
                        if (x_tmp2 - x_tmp1 > 30)
                        {
                            if (position <= chapters.size() - 2)
                            {
                                showLoadingView();
                                position++;
                                mTvchapterContent.setText(chapters.get(this.position).getName());
                                mPresenter
                                        .getChapterContent(chapters.get(position).getUrl(), false);
                            }
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

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        isTimer = false;
    }

}
