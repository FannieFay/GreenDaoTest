package com.example.testactionbar.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testactionbar.R;
import com.example.testactionbar.presenter.modle.BookInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class BookListAdapter extends BaseAdapter
{
    ArrayList<BookInfo> aBookInfos;
    Context mContext;

    ImageLoader imageLoader;
    DisplayImageOptions options;

    public BookListAdapter(Context mContext, ArrayList<BookInfo> aBookInfos)
    {
        this.mContext = mContext;
        this.aBookInfos = aBookInfos;
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

    public void setList(ArrayList<BookInfo> aBookInfos)
    {
        this.aBookInfos = aBookInfos;
        notifyDataSetChanged();
    }

    public ArrayList<BookInfo> getList()
    {
        return this.aBookInfos;
    }

    @Override
    public int getCount()
    {
        return aBookInfos.size();
    }

    @Override
    public BookInfo getItem(int position)
    {
        return aBookInfos.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        BookInfo mBookInfo = aBookInfos.get(position);
        ViewHolder mViewHolder = null;
        if (convertView == null)
        {
            mViewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.adapter_booklist, null);
            mViewHolder.mImageView = (ImageView) convertView
                    .findViewById(R.id.adapterBookListImage);
            mViewHolder.mTextName = (TextView) convertView
                    .findViewById(R.id.adapterBookListBookName);
            mViewHolder.mTextNewChapter = (TextView) convertView
                    .findViewById(R.id.adapterBookListNewChapter);
            convertView.setTag(mViewHolder);
        }
        else
        {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        imageLoader.displayImage(mBookInfo.getImage(), mViewHolder.mImageView, options);
        mViewHolder.mTextName.setText(mBookInfo.getBookName());
        mViewHolder.mTextNewChapter.setText(mBookInfo.getNewChapter());
        return convertView;
    }

    class ViewHolder
    {
        ImageView mImageView;
        TextView mTextName;
        TextView mTextNewChapter;
    }

}
