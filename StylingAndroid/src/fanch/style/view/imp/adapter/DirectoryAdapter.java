package fanch.style.view.imp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DirectoryAdapter extends BaseAdapter
{
    ArrayList<String> mArrayList;
    Context context;

    public DirectoryAdapter(Context context, ArrayList<String> mArrayList)
    {
        this.context = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public int getCount()
    {
        return mArrayList.size();
    }

    @Override
    public String getItem(int position)
    {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        TextView textView = new TextView(context);
        textView.setText(mArrayList.get(position));
        textView.setPadding(50, 25, 50, 25);
        return textView;
    }

}
