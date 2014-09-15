package com.example.testactionbar.view.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.testactionbar.R;
import com.example.testactionbar.modle.BookCategory;

public class TypeRankAdapter extends BaseAdapter
{
    private ArrayList<BookCategory> arrayList;
    private Context context;

    public TypeRankAdapter(Context context, ArrayList<BookCategory> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount()
    {
        return arrayList.size();
    }

    @Override
    public BookCategory getItem(int position)
    {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_type_rank, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.initData(getItem(position));

        return convertView;
    }

    class ViewHolder
    {
        TextView textView;

        public ViewHolder(View view)
        {
            textView = (TextView) view.findViewById(R.id.adapter_tv);
        }

        public void initData(BookCategory bookCategory)
        {
            textView.setText(bookCategory.getBookName());
        }
    }
}
