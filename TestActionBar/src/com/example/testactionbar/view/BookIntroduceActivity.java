package com.example.testactionbar.view;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.testactionbar.IBookIntroduceView;
import com.example.testactionbar.R;
import com.example.testactionbar.common.IntentKey;
import com.example.testactionbar.presenter.BookIntroducePresenter;
import com.example.testactionbar.presenter.modle.BookInfo;
import com.example.testactionbar.presenter.modle.Chapter;

public class BookIntroduceActivity extends FragmentActivity implements IBookIntroduceView
{
    private TextView mTVBookIntroduce;
    private BookIntroducePresenter mPresenter;
    private String url;
    private BookInfo bookInfo;
    private View loadingView;
    private Button btnRead, btnAddToShelf;
    private ArrayList<Chapter> arrayChapters;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_book_introduce);

        mPresenter = new BookIntroducePresenter(this, this);
        mTVBookIntroduce = (TextView) findViewById(R.id.bookIntroduceTv);
        btnRead = (Button) findViewById(R.id.bookIntroduceReadBtn);
        btnAddToShelf = (Button) findViewById(R.id.bookIntroduceAddBtn);
        loadingView = findViewById(R.id.loading);

        bookInfo = (BookInfo) getIntent().getSerializableExtra(IntentKey.INTENT_BOOKINFO_KEY);
        url = getIntent().getStringExtra(IntentKey.INTENT_URL_KEY);
        showLoadingView();
        mPresenter.getBookInfoDetail(url, bookInfo);

        btnRead.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(BookIntroduceActivity.this, BookChapterListActivity.class);
                intent.putExtra(IntentKey.INTENT_CHAPTER_LIST_KEY, arrayChapters);
                startActivity(intent);
            }
        });

        btnAddToShelf.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

    }

    @Override
    public void getBookInfoSuccess(BookInfo bookInfo)
    {
        Message message = new Message();
        message.what = 1;
        message.obj = bookInfo;
        mHandler.sendMessage(message);
    }

    @Override
    public void getBookInfoFailure()
    {

    }

    private void showLoadingView()
    {
        loadingView.setVisibility(View.VISIBLE);
        mTVBookIntroduce.setVisibility(View.GONE);
    }

    private void showContentView()
    {
        loadingView.setVisibility(View.GONE);
        mTVBookIntroduce.setVisibility(View.VISIBLE);
    }

    Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case 1:
                    BookInfo bookInfo = (BookInfo) msg.obj;
                    mTVBookIntroduce.setText("作者 : " + bookInfo.getAuthor() + "\n" + "书名 :"
                            + bookInfo.getBookName() + "\n" + bookInfo.getDetailIntroduce() + "\n"
                            + "状态 : " + bookInfo.getState() + "\n");

                    arrayChapters = bookInfo.getCharacters();
                    showContentView();
                    break;

                case 2:
                    break;
                default:
                    break;
            }
        };
    };
}
