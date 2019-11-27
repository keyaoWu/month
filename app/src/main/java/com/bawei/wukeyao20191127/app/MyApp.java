package com.bawei.wukeyao20191127.app;

import android.app.Application;
import android.content.Context;

import com.bawei.wukeyao20191127.carsh.CarshHandler;

/**
 * 功能：MyApp类
 * 作者：武柯耀
 * 当前日期：2019/11/27
 * 当前时间：8:53
 */
public class MyApp extends Application {
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
       mContext = this;
        CarshHandler.getInstance().init(this);
    }
}
