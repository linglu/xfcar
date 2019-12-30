package com.xfcar.driver.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.xfcar.driver.utils.ToastUtils;

import androidx.fragment.app.FragmentActivity;


public abstract class BaseActivity extends FragmentActivity {
    private boolean mIsActivityDestroyed = false;
    private boolean isInResume = false;
    protected FragmentActivity mInstance = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keepFontSize();
    }


    protected void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    protected void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    protected void startActivityForResult(Class clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    protected void startActivityForResult(Class clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivityForResult(intent, requestCode);
    }


    public boolean isActivityDestroyed() {
        return mIsActivityDestroyed;
    }


    protected void toastMsg(String str) {
        ToastUtils.show(this, str);
    }

    protected void toastMsg(int str) {
        ToastUtils.show(this, getString(str));
    }

    /**
     * 释放Toast
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIsActivityDestroyed = true;

    }

    @Override
    protected void onResume() {
        super.onResume();
        isInResume = true;

    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isInResume = false;
    }

    public boolean isInResume() {
        return isInResume;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        dealTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    public void dealTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
    }

    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }


    private void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void keepFontSize(){
        Resources res = getResources();
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.fontScale = 1;
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}

