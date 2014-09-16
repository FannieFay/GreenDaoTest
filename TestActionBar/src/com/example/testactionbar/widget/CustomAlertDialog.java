package com.example.testactionbar.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testactionbar.R;

public class CustomAlertDialog extends Dialog
{

    private Builder mBuilder;

    private TextView tvTitle, tvSubTitle;
    private Button btnLeft, btnRight;

    private DialogInterface mDialogInterface;

    public interface OnCustomClickListener
    {
        /**
         * This method will be invoked when a button in the dialog is clicked.
         * 
         * @param dialog
         *            The dialog that received the click.\
         * */
        public void onClick(DialogInterface dialog);
    }

    public CustomAlertDialog(Context context, Builder builder)
    {
        super(context, R.style.theme_dialog);
        mDialogInterface = this;
        mBuilder = builder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom_alert);
        initView();
    }

    private void initView()
    {
        tvTitle = (TextView) findViewById(R.id.title);
        tvSubTitle = (TextView) findViewById(R.id.subTitle);
        btnLeft = (Button) findViewById(R.id.leftBtn);
        btnRight = (Button) findViewById(R.id.rightBtn);
        if (mBuilder != null)
        {
            tvTitle.setText(mBuilder.getTitle());
            tvSubTitle.setText(mBuilder.getSubTitle());
            btnLeft.setText(mBuilder.getLeftButtonName());
            btnRight.setText(mBuilder.getRightButtonName());
            btnLeft.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    mBuilder.leftButtonListener.onClick(mDialogInterface);

                }
            });
            if (!mBuilder.isSingleButton())
            {
                btnRight.setText(mBuilder.getRightButtonName());

                btnRight.setOnClickListener(new View.OnClickListener()
                {

                    @Override
                    public void onClick(View v)
                    {
                        mBuilder.rightButtonListener.onClick(mDialogInterface);

                    }
                });

            }
            else
            {
                btnRight.setVisibility(View.GONE);
            }
        }

    }

    public static class Builder
    {
        private String title;
        private String subTitle;
        private String leftButtonName;
        private String rightButtonName;

        private OnCustomClickListener leftButtonListener;
        private OnCustomClickListener rightButtonListener;

        private Context mContext;

        private boolean isSingleButton = false;

        public Builder(Context context)
        {
            mContext = context;
        }

        public String getTitle()
        {
            return title;
        }

        public Builder setTitle(String title)
        {
            this.title = title;
            return this;
        }

        public Builder setTitle(int title)
        {
            this.title = mContext.getResources().getString(title);
            return this;
        }

        public String getSubTitle()
        {
            return subTitle;
        }

        public Builder setSubTitle(String subTitle)
        {
            this.subTitle = subTitle;
            return this;
        }

        public Builder setSubTitle(int subTitle)
        {
            this.subTitle = mContext.getResources().getString(subTitle);
            return this;
        }

        public Builder setLeftButton(int leftButtonName, OnCustomClickListener leftButtonListener)
        {
            this.leftButtonName = mContext.getResources().getString(leftButtonName);
            this.leftButtonListener = leftButtonListener;
            return this;
        }

        public Builder setLeftButton(String leftButtonName, OnCustomClickListener leftButtonListener)
        {
            this.leftButtonName = leftButtonName;
            this.leftButtonListener = leftButtonListener;
            return this;
        }

        public Builder setRightButton(int rightButtonName, OnCustomClickListener rightButtonListener)
        {
            this.rightButtonName = mContext.getResources().getString(rightButtonName);
            this.rightButtonListener = rightButtonListener;
            return this;
        }

        public Builder setRightButton(String rightButtonName,
                OnCustomClickListener rightButtonListener)
        {
            this.rightButtonName = rightButtonName;
            this.rightButtonListener = rightButtonListener;
            return this;
        }

        public String getLeftButtonName()
        {
            return leftButtonName;
        }

        public String getRightButtonName()
        {
            return rightButtonName;
        }

        public OnCustomClickListener getLeftButtonListener()
        {
            return leftButtonListener;
        }

        public OnCustomClickListener getRightButtonListener()
        {
            return rightButtonListener;
        }

        public boolean isSingleButton()
        {
            return isSingleButton;
        }

        public Builder setSingleButton(boolean isSingleButton)
        {
            this.isSingleButton = isSingleButton;
            return this;
        }

    }

}
