package com.example.testactionbar.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.testactionbar.R;

public class MenuPopupWindow extends PopupWindow
{
    private View mMenuView;
    private TextView mTvPop_content;

    public interface PopClickListener
    {
        public void onClickListener(View view);
    }

    public MenuPopupWindow(Context context, final PopClickListener popClickListener)
    {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.layout_menu_popwindow, null);

        mTvPop_content = (TextView) mMenuView.findViewById(R.id.pop_content);

        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimPop);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框

        mTvPop_content.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                popClickListener.onClickListener(v);
            }
        });

    }
}
