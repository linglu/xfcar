package com.xfcar.driver.network;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Linky
 */
public interface ApiService {

    // 获取验证码
    @GET("/ioc/code/sms")
    Observable<Response<String>> verifyCode(@Query("mobile") String mobile);

    // 登录接口
    @GET("/ioc/login")
    Observable<Response<String>> login(@Query("mobile") String mobile, @Query("smsCode") String smsCode);

}