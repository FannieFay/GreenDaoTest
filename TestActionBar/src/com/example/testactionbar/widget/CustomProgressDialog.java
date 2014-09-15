package com.example.testactionbar.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.testactionbar.R;

public class CustomProgressDialog extends Dialog
{
    private TextView tvMsg;
    private String mMsg;

    public CustomProgressDialog(Context context)
    {
        super(context, R.style.theme_dialog);
    }

    public CustomProgressDialog(Context context, String msg)
    {
        super(context, R.style.theme_dialog);
        mMsg = msg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        tvMsg = (TextView) findViewById(R.id.dialog_msg);
        if (mMsg != null && !mMsg.equals(""))
        {
            tvMsg.setText(mMsg);
        }
    }
}
