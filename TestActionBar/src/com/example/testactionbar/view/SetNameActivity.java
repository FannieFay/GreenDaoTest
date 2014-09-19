package com.example.testactionbar.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testactionbar.R;
import com.example.testactionbar.repo.preference.PublicPreferences;
import com.example.testactionbar.view.base.BaseActionBarActivity;

public class SetNameActivity extends BaseActionBarActivity
{
    EditText editText;
    Button button;
    PublicPreferences publicPreferences;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_name);

        editText = (EditText) findViewById(R.id.edit);
        button = (Button) findViewById(R.id.button);

        publicPreferences = PublicPreferences.getInstance(this);
        if (publicPreferences.getBooleanValue(PublicPreferences.IS_SET_NAME))
        {
            Intent intent = new Intent(SetNameActivity.this, ThemeActivity.class);
            startActivity(intent);
            finish();
        }
        button.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String string = editText.getText().toString();
                if (string.equals("范成浩"))
                {
                    publicPreferences.setValue(PublicPreferences.IS_SET_NAME, true);
                    Toast.makeText(SetNameActivity.this, "算你有点良心", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SetNameActivity.this, ThemeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(SetNameActivity.this, "连亲爱的名字都不知道,你的良心被猫吃啦", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }
}
