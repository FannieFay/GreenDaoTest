package com.example.testactionbar.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.testactionbar.R;
import com.example.testactionbar.common.IntentKey;
import com.example.testactionbar.modle.BookCategory;
import com.example.testactionbar.view.adapter.TypeRankAdapter;
import com.example.testactionbar.view.base.BaseActionBarActivity;

public class TypeActivity extends BaseActionBarActivity
{
    ListView listView;
    TypeRankAdapter mAdapter;
    ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        initView();
    }

    private void initView()
    {
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(getResources().getString(R.string.bookDirectory));

        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new TypeRankAdapter(this, BookCategory.getBookTypes());
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                BookCategory bookCategory = (BookCategory) parent.getAdapter().getItem(position);
                Intent intent = new Intent();
                intent.setClass(TypeActivity.this, BookListByTypeActivity.class);
                intent.putExtra(IntentKey.INTENT_TITLE_KEY, bookCategory.getBookName());
                intent.putExtra(IntentKey.INTENT_URL_KEY, bookCategory.getBookUrl());
                startActivity(intent);
            }

        });
    }
}
