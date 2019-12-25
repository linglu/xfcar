package com.xfcar.driver.network;

import android.content.Intent;
import android.util.Log;

import com.xfcar.driver.App;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.view.LoginActivity;

import rx.Subscriber;

/**
 * @author Linky
 */
public abstract class NetworkSubscriber<T> extends Subscriber<Response<T>> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        L.i("onError " + Log.getStackTraceString(e));
        onFail(e.getMessage());
    }

    @Override
    public void onNext(Response<T> response) {
        if (response.code == 1) {
            onSuccess(response.data);
        } else if (response.code == 401) {
            // 跳转到 登录界面
            App.sInstance.startActivity(new Intent(App.sInstance.mCurAct, LoginActivity.class));
        } else {
            onFail(response.msg);
        }
    }

    public abstract void onSuccess(T t);
    public abstract void onFail(String msg);
}
