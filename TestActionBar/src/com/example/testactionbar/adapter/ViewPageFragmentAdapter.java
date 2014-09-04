package com.example.testactionbar.adapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

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

    public void setList(List<Fragment> fragments)
    {
        FragmentTransaction ft = fm.beginTransaction();
        if (this.fragments != null && this.fragments.size() != 0)
        {
            for (Fragment f : this.fragments)
            {
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            fm.executePendingTransactions();
        }
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position)
    {
        PageFragment fragment = new PageFragment();
        StartAndEnd startAndEnd = aStartAndEnds.get(position);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(IntentKey.INTENT_LIST_KEY, mList);
        bundle.putInt(IntentKey.INTENT_START_KEY, startAndEnd.getStart());
        bundle.putInt(IntentKey.INTENT_END_KEY, startAndEnd.getEnd());
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
        PageFragment fragment = (PageFragment) object;
        String title = fragment.;
        int position = titles.indexOf(title);

        if (position >= 0)
        {
            return position;
        }
        else
        {
            return POSITION_NONE;
        }
    }
}
