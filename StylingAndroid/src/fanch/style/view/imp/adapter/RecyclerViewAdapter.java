package fanch.style.view.imp.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import fanch.style.R;
import fanch.style.mode.ItemData;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>
{
    private ItemData[] itemsData;

    public RecyclerViewAdapter(ItemData[] itemsData)
    {
        this.itemsData = itemsData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtViewTitle;
        public ImageView imgViewIcon;

        public ViewHolder(View itemLayoutView)
        {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.item_title);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.item_icon);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_recyclerview, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)
    {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        viewHolder2.txtViewTitle.setText(itemsData[position].getTitle());
        viewHolder2.imgViewIcon.setImageResource(itemsData[position].getImageUrl());
    }

    // 删除刷新都要用到 notifyItemRemoved(position);

    @Override
    public int getItemCount()
    {
        return itemsData.length;
    }

}
