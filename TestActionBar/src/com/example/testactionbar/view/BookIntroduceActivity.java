package com.example.testactionbar.view;

import java.util.ArrayList;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testactionbar.IBookIntroduceView;
import com.example.testactionbar.R;
import com.example.testactionbar.application.Myapplication;
import com.example.testactionbar.common.IntentKey;
import com.example.testactionbar.db.Book;
import com.example.testactionbar.db.BookDao;
import com.example.testactionbar.db.Chapter;
import com.example.testactionbar.db.ChapterDao;
import com.example.testactionbar.db.ChapterDao.Properties;
import com.example.testactionbar.modle.BookInfo;
import com.example.testactionbar.modle.ChapterInfo;
import com.example.testactionbar.presenter.BookIntroducePresenter;
import com.example.testactionbar.view.base.BaseActionBarActivity;
import com.example.testactionbar.widget.CustomAlertDialog;
import com.example.testactionbar.widget.CustomAlertDialog.OnCustomClickListener;
import com.example.testactionbar.widget.CustomProgressDialog;

import de.greenrobot.dao.query.QueryBuilder;

public class BookIntroduceActivity extends BaseActionBarActivity implements IBookIntroduceView
{
    private TextView mTVBookIntroduce;
    private BookIntroducePresenter mPresenter;
    private String url;
    private BookInfo bookInfo;
    private Button btnRead, btnAddToShelf;
    private ArrayList<ChapterInfo> arrayChapters;
    private CustomProgressDialog mProgressDialog;
    private CustomAlertDialog customAlertDialog;

    // DB
    private BookDao bookDao;
    private ChapterDao chapterDao;
    private Book book;
    private ArrayList<Chapter> chapterList;

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

        bookDao = Myapplication.getBookDao();
        chapterDao = Myapplication.getChapterDao();
        book = bookDao.load(bookInfo.getBookName());
        if (book == null)
        {
            mPresenter.getBookInfoDetail(url, bookInfo);
        }
        else
        {
            initDBData(book);
        }

        btnRead.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setClass(BookIntroduceActivity.this, BookChapterListActivity.class);
                if (book != null)
                {
                    intent.putExtra(IntentKey.INTENT_FROM_KEY, BookChapterListActivity.FROM_DB);
                    intent.putExtra(IntentKey.INTENT_CHAPTER_LIST_KEY, chapterList);
                    intent.putExtra(IntentKey.INTENT_BOOKINFO_KEY, book);
                }
                {
                    intent.putExtra(IntentKey.INTENT_FROM_KEY, BookChapterListActivity.FROM_NET);
                    intent.putExtra(IntentKey.INTENT_BOOKINFO_KEY, bookInfo);
                }

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
                                            mProgressDialog.show();

                                            Thread thread = new Thread(new Runnable()
                                            {

                                                @Override
                                                public void run()
                                                {
                                                    Book book = BookInfo.bookInfoToBook(bookInfo,
                                                            "0", "0");
                                                    Log.e("time0", System.currentTimeMillis() + "");
                                                    bookDao.insertOrReplace(book);
                                                    Log.e("time1", System.currentTimeMillis() + "");
                                                    ArrayList<Chapter> chapterList = ChapterInfo
                                                            .chapterInfoToChapter(
                                                                    book.getBookName(),
                                                                    arrayChapters);
                                                    Log.e("time2", System.currentTimeMillis() + "");
                                                    for (int i = 0; i < chapterList.size(); i++)
                                                    {
                                                        Log.e("time3" + ":" + i,
                                                                System.currentTimeMillis() + "");
                                                        chapterDao.insertOrReplace(chapterList
                                                                .get(i));
                                                    }
                                                    Log.e("time3", System.currentTimeMillis() + "");
                                                    mHandler.sendEmptyMessage(2);
                                                }
                                            });
                                            thread.start();

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

    private void initDBData(Book book)
    {
        QueryBuilder qb = chapterDao.queryBuilder();
        qb.where(Properties.BookName.eq(book.getBookName()));
        chapterList = (ArrayList<Chapter>) qb.list();

        mTVBookIntroduce.setText(book.getAuthor() + "\n\n" + "书名 : " + book.getBookName() + "\n\n"
                + "状态 : " + book.getState() + "\n\n" + book.getIntroduce() + "\n\n");
        showContentView();
        btnAddToShelf.setVisibility(View.GONE);
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
                    Toast.makeText(BookIntroduceActivity.this, "添加完成", Toast.LENGTH_SHORT).show();
                    btnAddToShelf.setVisibility(View.GONE);
                    mProgressDialog.dismiss();
                    break;
                default:
                    break;
            }
        };
    };
}
