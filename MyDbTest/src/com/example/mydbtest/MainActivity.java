package com.example.mydbtest;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.mydbtest.db.Book;
import com.example.mydbtest.db.Chapter;
import com.example.mydbtest.db.DBHelper;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper mDbHelper = DBHelper.getInstance(this);
        Book book = new Book();
        book.setAuthor("阿三");
        book.setBookName("阿三的书");
        book.setBookUrl("www.baidu.com");
        book.setBookID("100");
        book.setIntroduce("介绍");
        book.setLastChapterName("不知道");
        book.setLastChapterUrl("www.google.com");
        book.setState("在更新");

        Chapter chapter = new Chapter("100", "aaa", "sdjkdj");

        mDbHelper.saveBook(book);
        mDbHelper.saveChapter(chapter);

        Book book2 = mDbHelper.loadBook("100");
        List<Chapter> mChapters = mDbHelper.loadAllChapter();
        Log.e("", "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
