package com.xfcar.driver;

/**
 * @author linky
 */

import android.app.Application;

import com.bumptech.glide.Glide;
import com.xfcar.driver.data.ACache;
import com.xfcar.driver.utils.L;

/**
 * @author Linky
 * 当 Module 作为 app 运行时，需要继承这个文件
 */
public class LibApplication extends Application {

    public static LibApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        L.init();

        sInstance = this;
        ACache.init(this);
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
    }

}