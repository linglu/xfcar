package com.xfcar.driver.utils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.xfcar.driver.BuildConfig;

/**
 * @author linky
 * Des: Log日志打印封装
 * 使用 Logger 进行打印日志格式化
 */
public class L {

    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static final String TAG = "xfcar-log";

    public static void init() {

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(2)
                .tag(TAG)
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {

            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    /**
     * 下面四个是默认tag的函数
     */
    public static void i(String msg) {
        Logger.i(msg);
    }


    public static void d(String msg) {
        Logger.d(msg);

    }

    public static void e(String msg) {
        Logger.e(msg);

    }

    public static void v(String msg) {
        Logger.v(msg);
    }

    /**
     * 下面是传入自定义tag的函数
     */
    public static void i(String tag, String msg) {
        Logger.i(tag + ":" +msg);
    }

    public static void d(String tag, String msg) {
        Logger.d(tag+ ":" + msg);
    }

    public static void e(String tag, String msg) {
        Logger.e(tag+ ":" + msg);
    }

    public static void v(String tag, String msg) {
        Logger.v(tag+ ":" + msg);

    }

    public static void json(String jsonString) {
        Logger.json(jsonString);
    }
}