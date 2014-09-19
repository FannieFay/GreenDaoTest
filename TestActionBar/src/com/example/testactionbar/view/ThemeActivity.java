package com.example.testactionbar.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.testactionbar.R;
import com.example.testactionbar.application.Myapplication;
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
    }

    public void toTypeActivity(View v)
    {
        Intent intent = new Intent();
        intent.setClass(ThemeActivity.this, TypeActivity.class);
        startActivity(intent);
    }

    public void toRankActivity(View v)
    {
        Toast.makeText(ThemeActivity.this, "大傻蛋 排行榜还没完成，先去看书吧", Toast.LENGTH_SHORT).show();

        // Intent intent = new Intent();
        // intent.setClass(ThemeActivity.this, RankActivity.class);
        // startActivity(intent);
    }
}
