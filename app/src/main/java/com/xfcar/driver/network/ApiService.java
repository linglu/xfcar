package com.xfcar.driver.network;

import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.model.adapterbean.ClaimPayBean;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;
import com.xfcar.driver.model.adapterbean.RepairBean;
import com.xfcar.driver.model.bean.AlarmRecordEntity;
import com.xfcar.driver.model.bean.AmountRechargeEntity;
import com.xfcar.driver.model.bean.CarObjectId;
import com.xfcar.driver.model.bean.Command;
import com.xfcar.driver.model.bean.QRCodeBean;
import com.xfcar.driver.model.bean.ShortRentEntity;
import com.xfcar.driver.model.bean.SysUserEntity;
import com.xfcar.driver.model.bean.UserEntity;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
    @FormUrlEncoded
    @POST("ioc/login/mobile")
    Observable<Response<SysUserEntity>> login(@Field("mobile") String mobile, @Field("smsCode") String smsCode);

    // 退出接口
    @POST("ioc/logout")
    Observable<Response<String>> logout();

    @POST("/ioc/user/add")
    Observable<Response<Object>> userAdd(@Body SysUserEntity sysuserentity);

    //App报警记录: JSON格式传参userId
    @POST("ioc/app/alarmRecord/oneKeyAlarm")
    Observable<Response<String>> appAlarmrecordOnekeyalarm(@Body AlarmRecordEntity alarmrecordentity);

    //App车辆信息: JSON格式传参userId
    @POST("ioc/app/car/getCarInfoByUser")
    Observable<Response<List<CarInfoBean>>> appCarGetcarinfobyuser(@Body UserEntity userEntity);

    //App车辆理赔: JSON格式传参userId
    @POST("ioc/app/car/insurance/getInsuranceByUser")
    Observable<Response<List<ClaimPayBean>>> appCarInsuranceGetinsurancebyuser(@Body UserEntity userEntity);

    //App预约返租: JSON格式传参userId
    @POST("ioc/app/car/leaseback/oneKey")
    Observable<Response<String>> appCarLeasebackOnekey(@Body UserEntity userEntity);

    //App车辆维护: JSON格式传参userId
    @POST("ioc/app/car/maintain/getMaintainByUser")
    Observable<Response<List<RepairBean>>> appCarMaintainGetmaintainbyuser(@Body UserEntity userEntity);

    //App车辆操作: 传参：carNo, command
    @POST("ioc/app/car/operate/command")
    Observable<Response<String>> appCarOperateCommand(@Body Command command);

    //App平台销卖出租车型表
    @POST("ioc/app/car/sellModel/info/{id}")
    Observable<Response<String>> appCarSellmodelInfo(@Path("id") int id);

    @GET("ioc/app/car/sellModel/getList")
    Observable<Response<List<RentCarInfoBean>>> appCarSellModelGetList();

    //App平台销卖出租车型表
    @POST("ioc/app/car/sellModel/list")
    Observable<Response<String>> appCarSellmodelList(@Body Object object);

    //预约短租: Json格式传参：subletDate 转租时间
    @POST("ioc/app/car/shortRent/add")
    Observable<Response<String>> appCarShortrentAdd(@Body ShortRentEntity shortrententity);

    //App车况监控: 参数：objectid（车牌号和手机串号绑定后生成返回的ID，保存在车辆信息表中）
    @POST("ioc/app/monitor/position")
    Observable<Response<String>> appMonitorPosition(@Body CarObjectId objectId);

    //['App公司账户信息配置表']
    @GET("ioc/app/getPaymentSource")
    Observable<Response<QRCodeBean>> getPaymentSource(@Query("type") int type);

    //App充值: JSON格式传参userId
    @POST("ioc/app/recharge/getRechargeByUser")
    Observable<Response<String>> appRechargeGetrechargebyuser(@Body AmountRechargeEntity amountrechargeentity);
}