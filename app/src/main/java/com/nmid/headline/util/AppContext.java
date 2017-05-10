package com.nmid.headline.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by xwysu on 2017/5/10.
 */

public class AppContext extends Application{
    private static Context instance;

    @Override
    public void onCreate()
    {
        instance = getApplicationContext();
    }

    public static Context getContext()
    {
        return instance;
    }
}
