package com.example.testactionbar.adapter;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.testactionbar.common.IntentKey;
import com.example.testactionbar.presenter.modle.StartAndEnd;
import com.example.testactionbar.view.fragment.PageFragment;

public class ViewPageFragmentAdapter extends FragmentPagerAdapter
{
    private ArrayList<String> mList;
    private ArrayList<StartAndEnd> aStartAndEnds;
    FragmentManager fm;

    public ViewPageFragmentAdapter(FragmentManager fm, ArrayList<String> mList,
            ArrayList<StartAndEnd> aStartAndEnds)
    {
        super(fm);
        this.fm = fm;
        this.mList = mList;
        this.aStartAndEnds = aStartAndEnds;
    }

    public void setList(ArrayList<String> mList, ArrayList<StartAndEnd> aStartAndEnds)
    {
        this.mList = mList;
        this.aStartAndEnds = aStartAndEnds;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position)
    {
        PageFragment fragment = new PageFragment();
        StartAndEnd startAndEnd = aStartAndEnds.get(position);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(IntentKey.INTENT_LIST_KEY, mList);
        bundle.putSerializable(IntentKey.INTENT_START_END_KEY, startAndEnd);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount()
    {
        return aStartAndEnds.size();
    }

    @Override
    public int getItemPosition(Object object)
    {
        return POSITION_NONE;
    }
}
