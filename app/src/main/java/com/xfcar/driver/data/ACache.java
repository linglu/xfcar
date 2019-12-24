package com.xfcar.driver.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @author Linky
 * 数据管理, 包括缓存数据和持久化数据
 */
public class ACache {

    private static final String CACHE_NAME = "cache_smart_house";
    private static SharedPreferences sp;

    public static void init(Context context) {
        sp = context.getSharedPreferences(CACHE_NAME, Context.MODE_PRIVATE);
    }

    public static void put(String key, Object value) {
        if (value != null) {
            sp.edit().putString(key, JSON.toJSONString(value)).apply();
        } else {
            sp.edit().putString(key, "").apply();
        }
    }

    public static <T> List<T> getList(String key, Class<T> clz) {
        String values = sp.getString(key, "");
        if (TextUtils.isEmpty(values)) {
            return null;
        } else {
            return JSON.parseArray(values, clz);
        }
    }

    public static <T> T get(String key, Class<T> clz) {
        String values = sp.getString(key, "");
        if (TextUtils.isEmpty(values)) {
            return null;
        } else {
            return JSON.parseObject(values, clz);
        }
    }
}
