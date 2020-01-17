package com.xfcar.driver;

/**
 * @author linky
 */

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.L;

import okhttp3.Cookie;

/**
 * @author Linky
 * 当 Module 作为 app 运行时，需要继承这个文件
 */
public class LibApplication extends Application {

    public static LibApplication sInstance;
    private ActivityLifecycleCallbacks callbacks;
    public Activity mCurAct;
    public DataManager mDataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        L.init();

        sInstance = this;
        mDataManager = new DataManager(this);
        registerActivity();
    }

    public String getToken() {
        return mDataManager.getToken();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(this).clearMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory();
        }
        Glide.get(this).trimMemory(level);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(callbacks);
    }

    private void registerActivity() {

        //注册对app内所有activity 的生命周期监听
        callbacks = new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                mCurAct = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        };
        registerActivityLifecycleCallbacks(callbacks);
    }

}