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
}
