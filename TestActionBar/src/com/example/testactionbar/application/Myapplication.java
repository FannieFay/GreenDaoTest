package com.example.testactionbar.application;

import android.app.Application;
import android.content.Context;

import com.example.testactionbar.common.Constans;
import com.example.testactionbar.db.DaoMaster;
import com.example.testactionbar.db.DaoMaster.OpenHelper;
import com.example.testactionbar.db.DaoSession;

public class Myapplication extends Application
{
    private static Myapplication mInstance = null;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static Myapplication getInstance()
    {
        return mInstance;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    /**
     * 取得DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context)
    {
        if (daoMaster == null)
        {
            OpenHelper helper = new DaoMaster.DevOpenHelper(context, Constans.DATABASE_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context)
    {
        if (daoSession == null)
        {
            if (daoMaster == null)
            {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

}
