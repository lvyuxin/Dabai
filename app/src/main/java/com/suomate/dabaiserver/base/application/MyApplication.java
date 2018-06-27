package com.suomate.dabaiserver.base.application;

import android.app.Application;

import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;

/**
 * Created by 李平 on 2017/8/11.
 * 第三方库初始化
 */

public class MyApplication extends Application {
    private static MyApplication app;
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        NoHttp.initialize(this);
        Logger.setDebug(true);
        Logger.setTag("NoHttp");
    }
    public static MyApplication getInstance(){
        return app;
    }
}
