package com.example.testactionbar.view.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.testactionbar.common.IntentKey;

public class BaseActionBarActivity extends ActionBarActivity
{

    public String title;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public void initActionBar()
    {
        title = getIntent().getStringExtra(IntentKey.INTENT_TITLE_KEY);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
