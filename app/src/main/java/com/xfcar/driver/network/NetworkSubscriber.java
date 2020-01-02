package com.xfcar.driver.network;

import android.content.Intent;
import android.util.Log;

import com.xfcar.driver.App;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.utils.ToastUtils;
import com.xfcar.driver.view.LoginActivity;

import retrofit2.adapter.rxjava.HttpException;
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

        if (e instanceof HttpException) {
            HttpException he = (HttpException) e;
            if (he.code() == 401) {

                DataManager dataManager = App.sInstance.mDataManager;
                dataManager.setUserId(0);

                // 跳转到 登录界面
                Intent intent = new Intent(App.sInstance.mCurAct, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                App.sInstance.mCurAct.startActivity(intent);
                App.sInstance.mCurAct.finish();

                ToastUtils.show(App.sInstance.mCurAct, "用户已过期, 请重新登录");
            } else if (he.code() == 502) {
                ToastUtils.show(App.sInstance.mCurAct, "服务器异常");
            } else {
                ToastUtils.show(App.sInstance.mCurAct, "网络异常");
            }
        }
        onFail(e.getMessage());
    }

    @Override
    public void onNext(Response<T> response) {
        if (response.code == 1) {
            onSuccess(response.data);
        } else {
            onFail(response.msg);
        }
    }

    public abstract void onSuccess(T t);
    public abstract void onFail(String msg);
}
