package com.example.testactionbar;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testactionbar.view.adapter.MyTestAdapter;

public class ImageLoaderTest extends Activity
{
    TextView mTextView;
    ListView mListView;
    MyTestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageloader_test);

        initView();
    }

    private void initView()
    {
        mListView = (ListView) findViewById(R.id.mListView);
        mAdapter = new MyTestAdapter(this, initStrings());
        mListView.setAdapter(mAdapter);
    }

    public ArrayList<String> initStrings()
    {
        ArrayList<String> arrayList = new ArrayList<String>();
        String string1 = "http://avatar.csdn.net/4/8/3/1_fengyu0506.jpg";
        String string2 = "http://avatar.csdn.net/4/8/3/1_fengyu0506.jpg";
        String string3 = "http://avatar.csdn.net/4/8/3/1_fengyu0506.jpg";
        String string4 = "http://avatar.csdn.net/4/8/3/1_fengyu0506.jpg";
        String string5 = "http://avatar.csdn.net/4/8/3/1_fengyu0506.jpg";
        String string6 = "http://avatar.csdn.net/4/8/3/1_fengyu0506.jpg";
        String string7 = "http://avatar.csdn.net/4/8/3/1_fengyu0506.jpg";
        String string8 = "http://avatar.csdn.net/4/8/3/1_fengyu0506.jpg";
        String string9 = "http://avatar.csdn.net/4/8/3/1_fengyu0506.jpg";
        for (int i = 0; i < 100; i++)
        {
            arrayList.add(string1);
        }
        arrayList.add(string2);
        arrayList.add(string3);
        arrayList.add(string4);
        arrayList.add(string5);
        arrayList.add(string6);
        arrayList.add(string7);
        arrayList.add(string8);
        arrayList.add(string9);
        return arrayList;
    }
}