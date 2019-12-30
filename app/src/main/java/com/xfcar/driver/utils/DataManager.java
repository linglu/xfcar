package com.xfcar.driver.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 轻量的存储项
 */
public class DataManager {

    private static final String PREF_NAME = "xfcar";
    private final SharedPreferences mPref;
    private Context mContext;

    private final String MOBILE = "MOBILE";
    private final String LOGIN = "LOGIN";
    private final String COOKIE = "COOKIE";

    public DataManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mContext = context;
    }

    public void setMobile(String mobile) {
        mPref.edit().putString(MOBILE, mobile).apply();
    }

    public String getMobile() {
        return mPref.getString(MOBILE, "");
    }

    public void setUserId(int userId) {
        mPref.edit().putInt(LOGIN, userId).apply();
    }

    public Integer getUserId() {
        return mPref.getInt(LOGIN, 0);
    }

    public void setCookie(String cookie) {
        mPref.edit().putString(COOKIE, cookie).apply();
    }

    public String getCookie() {
        return mPref.getString(COOKIE, null);
    }

    public boolean isLogin() {
        return getUserId() != 0;
    }

}
