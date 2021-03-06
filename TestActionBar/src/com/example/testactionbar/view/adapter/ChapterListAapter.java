package com.example.testactionbar.view.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.testactionbar.R;
import com.example.testactionbar.modle.ChapterInfo;

public class ChapterListAapter extends BaseAdapter
{
    ArrayList<ChapterInfo> aChapters;
    Context mContext;

    public ChapterListAapter(Context mContext, ArrayList<ChapterInfo> aChapters)
    {
        this.mContext = mContext;
        this.aChapters = aChapters;
    }

    public void setList(ArrayList<ChapterInfo> aChapters)
    {
        this.aChapters = aChapters;
        notifyDataSetChanged();
    }

    public ArrayList<ChapterInfo> getList()
    {
        return this.aChapters;
    }

    @Override
    public int getCount()
    {
        return aChapters.size();
    }

    @Override
    public ChapterInfo getItem(int position)
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
        ChapterInfo chapter = aChapters.get(position);
        ViewHolder mViewHolder = null;
        if (convertView == null)
        {
            mViewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.adapter_chapter_list, null);
            mViewHolder.mTextName = (TextView) convertView
                    .findViewById(R.id.adapter_chapter_name_tv);
            mViewHolder.mTextPosition = (TextView) convertView
                    .findViewById(R.id.adapter_chapter_position_tv);
            convertView.setTag(mViewHolder);
        }
        else
        {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.mTextName.setText(chapter.getName());
        mViewHolder.mTextPosition.setText(String.valueOf(aChapters.size() - position));
        return convertView;
    }

    class ViewHolder
    {
        TextView mTextName;
        TextView mTextPosition;
    }
}
