package com.xfcar.driver.network;


import com.xfcar.driver.App;
import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.model.adapterbean.ClaimPayBean;
import com.xfcar.driver.model.adapterbean.RepairBean;
import com.xfcar.driver.model.bean.CarObjectId;
import com.xfcar.driver.model.bean.Command;
import com.xfcar.driver.model.bean.SysUserEntity;
import com.xfcar.driver.model.bean.UserEntity;
import com.xfcar.driver.network.converter.FastjsonConverterFactory;
import com.xfcar.driver.utils.L;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.Result;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.android.schedulers.AndroidSchedulers;
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

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);  // <-- this is the important line!
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request request = chain.request();
                    Request.Builder builder = request.newBuilder();

                    String cookie = App.sInstance.getCookie();
                    if (cookie != null) {
                        builder.addHeader("Cookie", cookie);
                    }

//                    Request.Builder builder = request.newBuilder();
//                    if
//                    if(cookie!=null) {
//                        builder.addHeader("Cookie", cookie);
//                        if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
//                            builder.addHeader("Connection", "close");
//                        }
//                    }

                    // 获取到 response
                    Response response = chain.proceed(builder.build());
                    L.i("headers : " + response.headers().toString());

                    String setCookie = response.header("Set-Cookie");
                    if (setCookie != null) {
                        String newCookie = setCookie.split(";")[0];
                        if (newCookie.contains("JSESSIONID")) {
                            App.sInstance.updateCookie(newCookie);
                        } else {
                            App.sInstance.updateCookie(null);
                        }
                    }

                    return response;
                }
            });

            client = httpClient.build();
//            client = new OkHttpClient.Builder()
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request request = chain.request();
//                            L.i("request " + request.url());
//                            L.i("" + request.headers().toString());
//                            return chain.proceed(request);
//                        }
//                    }).build();
        }


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
//                    .baseUrl("http://xuanfeng.xiaomy.net")
                    .baseUrl("http://39.108.166.99:9000")
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(FastjsonConverterFactory.create())
                    .build();
        }

        if (service == null) {
            service = retrofit.create(ApiService.class);
        }
    }

    public void monitorPosition(String objectId, final ResultCallback<String> callback) {
        service.monitorPosition(new CarObjectId(objectId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String o) {
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        callback.onFail(msg);
                    }
                });
    }

    public void carOperateCommand(String company, String carNo, String macid, String carCmd,
                                  final ResultCallback<String> callback) {
        Command cmd = new Command(company, carNo, macid, carCmd);
        service.carOperateCommand(cmd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String o) {
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        callback.onFail(msg);
                    }
                });
    }

    public void getCarInfoByUser(String userId, final ResultCallback<List<CarInfoBean>> callback) {
        service.getCarInfoByUser(new UserEntity(Integer.parseInt(userId)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<List<CarInfoBean>>() {

                    @Override
                    public void onSuccess(List<CarInfoBean> o) {
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        callback.onFail(msg);
                    }
                });
    }

    public void getMaintainByUser(String userId, final ResultCallback<List<RepairBean>> callback) {
        service.getMaintainByUser(new UserEntity(Integer.parseInt(userId)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<List<RepairBean>>() {

                    @Override
                    public void onSuccess(List<RepairBean> o) {
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        callback.onFail(msg);
                    }
                });
    }

    public void getInsuranceByUser(String userId, final ResultCallback<List<ClaimPayBean>> callback) {
        service.getInsuranceByUser(new UserEntity(Integer.parseInt(userId)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<List<ClaimPayBean>>() {

                    @Override
                    public void onSuccess(List<ClaimPayBean> o) {
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        callback.onFail(msg);
                    }
                });
    }

    public void logout(final ResultCallback<String> callback) {
        service.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String o) {
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        callback.onFail(msg);
                    }
                });
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

    public void login(String mobile, String code, final ResultCallback<SysUserEntity> callback) {
        service.login(mobile, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<SysUserEntity>() {

                    @Override
                    public void onSuccess(SysUserEntity res) {
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
