package com.xfcar.driver.network;

import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.model.adapterbean.ClaimPayBean;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;
import com.xfcar.driver.model.adapterbean.RepairBean;
import com.xfcar.driver.model.bean.CarSecurityBean;
import com.xfcar.driver.model.bean.QRCodeBean;
import com.xfcar.driver.model.bean.SysUserEntity;
import com.xfcar.driver.model.bean.UserEntity;
import com.xfcar.driver.model.mybean.OursBean;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author linky
 */
public interface ApiService1 {

    // 获取验证码
    @GET("/ioc/code/sms")
    Observable<Response<String>> verifyCode(@Query("mobile") String mobile);

    // 登录接口
    @FormUrlEncoded
    @POST("ioc/login/mobile")
    Observable<Response<SysUserEntity>> login(@Field("mobile") String mobile, @Field("smsCode") String smsCode);

    // 退出接口
    @POST("ioc/logout")
    Observable<Response<String>> logout();

    @POST("/ioc/user/add")
    Observable<Response<Object>> userAdd(@Body SysUserEntity sysuserentity);

    //['App公司账户信息配置表']
    @GET("ioc/app/getPaymentSource")
    Observable<Response<QRCodeBean>> getPaymentSource(@Query("type") int type);

    //App车辆信息: JSON格式传参userId
    @POST("ioc/app/car/getSecurityInfo")
    Observable<Response<CarSecurityBean>> appCarGetsecurityinfo(@Body UserEntity ue);


    //App车辆信息: JSON格式传参userId
    @POST("ioc/app/car/appCarGetCarInfoByUser")
    Observable<Response<CarInfoBean>> appCarGetcarinfobyuser(@Body UserEntity userEntity);

    //App车辆理赔: JSON格式传参userId
    @POST("ioc/app/car/insurance/getInsuranceByUser")
    Observable<Response<List<ClaimPayBean>>> appCarInsuranceGetinsurancebyuser(@Body UserEntity userEntity);

    //App车辆维护: JSON格式传参userId
    @POST("ioc/app/car/maintain/getMaintainByUser")
    Observable<Response<List<RepairBean>>> appCarMaintainGetmaintainbyuser(@Body UserEntity userEntity);

    //App车辆信息: JSON格式传参userId
    @POST("ioc//app/car/appCarGetCarInfoByUser")
    Observable<Response<CarInfoBean>> appCarGetCarInfoByUser(@Body UserEntity ue);

    //App车辆信息: JSON格式传参userId
    @POST("ioc//app/car/getSecurityInfo")
    Observable<Response<CarSecurityBean>> appCarGetSecurityInfo(@Body UserEntity ue);

    //App车辆理赔: JSON格式传参userId
    @POST("ioc//app/car/insurance/getInsuranceByUser")
    Observable<Response<List<ClaimPayBean>>> appCarInsuranceGetInsuranceByUser(@Body UserEntity ue);

    //App车辆维护: JSON格式传参userId
    @POST("ioc//app/car/maintain/getMaintainByUser")
    Observable<Response<List<RepairBean>>> appCarMaintainGetMaintainByUser(@Body UserEntity ue);

    //App公司账户信息配置表: Json传参 type: 1-支付宝 2-微信 3-银行
    @GET("ioc//app/getPaymentSource")
    Observable<Response<QRCodeBean>> appGetPaymentSource(@Query("type") int type);

    //公司信息表: Json格式，传参：enLogogram 公司字母缩写
    @POST("ioc/app/companyInfo/find")
    Observable<Response<OursBean>> appCompanyInfoFind(@Body Object object);


}
