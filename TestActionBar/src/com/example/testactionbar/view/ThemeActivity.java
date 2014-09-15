package com.example.testactionbar.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.testactionbar.R;

public class ThemeActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
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
