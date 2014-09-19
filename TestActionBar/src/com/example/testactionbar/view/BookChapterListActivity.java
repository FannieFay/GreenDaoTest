package com.example.testactionbar.view;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.testactionbar.R;
import com.example.testactionbar.common.IntentKey;
import com.example.testactionbar.modle.ChapterInfo;
import com.example.testactionbar.view.adapter.ChapterListAapter;
import com.example.testactionbar.view.base.BaseActionBarActivity;

public class BookChapterListActivity extends BaseActionBarActivity
{

    ListView listView;
    ChapterListAapter mAdapter;
    ArrayList<ChapterInfo> aChapters;
    public static int FROM_NET = 0;
    public static int FROM_DB = 1;

    @Override
    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.activity_book_chapter_list);
        initActionBar();

        aChapters = (ArrayList<ChapterInfo>) getIntent().getSerializableExtra(
                IntentKey.INTENT_CHAPTER_LIST_KEY);
        listView = (ListView) findViewById(R.id.chapter_list);

        mAdapter = new ChapterListAapter(this, aChapters);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ChapterInfo chapter = (ChapterInfo) parent.getAdapter().getItem(position);
                Intent intent = new Intent(BookChapterListActivity.this,
                        ChapterContentActivity.class);
                intent.putExtra(IntentKey.INTENT_CHAPTER_LIST_KEY, aChapters);
                intent.putExtra(IntentKey.INTENT_POSITION_KEY, position);
                startActivity(intent);
            }
        });
    }
}
