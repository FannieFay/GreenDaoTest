package com.example.testactionbar.view.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.testactionbar.R;
import com.example.testactionbar.common.IntentKey;
import com.example.testactionbar.widget.PageView;

public class PageFragment extends Fragment
{
    LinearLayout linear;
    View rootView;
    public static final String INTENT_PAGE_KEY = "intent_page_key";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_page, null);
            initView(rootView);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;
    }

    private void initView(View view)
    {
        linear = (LinearLayout) view.findViewById(R.id.linear);

        Bundle bundle = getArguments();
        ArrayList<String> arrayList = (ArrayList<String>) bundle
                .getStringArrayList(IntentKey.INTENT_LIST_KEY);
        int start = bundle.getInt(IntentKey.INTENT_START_KEY);
        int end = bundle.getInt(IntentKey.INTENT_END_KEY);
        PageView pageView = new PageView(getActivity(), arrayList, start, end);
        linear.addView(pageView);
    }

}
