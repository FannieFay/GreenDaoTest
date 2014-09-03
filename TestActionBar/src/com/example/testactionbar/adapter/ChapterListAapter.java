package com.example.testactionbar.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.testactionbar.R;
import com.example.testactionbar.presenter.modle.Chapter;

public class ChapterListAapter extends BaseAdapter
{
    ArrayList<Chapter> aChapters;
    Context mContext;

    public ChapterListAapter(Context mContext, ArrayList<Chapter> aChapters)
    {
        this.mContext = mContext;
        this.aChapters = aChapters;
    }

    public void setList(ArrayList<Chapter> aChapters)
    {
        this.aChapters = aChapters;
        notifyDataSetChanged();
    }

    public ArrayList<Chapter> getList()
    {
        return this.aChapters;
    }

    @Override
    public int getCount()
    {
        return aChapters.size();
    }

    @Override
    public Chapter getItem(int position)
    {
        return aChapters.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Chapter chapter = aChapters.get(position);
        ViewHolder mViewHolder = null;
        if (convertView == null)
        {
            mViewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.adapter_chapter_list, null);
            mViewHolder.mTextName = (TextView) convertView.findViewById(R.id.adapter_chapter_tv);
            convertView.setTag(mViewHolder);
        }
        else
        {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.mTextName.setText(chapter.getName());
        return convertView;
    }

    class ViewHolder
    {
        TextView mTextName;
    }
}
