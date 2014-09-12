package com.example.testactionbar.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;

import com.example.testactionbar.view.adapter.MyViewPagerAdapter;

public class MyViewPage extends Activity
{
    ViewPager mViewPager;
    String content;
    MyViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_my_view_page);
        // mViewPager = (ViewPager) findViewById(R.id.mViewPage);
        // content = getResources().getString(R.string.testString);
        String str[] = getArray(content, 1000);
        mViewPagerAdapter = new MyViewPagerAdapter(this, str);

        mViewPager.setAdapter(mViewPagerAdapter);
    }

    public String[] getArray(String string, int perLength)
    {
        String str[] = null;
        Log.e("aaa", String.valueOf(string.length()));
        int index = string.length() / perLength;
        if (index == 0)
        {
            str = new String[1];
            str[0] = StringFilter(string);
        }
        else
        {
            str = new String[index + 1];
            int locateLength = 0;
            for (int i = 0; i <= index; i++)
            {
                if (i < index)
                {
                    str[i] = StringFilter(string.substring(locateLength, locateLength + perLength));
                }
                else
                {
                    str[i] = StringFilter(string.substring(locateLength, string.length()));
                }
                locateLength += perLength;
            }
        }
        return str;
    }

    // 替换、过滤特殊字符
    public static String StringFilter(String str) throws PatternSyntaxException
    {
        str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.my_view_page, menu);
        return true;
    }

}
