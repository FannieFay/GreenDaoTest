package com.example.testactionbar.view;

import java.util.ArrayList;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.testactionbar.IBookIntroduceView;
import com.example.testactionbar.R;
import com.example.testactionbar.common.IntentKey;
import com.example.testactionbar.modle.BookInfo;
import com.example.testactionbar.modle.Chapter;
import com.example.testactionbar.presenter.BookIntroducePresenter;
import com.example.testactionbar.view.base.BaseActionBarActivity;
import com.example.testactionbar.widget.CustomAlertDialog;
import com.example.testactionbar.widget.CustomAlertDialog.OnCustomClickListener;
import com.example.testactionbar.widget.CustomProgressDialog;

public class BookIntroduceActivity extends BaseActionBarActivity implements IBookIntroduceView
{
    private TextView mTVBookIntroduce;
    private BookIntroducePresenter mPresenter;
    private String url;
    private BookInfo bookInfo;
    private Button btnRead, btnAddToShelf;
    private ArrayList<Chapter> arrayChapters;
    private CustomProgressDialog mProgressDialog;
    private CustomAlertDialog customAlertDialog;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_book_introduce);

        initActionBar();

        mProgressDialog = new CustomProgressDialog(this);
        mPresenter = new BookIntroducePresenter(this, this);
        mTVBookIntroduce = (TextView) findViewById(R.id.bookIntroduceTv);
        btnRead = (Button) findViewById(R.id.bookIntroduceReadBtn);
        btnAddToShelf = (Button) findViewById(R.id.bookIntroduceAddBtn);

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
                intent.putExtra(IntentKey.INTENT_TITLE_KEY, title);
                startActivity(intent);
            }
        });

        btnAddToShelf.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (customAlertDialog == null)
                {
                    customAlertDialog = new CustomAlertDialog(BookIntroduceActivity.this,
                            new CustomAlertDialog.Builder(BookIntroduceActivity.this)
                                    .setSubTitle(getResources().getString(R.string.addShelf))
                                    .setTitle(getResources().getString(R.string.collect))
                                    .setLeftButton("确认", new OnCustomClickListener()
                                    {

                                        @Override
                                        public void onClick(DialogInterface dialog)
                                        {
                                            customAlertDialog.dismiss();
                                        }
                                    }).setRightButton("取消", new OnCustomClickListener()
                                    {

                                        @Override
                                        public void onClick(DialogInterface dialog)
                                        {
                                            customAlertDialog.dismiss();
                                        }
                                    }));
                }

                customAlertDialog.show();
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
        mProgressDialog.dismiss();
    }

    private void showLoadingView()
    {
        btnRead.setVisibility(View.GONE);
        btnAddToShelf.setVisibility(View.GONE);
        mProgressDialog.show();
        mTVBookIntroduce.setVisibility(View.GONE);
    }

    private void showContentView()
    {
        btnRead.setVisibility(View.VISIBLE);
        btnAddToShelf.setVisibility(View.VISIBLE);
        mProgressDialog.dismiss();
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
                    mTVBookIntroduce.setText(bookInfo.getAuthor() + "\n\n" + "书名 : "
                            + bookInfo.getBookName() + "\n\n" + "状态 : " + bookInfo.getState()
                            + "\n\n" + bookInfo.getDetailIntroduce() + "\n\n");

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
