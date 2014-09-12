package com.example.testactionbar.view.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.testactionbar.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class MyTestAdapter extends BaseAdapter
{
    Context mContext;
    ArrayList<String> strings;
    ImageLoader imageLoader;
    DisplayImageOptions options;

    public MyTestAdapter(Context mContext, ArrayList<String> strings)
    {
        this.mContext = mContext;
        this.strings = strings;
        imageLoader = ImageLoader.getInstance();
        ImageLoaderConfiguration mConfiguration = new ImageLoaderConfiguration.Builder(mContext)
                .memoryCacheExtraOptions(100, 100).threadPoolSize(5)
                .threadPriority(Thread.NORM_PRIORITY - 2).build();
        imageLoader.init(mConfiguration);

        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.icon) // 设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.ic_launcher)// 设置图片Uri为空或是错误的时候显示的图片
                .cacheInMemory(true)// 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)// 设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
                .displayer(new FadeInBitmapDisplayer(100)).build();
    }

    @Override
    public int getCount()
    {
        return strings.size();
    }

    @Override
    public Object getItem(int position)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        String string = strings.get(position);
        ViewHolder mViewHolder = null;
        if (convertView == null)
        {
            mViewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.adapter_mytestimageloader, null);
            mViewHolder.mImageView = (ImageView) convertView.findViewById(R.id.adapterImage);
            convertView.setTag(mViewHolder);
        }
        else
        {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        imageLoader.displayImage(string, mViewHolder.mImageView, options);
        return convertView;
    }

    private class ViewHolder
    {
        ImageView mImageView;
    }

}
