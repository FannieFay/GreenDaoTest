package com.example.testactionbar.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPageFragmentAdapter extends FragmentPagerAdapter
{
    private List<Fragment> fragments;

    public ViewPageFragmentAdapter(FragmentManager fm)
    {
        super(fm);
    }

    public ViewPageFragmentAdapter(FragmentManager fm, List<Fragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }
}
