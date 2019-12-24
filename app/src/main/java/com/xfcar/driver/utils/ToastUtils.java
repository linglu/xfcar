package com.xfcar.driver.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author linky
 */
public class ToastUtils {

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
