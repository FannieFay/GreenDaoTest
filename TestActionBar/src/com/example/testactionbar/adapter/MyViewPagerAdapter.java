package com.example.testactionbar.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyViewPagerAdapter extends PagerAdapter
{
    Context mContext;

    String str[];
    int index = 0;

    public MyViewPagerAdapter(Context mContext, String str[])
    {
        this.mContext = mContext;
        this.str = str;
    }

    @Override
    public int getCount()
    {
        return str.length;
    }

    @Override
    public Object instantiateItem(View collection, int position)
    {
        Log.e("add", String.valueOf(position));
        TextView view = new TextView(mContext);
        view.setText(str[position]);
        view.setTag("view" + position);
        ((ViewPager) collection).addView(view);
        return view;
    }

    @Override
    public void destroyItem(View collection, int position, Object o)
    {
        View view = (View) o;
        ((ViewPager) collection).removeView(view);
        view = null;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1)
    {
        return arg0 == arg1;
    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        return super.instantiateItem(container, position);
    }

    @Override
    public void finishUpdate(ViewGroup container)
    {
        super.finishUpdate(container);
    }

}
