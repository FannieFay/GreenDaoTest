package com.example.testactionbar.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.testactionbar.R;
import com.example.testactionbar.common.Constans.RankName;
import com.example.testactionbar.common.Constans.RankUrl;
import com.example.testactionbar.common.IntentKey;
import com.example.testactionbar.view.base.BaseActionBarActivity;

public class RankActivity extends BaseActionBarActivity
{
    LinearLayout linear_rank;
    ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        initView();
    }

    private void initView()
    {
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(getResources().getString(R.string.bookRank));

        linear_rank = (LinearLayout) findViewById(R.id.linear_rank);
        String typeName = null;
        String typeUrl = null;
        for (int i = 0; i < 9; i++)
        {
            Button button = new Button(this);

            switch (i)
            {

                case 0:
                    typeName = RankName.total;
                    typeUrl = RankUrl.total;
                    break;
                case 1:
                    typeName = RankName.xunhuan;
                    typeUrl = RankUrl.xunhuan;
                    break;
                case 2:
                    typeName = RankName.yanqing;
                    typeUrl = RankUrl.yanqing;
                    break;
                case 3:
                    typeName = RankName.xianxia;
                    typeUrl = RankUrl.xianxia;
                    break;
                case 4:
                    typeName = RankName.lishi;
                    typeUrl = RankUrl.lishi;
                    break;
                case 5:
                    typeName = RankName.wangyou;
                    typeUrl = RankUrl.wangyou;
                    break;
                case 6:
                    typeName = RankName.lingyi;
                    typeUrl = RankUrl.lingyi;
                    break;
                case 7:
                    typeName = RankName.tongren;
                    typeUrl = RankUrl.tongren;
                    break;
                case 8:
                    typeName = RankName.quanben;
                    typeUrl = RankUrl.quanben;
                    break;

                default:
                    break;
            }

            final String url = typeUrl;
            linear_rank.addView(button);
            button.setText(typeName);
            button.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent();
                    intent.setClass(RankActivity.this, BookListByRankActivity.class);
                    intent.putExtra(IntentKey.INTENT_URL_KEY, url);
                    startActivity(intent);
                }
            });

        }
    }
}
