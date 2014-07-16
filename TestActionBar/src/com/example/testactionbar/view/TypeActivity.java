package com.example.testactionbar.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.testactionbar.R;
import com.example.testactionbar.common.Constans.BookTypeName;
import com.example.testactionbar.common.Constans.BookTypeUrl;

public class TypeActivity extends Activity
{
    LinearLayout linear_type;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        initView();
    }

    private void initView()
    {
        linear_type = (LinearLayout) findViewById(R.id.linear_type);
        String typeName = null;
        String typeUrl = null;
        for (int i = 0; i < 8; i++)
        {
            Button button = new Button(this);

            switch (i)
            {
            case 0:
                typeName = BookTypeName.xunhuan;
                typeUrl = BookTypeUrl.xunhuan;
                break;
            case 1:
                typeName = BookTypeName.yanqing;
                typeUrl = BookTypeUrl.yanqing;
                break;
            case 2:
                typeName = BookTypeName.xianxia;
                typeUrl = BookTypeUrl.xianxia;
                break;
            case 3:
                typeName = BookTypeName.lishi;
                typeUrl = BookTypeUrl.lishi;
                break;
            case 4:
                typeName = BookTypeName.wangyou;
                typeUrl = BookTypeUrl.wangyou;
                break;
            case 5:
                typeName = BookTypeName.lingyi;
                typeUrl = BookTypeUrl.lingyi;
                break;
            case 6:
                typeName = BookTypeName.tongren;
                typeUrl = BookTypeUrl.tongren;
                break;
            case 7:
                typeName = BookTypeName.quanben;
                typeUrl = BookTypeUrl.quanben;
                break;

            default:
                break;
            }

            final String url = typeUrl;
            button.setText(typeName);
            linear_type.addView(button);
            button.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent();
                    intent.setClass(TypeActivity.this, BookListActivity.class);
                    intent.putExtra(BookListActivity.BOOKLISTURL, url);
                    intent.putExtra(BookListActivity.BOOKLIST_TYPE,
                            BookListActivity.type_type);
                    startActivity(intent);
                }
            });

        }
    }
}
