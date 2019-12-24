package com.xfcar.driver.network;


import com.xfcar.driver.model.bean.SysUserEntity;
import com.xfcar.driver.network.converter.FastjsonConverterFactory;
import com.xfcar.driver.utils.L;

import java.io.IOException;
import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.schedulers.Schedulers;

/**
 * @author Linky
 *
 */
public class Requester {

    private Retrofit retrofit;
    private ApiService service;
    private OkHttpClient client;

    public Requester() {

        if (client == null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            L.i("request get " + request.url());
                            return chain.proceed(request);
                        }
                    }).build();
        }


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://39.108.166.99:9000/")
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(FastjsonConverterFactory.create())
                    .build();
        }

        if (service == null) {
            service = retrofit.create(ApiService.class);
        }
    }

    // 注册
    public void register(String name, String mobile, String pwd, int userType, final ResultCallback<Object> callback) {
        SysUserEntity sue = new SysUserEntity();
        sue.username = name;
        sue.mobile = mobile;
        sue.password = pwd;
        sue.userType = String.valueOf(userType);
        service.userAdd(sue)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<Object>() {

                    @Override
                    public void onSuccess(Object o) {
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        callback.onFail(msg);
                    }
                });
    }

    public void login(String mobile, String code, final ResultCallback<String> callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("smsCode", code);
        service.login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String res) {
                        callback.onSuccess(res);
                    }

                    @Override
                    public void onFail(String msg) {
                        callback.onFail(msg);
                    }
                });
    }

    public void getVerifyCode(String mobile, final ResultCallback<String> callback) {
        service.verifyCode(mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String res) {
                        callback.onSuccess(res);
                    }

                    @Override
                    public void onFail(String msg) {
                        callback.onFail(msg);
                    }
                });
    }

}
