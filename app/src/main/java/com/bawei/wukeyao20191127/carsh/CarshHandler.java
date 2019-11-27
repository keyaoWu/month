package com.bawei.wukeyao20191127.carsh;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * 功能：CarshHandler类
 * 作者：武柯耀
 * 当前日期：2019/11/27
 * 当前时间：10:01
 */
public class CarshHandler implements Thread.UncaughtExceptionHandler {
    private Context mContext;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private CarshHandler() {
    }

    private static class CarshHolder{
        static CarshHandler carshHandler = new CarshHandler();
    }

    public static CarshHandler getInstance() {
        return CarshHolder.carshHandler;
    }

    public void  init(Context context){
        this.mContext = context;
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
     if (!handlerException(throwable) && uncaughtExceptionHandler !=null){
         uncaughtExceptionHandler.uncaughtException(thread,throwable);
     }else {
         try {
             Thread.sleep(3000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
     android.os.Process.killProcess(android.os.Process.myPid());
     System.exit(1);
    }

    private boolean handlerException(Throwable throwable) {
        if (throwable==null){
            return false;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
        return false;
    }
}
