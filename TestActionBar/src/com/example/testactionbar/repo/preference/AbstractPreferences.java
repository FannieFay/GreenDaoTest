package com.example.testactionbar.repo.preference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public abstract class AbstractPreferences
{
    protected SharedPreferences sPreferences;
    protected SharedPreferences.Editor editor;

    public AbstractPreferences(Context context, String name)
    {
        initPreferences(context, name);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint({ "WorldWriteableFiles", "CommitPrefEdits" })
    void initPreferences(Context context, String name)
    {
        sPreferences = context.getSharedPreferences(name, Context.MODE_WORLD_WRITEABLE);
        editor = sPreferences.edit();
    }

    public void setValue(String key, String value)
    {
        editor.putString(key, value).commit();
    }

    public String getStringValue(String key)
    {
        return sPreferences.getString(key, null);
    }

    public void setValue(String key, int value)
    {
        editor.putInt(key, value).commit();
    }
    
    public void setValue(String key, long value)
    {
        editor.putLong(key, value);
    }
    
    public long getLongValue(String key){
        return sPreferences.getLong(key, 0);
    }

    public int getIntValue(String key)
    {
        return sPreferences.getInt(key, 0);
    }

    public void setValue(String key, Float value)
    {
        editor.putFloat(key, value).commit();
    }

    public float getFloatValue(String key)
    {
        return sPreferences.getFloat(key, 0);
    }

    public void setValue(String key, Boolean value)
    {
        editor.putBoolean(key, value).commit();
    }

    public Boolean getBooleanValue(String key)
    {
        return sPreferences.getBoolean(key, false);
    }

    public void removeKey(String key)
    {
        editor.remove(key);
        editor.commit();
    }
}
