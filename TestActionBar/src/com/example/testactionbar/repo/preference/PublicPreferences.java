package com.example.testactionbar.repo.preference;

import android.content.Context;

public class PublicPreferences extends AbstractPreferences
{
    private static PublicPreferences _instance = null;
    private static Context mContext;

    /**
     * 是否设置名称
     */
    public static final String IS_SET_NAME = "is_set_name";

    private PublicPreferences(Context context)
    {
        super(context, "PublicPreferences");
    }

    public static PublicPreferences getInstance(Context ctx)
    {
        mContext = ctx;
        if (_instance == null)
        {
            _instance = new PublicPreferences(ctx);
        }
        return _instance;
    }

    /**
     * 获取是否登陆
     * 
     * @return
     */
    public boolean getIsSetName()
    {
        return getBooleanValue(IS_SET_NAME);
    }

}
