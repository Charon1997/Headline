package com.nmid.headline.util;

import android.app.Application;
import android.content.Context;

import com.jakewharton.threetenabp.AndroidThreeTen;

/**
 * Created by xwysu on 2017/5/10.
 * 用于全局获取Context对象，并且对AndroidThreeTen（java.util.time）进行注册
 */

public class AppContext extends Application{
    private static Context instance;

    @Override
    public void onCreate()
    {
        instance = getApplicationContext();
        AndroidThreeTen.init(this);
    }

    public static Context getContext()
    {
        return instance;
    }
}
