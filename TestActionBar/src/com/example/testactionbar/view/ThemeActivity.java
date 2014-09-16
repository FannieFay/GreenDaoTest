package com.example.testactionbar.view;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.testactionbar.R;
import com.example.testactionbar.application.Myapplication;
import com.example.testactionbar.db.Book;
import com.example.testactionbar.db.Chapter;
import com.example.testactionbar.db.DaoMaster;
import com.example.testactionbar.db.DaoSession;

public class ThemeActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        DaoMaster daoMaster = Myapplication.getDaoMaster(this);
        DaoSession daoSession = Myapplication.getDaoSession(this);

        List<Book> books1 = daoSession.getBookList();
        Book book = new Book();
        book.setBookName("我的");
        book.setAuthor("2222");
        daoSession.getBookDao().update(book);
        List<Book> books = daoSession.getBookList();

        List<Chapter> chapters1 = daoSession.getChapterList("我的");

        Chapter chapter = new Chapter();
        chapter.setBookName("wode");
    }

    public void toTypeActivity(View v)
    {
        Intent intent = new Intent();
        intent.setClass(ThemeActivity.this, TypeActivity.class);
        startActivity(intent);
    }

    public void toRankActivity(View v)
    {
        Intent intent = new Intent();
        intent.setClass(ThemeActivity.this, RankActivity.class);
        startActivity(intent);
    }
}
