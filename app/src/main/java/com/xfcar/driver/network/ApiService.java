package com.xfcar.driver.network;

import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.model.adapterbean.ClaimPayBean;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;
import com.xfcar.driver.model.adapterbean.RepairBean;
import com.xfcar.driver.model.bean.AlarmRecordEntity;
import com.xfcar.driver.model.bean.BusinessResp;
import com.xfcar.driver.model.bean.BusinessType;
import com.xfcar.driver.model.bean.CarSecurityBean;
import com.xfcar.driver.model.bean.Command;
import com.xfcar.driver.model.bean.ContactDTO;
import com.xfcar.driver.model.bean.EnLogoGram;
import com.xfcar.driver.model.bean.ExchangeGoods;
import com.xfcar.driver.model.bean.IntegralGoodsVo;
import com.xfcar.driver.model.bean.InviteFriendEntity;
import com.xfcar.driver.model.bean.InviteRewardBean;
import com.xfcar.driver.model.bean.LoginResponse;
import com.xfcar.driver.model.bean.MessageBean;
import com.xfcar.driver.model.bean.MessageResp;
import com.xfcar.driver.model.bean.PasswordVO;
import com.xfcar.driver.model.bean.QRCodeBean;
import com.xfcar.driver.model.bean.ShortRentBean;
import com.xfcar.driver.model.bean.ShortRentEntity;
import com.xfcar.driver.model.bean.SignInOutBean;
import com.xfcar.driver.model.bean.SysUserEntity;
import com.xfcar.driver.model.bean.UserEntity;
import com.xfcar.driver.model.bean.UserId;
import com.xfcar.driver.model.mybean.OursBean;
import com.xfcar.driver.model.viewbean.RechargeItemBean;
import com.xfcar.driver.model.viewbean.ScoreProductBean;
import com.xfcar.driver.model.viewbean.ScoreRespBean;
import com.xfcar.driver.model.viewbean.ScoreTypeBean;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Linky
 */
public interface ApiService {


    // 头部 消息 和 banner 图片
    //消息中心 -> 我的消息
    @POST("ioc/app/user/message/get")
    Observable<Response<MessageResp>> userMessageGet(@Body UserId ui);

    @POST("ioc/app/business/config/find")
    Observable<Response<String>> businessConfigFind(@Body BusinessType ui);

    @POST("ioc/app/business/config/list")
    Observable<Response<BusinessResp>> businessConfigList(@Body BusinessType ui);


    //转单与订单收益

    //购车租车图片、车型介绍、购买
    @POST("ioc/app/car/config/get")
    Observable<Response<String>> carConfigGet(@Body UserId ui);

    @POST("ioc/app/car/shortRent/add")
    Observable<Response<String>> carShortRentAdd(@Body ShortRentBean bean);

    //充值管理到期、充值接口
    @POST("ioc/app/car/customer/business/find")
    Observable<Response<String>> carCustomerBusinessFind(@Body UserId ui);

    @POST("ioc/app/recharge/add")
    Observable<Response<String>> rechargeAdd(@Body UserId ui);

    //用户名与用户头像
    @POST("ioc/app/user/get")
    Observable<Response<String>> userGet(@Body UserId ui);

    //账目总览、奖励记录
    @POST("ioc/app/user/integralLog/find")
    Observable<Response<ScoreRespBean>> userIntegrallogFind(@Body UserId ui);

    @POST("ioc/app/user/integralLog/counts")
    Observable<Response<String>> userIntegrallogCounts(@Body UserId ui);

    // 用户反馈意见
    @POST("ioc/app/alarmRecord/feedback")
    Observable<Response<String>> alarmRecordFeedback(@Body MessageBean msg);

    //App报警记录: JSON格式传参userId
    @POST("ioc/app/alarmRecord/oneKeyAlarm")
    Observable<Response<String>> appAlarmRecordOneKeyAlarm(@Body AlarmRecordEntity alarmrecordentity);

    //App车辆信息: JSON格式传参userId
    @POST("ioc/app/car/getCarInfoByUser")
    Observable<Response<CarInfoBean>> appCarGetCarInfoByUser(@Body UserId ui);

    //App车辆信息: JSON格式传参userId
    @POST("ioc/app/car/getSecurityInfo")
    Observable<Response<CarSecurityBean>> appCarGetSecurityInfo(@Body UserId ui);

    //App车辆理赔: JSON格式传参userId
    @POST("ioc/app/car/insurance/getInsuranceByUser")
    Observable<Response<List<ClaimPayBean>>> appCarInsuranceGetInsuranceByUser(@Body UserId ui);

    //App预约返租: JSON格式传参userId
    @POST("ioc/app/car/leaseback/oneKey")
    Observable<Response<String>> appCarLeasebackOneKey(@Body UserId ui);

    //App车辆维护: JSON格式传参userId
    @POST("ioc/app/car/maintain/getMaintainByUser")
    Observable<Response<List<RepairBean>>> appCarMaintainGetMaintainByUser(@Body UserId ui);

    //App车辆操作: 传参：carNo, command
    @POST("ioc/app/car/operate/command")
    Observable<Response<String>> appCarOperateCommand(@Body Command command);

    //['App平台销卖出租车型表']
    @GET("ioc/app/car/sellModel/getList")
    Observable<Response<List<RentCarInfoBean>>> appCarSellModelGetList();

    @POST("/ioc/app/car/carInfoConfig/getList")
    Observable<Response<List<RentCarInfoBean>>> carInfoConfigGetList(@Body UserId ui);

    //App平台销卖出租车型表
    @POST("ioc/app/car/sellModel/info/{id}")
    Observable<Response<String>> appCarSellModelInfo(@Path("id") int id);

    //App平台销卖出租车型表: 默认pageNumber=1，pageSize=10
    @POST("ioc/app/car/sellModel/list")
    Observable<Response<String>> appCarSellModelList(@Body QueryBody body);

    //App预约短租: Json格式传参：subletDate 转租时间，userId 用户ID
    @POST("ioc/app/car/shortRent/add")
    Observable<Response<String>> appCarShortRentAdd(@Body ShortRentEntity shortrententity);

    //App预约短租: Json格式传参：userId 用户ID
    @POST("ioc/app/car/shortRent/getRentByUser")
    Observable<Response<String>> appCarShortRentGetRentByUser(@Body ShortRentEntity shortrententity);

    //公司信息表: Json格式，传参：enLogogram 公司字母缩写
    @POST("ioc/app/companyInfo/find")
    Observable<Response<OursBean>> appCompanyInfoFind(@Body EnLogoGram object);

    //App系统文章类，管理系统文章，包括注册协议等等: Json格式，传参 mark
    @POST("ioc/app/document/info")
    Observable<Response<String>> appDocumentInfo(@Body Object object);

    //App公司账户信息配置表: Json传参 type: 1-支付宝 2-微信 3-银行
    @GET("ioc/app/getPaymentSource")
    Observable<Response<QRCodeBean>> appGetPaymentSource(@Query("type") int type);

    //App积分商品
    @POST("ioc/app/integralGoods/findByGoodsTag")
    Observable<Response<List<ScoreProductBean>>> appIntegralGoodsFindByGoodsTag(@Body IntegralGoodsVo integralgoodsvo);

    //App车况监控: 参数：objectid（车牌号和手机串号绑定后生成返回的ID，保存在车辆信息表中）
    @POST("ioc/app/monitor/position")
    Observable<Response<String>> appMonitorPosition(@Body Object object);

    //App充值: JSON格式传参userId
    @POST("ioc/app/recharge/getRechargeByUser")
    Observable<Response<List<RechargeItemBean>>> appRechargeGetRechargeByUser(@Body UserId ui);

    //App用户积分消费订单表
    @POST("ioc/app/user/integralGoodsOrder/exchange")
    Observable<Response<String>> appUserIntegralGoodsOrderExchange(@Body ExchangeGoods exchangegoods);

    //App用户获得积分日志表: Json传参：userId
    @POST("ioc/app/user/integralLog/list")
    Observable<Response<List<ScoreTypeBean>>> appUserIntegralLogList(@Body Object object);

    //App邀请好友: Json传参：userId, userName, inviteFriendName, invitePhone
    @POST("ioc/app/user/inviteFriend/add")
    Observable<Response<String>> appUserInviteFriendAdd(@Body InviteFriendEntity invitefriendentity);

    //App邀请好友: Json传参：createDate:当月日期
    @POST("ioc/app/user/inviteFriend/byMonth")
    Observable<Response<String>> appUserInviteFriendByMonth(@Body Object object);

    //App邀请好友: Json传参：userId
    @POST("ioc/app/user/inviteFriend/inviteReward")
    Observable<Response<List<InviteRewardBean>>> appUserInviteFriendInviteReward(@Body Object object);

    //App用户: JSON格式传参userId, contactName, contactMobile
    @POST("ioc/app/user/updateContactByUser")
    Observable<Response<String>> appUserUpdateContactByUser(@Body ContactDTO contactdto);

    //App用户: JSON格式传参userId, oldPassword, newPassword, mobile
    @POST("ioc/app/user/updatePwByUser")
    Observable<Response<String>> appUserUpdatePwByUser(@Body PasswordVO passwordvo);


    // 获取验证码
    @GET("/ioc/app/code/sms")
    Observable<Response<String>> verifyCode(@Query("mobile") String mobile);

    // 登录接口
    @POST("ioc/app/login/mobile")
    Observable<Response<LoginResponse>> login(@Body SignInOutBean ue);

    // 退出接口
    @POST("ioc/app/logout")
    Observable<Response<String>> logout();

    @POST("/ioc/app/user/register")
    Observable<Response<Object>> userAdd(@Body SysUserEntity sysuserentity);

    //['App公司账户信息配置表']
    @GET("ioc/app/getPaymentSource")
    Observable<Response<QRCodeBean>> getPaymentSource(@Query("type") int type);

    //App车辆信息: JSON格式传参userId
    @POST("ioc/app/car/getSecurityInfo")
    Observable<Response<CarSecurityBean>> appCarGetsecurityinfo(@Body UserId ui);


    //App车辆信息: JSON格式传参userId
    @POST("ioc/app/car/appCarGetCarInfoByUser")
    Observable<Response<CarInfoBean>> appCarGetcarinfobyuser(@Body UserEntity userEntity);

    //App车辆理赔: JSON格式传参userId
    @POST("ioc/app/car/insurance/getInsuranceByUser")
    Observable<Response<List<ClaimPayBean>>> appCarInsuranceGetinsurancebyuser(@Body UserEntity userEntity);

    //App车辆维护: JSON格式传参userId
    @POST("ioc/app/car/maintain/getMaintainByUser")
    Observable<Response<List<RepairBean>>> appCarMaintainGetmaintainbyuser(@Body UserEntity userEntity);


}