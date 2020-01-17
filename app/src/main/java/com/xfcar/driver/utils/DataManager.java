package com.xfcar.driver.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.xfcar.driver.model.adapterbean.CarInfoBean;

/**
 * 轻量的存储项
 */
public class DataManager {

    private static final String PREF_NAME = "xfcar";
    private final SharedPreferences mPref;

    private final String MOBILE = "MOBILE";
    private final String LOGIN = "LOGIN";

    public DataManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setMobile(String mobile) {
        mPref.edit().putString(MOBILE, mobile).apply();
    }

    public String getMobile() {
        return mPref.getString(MOBILE, "");
    }

    public String getToken() {
        return mPref.getString("token", "");
    }

    public void setUserId(int userId) {
        mPref.edit().putInt(LOGIN, userId).apply();
    }

    public Integer getUserId() {
        return mPref.getInt(LOGIN, 0);
    }


    public boolean isLogin() {
        return getUserId() != 0;
    }

    public void setUser(String userStr) {
        mPref.edit().putString("user", userStr).apply();
    }

    public void setCarInfo(String carStr) {
        mPref.edit().putString("carInfo", carStr).apply();
    }

    public CarInfoBean getCarInfo() {
        String carInfo = mPref.getString("carInfo", "");
        if (!carInfo.equals("")) {
            return JSON.parseObject(carInfo, CarInfoBean.class);
        } else {
            return null;
        }
    }

    public void setToken(String token) {
        mPref.edit().putString("token", token).apply();
    }

    public void setLock(String lock) {
        mPref.edit().putString("lock", lock).apply();
    }
}
