package com.rjyx.quickdevelopment;

import android.app.Application;
import android.os.Handler;

/**
 * Created by rjyx on 2018/4/25.
 */

public class BaseApplication  extends Application{
    private static BaseApplication mApplication;
    private static int mMainTid;//主线程id
    private static Handler mHandler;//主线程处理handler
    private static Thread  mainThread ; //主线程 thread
    private boolean isDebug;  // 是否输出日志


    @Override
    public void onCreate() {
        synchronized (this) {
            if (mApplication == null) {
                mApplication = this;
            }
        mMainTid = android.os.Process.myTid();
        mHandler = new Handler();
        }
        super.onCreate();
    }

    public static BaseApplication getInstance() {
        return mApplication;
    }

    public static int getMainTid() {
        return mMainTid;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static void setMainThread(Thread mainThread) {
        BaseApplication.mainThread = mainThread;
    }

}
