package com.xfcar.driver.network;

import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.model.adapterbean.ClaimPayBean;
import com.xfcar.driver.model.adapterbean.RepairBean;
import com.xfcar.driver.model.bean.AlarmRecordDTO;
import com.xfcar.driver.model.bean.AlarmRecordEntity;
import com.xfcar.driver.model.bean.AmountRechargeDTO;
import com.xfcar.driver.model.bean.AmountRechargeEntity;
import com.xfcar.driver.model.bean.AmountUseDTO;
import com.xfcar.driver.model.bean.AmountUseEntity;
import com.xfcar.driver.model.bean.CallCarDTO;
import com.xfcar.driver.model.bean.CallCarEntity;
import com.xfcar.driver.model.bean.CarInfoDTO;
import com.xfcar.driver.model.bean.CarInfoEntity;
import com.xfcar.driver.model.bean.CarInsuranceDTO;
import com.xfcar.driver.model.bean.CarInsuranceEntity;
import com.xfcar.driver.model.bean.CarMaintainDTO;
import com.xfcar.driver.model.bean.CarMaintainEntity;
import com.xfcar.driver.model.bean.CarObjectId;
import com.xfcar.driver.model.bean.CarOperateDTO;
import com.xfcar.driver.model.bean.Command;
import com.xfcar.driver.model.bean.CouponDTO;
import com.xfcar.driver.model.bean.CouponEntity;
import com.xfcar.driver.model.bean.CouponUserDTO;
import com.xfcar.driver.model.bean.CouponUserEntity;
import com.xfcar.driver.model.bean.DiscountBuyDTO;
import com.xfcar.driver.model.bean.DiscountBuyEntity;
import com.xfcar.driver.model.bean.DiscountInfoDTO;
import com.xfcar.driver.model.bean.DiscountInfoEntity;
import com.xfcar.driver.model.bean.DiscountItemDTO;
import com.xfcar.driver.model.bean.DiscountItemEntity;
import com.xfcar.driver.model.bean.LeasebackDTO;
import com.xfcar.driver.model.bean.LeasebackEntity;
import com.xfcar.driver.model.bean.MessageDTO;
import com.xfcar.driver.model.bean.MessageEntity;
import com.xfcar.driver.model.bean.PasswordVO;
import com.xfcar.driver.model.bean.RecommendDTO;
import com.xfcar.driver.model.bean.RecommendEntity;
import com.xfcar.driver.model.bean.SysDictEntity;
import com.xfcar.driver.model.bean.SysUserDTO;
import com.xfcar.driver.model.bean.SysUserDeptEntity;
import com.xfcar.driver.model.bean.SysUserEntity;
import com.xfcar.driver.model.bean.TrafficViolationDTO;
import com.xfcar.driver.model.bean.TrafficViolationEntity;
import com.xfcar.driver.model.bean.TransactionDTO;
import com.xfcar.driver.model.bean.TransactionEntity;
import com.xfcar.driver.model.bean.UserEntity;
import com.xfcar.driver.model.bean.VoucherDTO;
import com.xfcar.driver.model.bean.VoucherEntity;
import com.xfcar.driver.model.bean.VoucherUserDTO;
import com.xfcar.driver.model.bean.VoucherUserEntity;

import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
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


    @POST("/ioc/alarmRecord/add")
    Observable<Response<String>> alarmRecordAdd(@Body AlarmRecordEntity alarmrecordentity);

    @POST("/ioc/alarmRecord/delete")
    Observable<Response<String>> alarmRecordDelete(@Body AlarmRecordEntity alarmrecordentity);

    @POST("/ioc/alarmRecord/deleteBatch")
    Observable<Response<String>> alarmRecordDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/alarmRecord/find")
    Observable<Response<String>> alarmRecordFind(@Body AlarmRecordDTO alarmrecorddto);

    @POST("/ioc/alarmRecord/info/{id}")
    Observable<Response<String>> alarmRecordInfo(@Path("id") int id);

    @POST("/ioc/alarmRecord/list")
    Observable<Response<String>> alarmRecordList(@Body QueryBody body);

    @POST("/ioc/alarmRecord/update")
    Observable<Response<String>> alarmRecordUpdate(@Body AlarmRecordEntity alarmrecordentity);

    @POST("/ioc/call/car/add")
    Observable<Response<String>> callCarAdd(@Body CallCarEntity callcarentity);

    @POST("/ioc/call/car/delete")
    Observable<Response<String>> callCarDelete(@Body CallCarEntity callcarentity);

    @POST("/ioc/call/car/deleteBatch")
    Observable<Response<String>> callCarDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/call/car/find")
    Observable<Response<String>> callCarFind(@Body CallCarDTO callcardto);

    @POST("/ioc/call/car/info/{id}")
    Observable<Response<String>> callCarInfo(@Path("id") int id);

    @POST("/ioc/call/car/list")
    Observable<Response<String>> callCarList(@Body QueryBody body);

    @POST("/ioc/call/car/update")
    Observable<Response<String>> callCarUpdate(@Body CallCarEntity callcarentity);

    @POST("/ioc/car/add")
    Observable<Response<String>> carAdd(@Body CarInfoEntity carinfoentity);

    @POST("/ioc/car/amountUse/add")
    Observable<Response<String>> carAmountuseAdd(@Body AmountUseEntity amountuseentity);

    @POST("/ioc/car/amountUse/delete")
    Observable<Response<String>> carAmountuseDelete(@Body AmountUseEntity amountuseentity);

    @POST("/ioc/car/amountUse/deleteBatch")
    Observable<Response<String>> carAmountuseDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/car/amountUse/find")
    Observable<Response<String>> carAmountuseFind(@Body AmountUseDTO amountusedto);

    @POST("/ioc/car/amountUse/info/{id}")
    Observable<Response<String>> carAmountuseInfo(@Path("id") int id);

    @POST("/ioc/car/amountUse/list")
    Observable<Response<String>> carAmountuseList(@Body QueryBody body);

    @POST("/ioc/car/amountUse/update")
    Observable<Response<String>> carAmountuseUpdate(@Body AmountUseEntity amountuseentity);

    @POST("/ioc/car/delete")
    Observable<Response<String>> carDelete(@Body CarInfoEntity carinfoentity);

    @POST("/ioc/car/deleteBatch")
    Observable<Response<String>> carDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/car/find")
    Observable<Response<String>> carFind(@Body CarInfoDTO carinfodto);

    @POST("/ioc/car/info/{id}")
    Observable<Response<String>> carInfo(@Path("id") int id);

    @POST("/ioc/car/insurance/getInsuranceByUser")
    Observable<Response<List<ClaimPayBean>>> getInsuranceByUser(@Body UserEntity userEntity);

    @POST("/ioc/car/insurance/delete")
    Observable<Response<String>> carInsuranceDelete(@Body CarInsuranceEntity carinsuranceentity);

    @POST("/ioc/car/insurance/deleteBatch")
    Observable<Response<String>> carInsuranceDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/car/insurance/find")
    Observable<Response<String>> carInsuranceFind(@Body CarInsuranceDTO carinsurancedto);

    @POST("/ioc/car/insurance/info/{id}")
    Observable<Response<String>> carInsuranceInfo(@Path("id") int id);

    @POST("/ioc/car/insurance/list")
    Observable<Response<String>> carInsuranceList(@Body QueryBody body);

    @POST("/ioc/car/insurance/update")
    Observable<Response<String>> carInsuranceUpdate(@Body CarInsuranceEntity carinsuranceentity);

    @POST("/ioc/car/leaseback/add")
    Observable<Response<String>> carLeasebackAdd(@Body LeasebackEntity leasebackentity);

    @POST("/ioc/car/leaseback/find")
    Observable<Response<String>> carLeasebackFind(@Body LeasebackDTO leasebackdto);

    @POST("/ioc/car/leaseback/info/{id}")
    Observable<Response<String>> carLeasebackInfo(@Path("id") int id);

    @POST("/ioc/car/leaseback/list")
    Observable<Response<String>> carLeasebackList(@Body QueryBody body);

    @POST("/ioc/car/leaseback/update")
    Observable<Response<String>> carLeasebackUpdate(@Body LeasebackEntity leasebackentity);

    @POST("/ioc/car/list")
    Observable<Response<String>> carList(@Body QueryBody body);

    @POST("ioc/car/maintain/getMaintainByUser")
    Observable<Response<List<RepairBean>>> getMaintainByUser(@Body UserEntity userEntity);

    @POST("/ioc/car/maintain/delete")
    Observable<Response<String>> carMaintainDelete(@Body CarMaintainEntity carmaintainentity);

    @POST("/ioc/car/maintain/deleteBatch")
    Observable<Response<String>> carMaintainDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/car/maintain/find")
    Observable<Response<String>> carMaintainFind(@Body CarMaintainDTO carmaintaindto);

    @POST("/ioc/car/maintain/info/{id}")
    Observable<Response<String>> carMaintainInfo(@Path("id") int id);

    @POST("/ioc/car/maintain/list")
    Observable<Response<String>> carMaintainList(@Body QueryBody body);

    @POST("/ioc/car/maintain/update")
    Observable<Response<String>> carMaintainUpdate(@Body CarMaintainEntity carmaintainentity);

    @POST("/ioc/car/operate/bound")
    Observable<Response<String>> carOperateBound(@Body Command command);

    @POST("/ioc/car/operate/command")
    Observable<Response<String>> carOperateCommand(@Body Command command);

    @POST("/ioc/car/operate/equipmentList")
    Observable<Response<String>> carOperateEquipmentlist();

    @POST("/ioc/car/operate/find")
    Observable<Response<String>> carOperateFind(@Body CarOperateDTO caroperatedto);

    @POST("/ioc/car/operate/info/{id}")
    Observable<Response<String>> carOperateInfo(@Path("id") int id);

    @POST("/ioc/car/operate/list")
    Observable<Response<String>> carOperateList(@Body QueryBody body);

    @POST("/ioc/car/operate/unBound")
    Observable<Response<String>> carOperateUnbound(@Body Command command);

    @POST("/ioc/car/operate/updateBoundInfo")
    Observable<Response<String>> carOperateUpdateboundinfo(@Body Command command);

    @POST("/ioc/car/getCarInfoByUser")
    Observable<Response<List<CarInfoBean>>> getCarInfoByUser(@Body UserEntity carinfoentity);

    @GET("/code/{type}")
    Observable<Response<String>> code(@Path("type") int type);

    @POST("/ioc/coupon/add")
    Observable<Response<String>> couponAdd(@Body CouponEntity couponentity);

    @POST("/ioc/coupon/delete")
    Observable<Response<String>> couponDelete(@Body CouponEntity couponentity);

    @POST("/ioc/coupon/deleteBatch")
    Observable<Response<String>> couponDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/coupon/find")
    Observable<Response<String>> couponFind(@Body CouponDTO coupondto);

    @POST("/ioc/coupon/info/{id}")
    Observable<Response<String>> couponInfo(@Path("id") int id);

    @POST("/ioc/coupon/list")
    Observable<Response<String>> couponList(@Body QueryBody body);

    @POST("/ioc/coupon/update")
    Observable<Response<String>> couponUpdate(@Body CouponEntity couponentity);

    @POST("/ioc/couponUser/add")
    Observable<Response<String>> couponUserAdd(@Body CouponUserEntity couponuserentity);

    @POST("/ioc/couponUser/delete")
    Observable<Response<String>> couponUserDelete(@Body CouponUserEntity couponuserentity);

    @POST("/ioc/couponUser/deleteBatch")
    Observable<Response<String>> couponUserDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/couponUser/find")
    Observable<Response<String>> couponUserFind(@Body CouponUserDTO couponuserdto);

    @POST("/ioc/couponUser/info/{id}")
    Observable<Response<String>> couponUserInfo(@Path("id") int id);

    @POST("/ioc/couponUser/list")
    Observable<Response<String>> couponUserList(@Body QueryBody body);

    @POST("/ioc/couponUser/update")
    Observable<Response<String>> couponUserUpdate(@Body CouponUserEntity couponuserentity);

    @POST("/ioc/discount/add")
    Observable<Response<String>> discountAdd(@Body DiscountInfoEntity discountinfoentity);

    @POST("/ioc/discount/delete")
    Observable<Response<String>> discountDelete(@Body DiscountInfoEntity discountinfoentity);

    @POST("/ioc/discount/deleteBatch")
    Observable<Response<String>> discountDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/discount/find")
    Observable<Response<String>> discountFind(@Body DiscountInfoDTO discountinfodto);

    @POST("/ioc/discount/info/{id}")
    Observable<Response<String>> discountInfo(@Path("id") int id);

    @POST("/ioc/discount/list")
    Observable<Response<String>> discountList(@Body QueryBody body);

    @POST("/ioc/discount/update")
    Observable<Response<String>> discountUpdate(@Body DiscountInfoEntity discountinfoentity);

    @POST("/ioc/discountBuy/add")
    Observable<Response<String>> discountBuyAdd(@Body DiscountBuyEntity discountbuyentity);

    @POST("/ioc/discountBuy/delete")
    Observable<Response<String>> discountBuyDelete(@Body DiscountBuyEntity discountbuyentity);

    @POST("/ioc/discountBuy/deleteBatch")
    Observable<Response<String>> discountBuyDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/discountBuy/find")
    Observable<Response<String>> discountBuyFind(@Body DiscountBuyDTO discountbuydto);

    @POST("/ioc/discountBuy/info/{id}")
    Observable<Response<String>> discountBuyInfo(@Path("id") int id);

    @POST("/ioc/discountBuy/list")
    Observable<Response<String>> discountBuyList(@Body QueryBody body);

    @POST("/ioc/discountBuy/update")
    Observable<Response<String>> discountBuyUpdate(@Body DiscountBuyEntity discountbuyentity);

    @POST("/ioc/discountItem/add")
    Observable<Response<String>> discountItemAdd(@Body DiscountItemEntity discountitementity);

    @POST("/ioc/discountItem/delete")
    Observable<Response<String>> discountItemDelete(@Body DiscountItemEntity discountitementity);

    @POST("/ioc/discountItem/deleteBatch")
    Observable<Response<String>> discountItemDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/discountItem/find")
    Observable<Response<String>> discountItemFind(@Body DiscountItemDTO discountitemdto);

    @POST("/ioc/discountItem/info/{id}")
    Observable<Response<String>> discountItemInfo(@Path("id") int id);

    @POST("/ioc/discountItem/list")
    Observable<Response<String>> discountItemList(@Body QueryBody body);

    @POST("/ioc/discountItem/update")
    Observable<Response<String>> discountItemUpdate(@Body DiscountItemEntity discountitementity);

    @POST("/ioc/money/voucher/add")
    Observable<Response<String>> moneyVoucherAdd(@Body VoucherEntity voucherentity);

    @POST("/ioc/money/voucher/delete")
    Observable<Response<String>> moneyVoucherDelete(@Body VoucherEntity voucherentity);

    @POST("/ioc/money/voucher/deleteBatch")
    Observable<Response<String>> moneyVoucherDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/money/voucher/find")
    Observable<Response<String>> moneyVoucherFind(@Body VoucherDTO voucherdto);

    @POST("/ioc/money/voucher/info/{id}")
    Observable<Response<String>> moneyVoucherInfo(@Path("id") int id);

    @POST("/ioc/money/voucher/list")
    Observable<Response<String>> moneyVoucherList(@Body QueryBody body);

    @POST("/ioc/money/voucher/update")
    Observable<Response<String>> moneyVoucherUpdate(@Body VoucherEntity voucherentity);

    @POST("/ioc/money/voucherUser/add")
    Observable<Response<String>> moneyVoucheruserAdd(@Body VoucherUserEntity voucheruserentity);

    @POST("/ioc/money/voucherUser/delete")
    Observable<Response<String>> moneyVoucheruserDelete(@Body VoucherUserEntity voucheruserentity);

    @POST("/ioc/money/voucherUser/deleteBatch")
    Observable<Response<String>> moneyVoucheruserDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/money/voucherUser/find")
    Observable<Response<String>> moneyVoucheruserFind(@Body VoucherUserDTO voucheruserdto);

    @POST("/ioc/money/voucherUser/info/{id}")
    Observable<Response<String>> moneyVoucheruserInfo(@Path("id") int id);

    @POST("/ioc/money/voucherUser/list")
    Observable<Response<String>> moneyVoucheruserList(@Body QueryBody body);

    @POST("/ioc/money/voucherUser/update")
    Observable<Response<String>> moneyVoucheruserUpdate(@Body VoucherUserEntity voucheruserentity);

    @POST("/ioc/monitor/position")
    Observable<Response<String>> monitorPosition(@Body CarObjectId carId);

    @POST("/ioc/recharge/add")
    Observable<Response<String>> rechargeAdd(@Body AmountRechargeEntity amountrechargeentity);

    @POST("/ioc/recharge/delete")
    Observable<Response<String>> rechargeDelete(@Body AmountRechargeEntity amountrechargeentity);

    @POST("/ioc/recharge/deleteBatch")
    Observable<Response<String>> rechargeDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/recharge/find")
    Observable<Response<String>> rechargeFind(@Body AmountRechargeDTO amountrechargedto);

    @POST("/ioc/recharge/info/{id}")
    Observable<Response<String>> rechargeInfo(@Path("id") int id);

    @POST("/ioc/recharge/list")
    Observable<Response<String>> rechargeList(@Body QueryBody body);

    @POST("/ioc/recharge/update")
    Observable<Response<String>> rechargeUpdate(@Body AmountRechargeEntity amountrechargeentity);

    @POST("/ioc/sms/log/list")
    Observable<Response<String>> smsLogList(@Body Object object);

    @POST("/ioc/sys/userDept/add")
    Observable<Response<String>> sysUserdeptAdd(@Body SysUserDeptEntity sysuserdeptentity);

    @POST("/ioc/sys/userDept/delete")
    Observable<Response<String>> sysUserdeptDelete(@Body SysUserDeptEntity sysuserdeptentity);

    @POST("/ioc/sys/userDept/info/{id}")
    Observable<Response<String>> sysUserdeptInfo(@Path("id") int id);

    @POST("/ioc/sys/userDept/list")
    Observable<Response<String>> sysUserdeptList(@Body Object object);

    @POST("/ioc/sys/userDept/update")
    Observable<Response<String>> sysUserdeptUpdate(@Body SysUserDeptEntity sysuserdeptentity);

    @POST("/ioc/sysDict/add")
    Observable<Response<String>> sysDictAdd(@Body SysDictEntity sysdictentity);

    @POST("/ioc/sysDict/delete")
    Observable<Response<String>> sysDictDelete(@Body SysDictEntity sysdictentity);

    @POST("/ioc/sysDict/info/{id}")
    Observable<Response<String>> sysDictInfo(@Path("id") int id);

    @POST("/ioc/sysDict/list")
    Observable<Response<String>> sysDictList(@Body QueryBody body);

    @POST("/ioc/sysDict/type")
    Observable<Response<String>> sysDictType(@Body Object object);

    @POST("/ioc/sysDict/update")
    Observable<Response<String>> sysDictUpdate(@Body SysDictEntity sysdictentity);

    @POST("/ioc/trafficViolation/add")
    Observable<Response<String>> trafficViolationAdd(@Body TrafficViolationEntity trafficviolationentity);

    @POST("/ioc/trafficViolation/delete")
    Observable<Response<String>> trafficViolationDelete(@Body TrafficViolationEntity trafficviolationentity);

    @POST("/ioc/trafficViolation/deleteBatch")
    Observable<Response<String>> trafficViolationDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/trafficViolation/find")
    Observable<Response<String>> trafficViolationFind(@Body TrafficViolationDTO trafficviolationdto);

    @POST("/ioc/trafficViolation/info/{id}")
    Observable<Response<String>> trafficViolationInfo(@Path("id") int id);

    @POST("/ioc/trafficViolation/list")
    Observable<Response<String>> trafficViolationList(@Body QueryBody body);

    @POST("/ioc/trafficViolation/update")
    Observable<Response<String>> trafficViolationUpdate(@Body TrafficViolationEntity trafficviolationentity);

    @POST("/ioc/transaction/add")
    Observable<Response<String>> transactionAdd(@Body TransactionEntity transactionentity);

    @POST("/ioc/transaction/delete")
    Observable<Response<String>> transactionDelete(@Body TransactionEntity transactionentity);

    @POST("/ioc/transaction/deleteBatch")
    Observable<Response<String>> transactionDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/transaction/find")
    Observable<Response<String>> transactionFind(@Body TransactionDTO transactiondto);

    @POST("/ioc/transaction/info/{id}")
    Observable<Response<String>> transactionInfo(@Path("id") int id);

    @POST("/ioc/transaction/list")
    Observable<Response<String>> transactionList(@Body Object object);

    @POST("/ioc/transaction/update")
    Observable<Response<String>> transactionUpdate(@Body TransactionEntity transactionentity);

    @POST("/ioc/user/add")
    Observable<Response<Object>> userAdd(@Body SysUserEntity sysuserentity);

    @POST("/ioc/user/delete")
    Observable<Response<String>> userDelete(@Body SysUserEntity sysuserentity);

    @POST("/ioc/user/find")
    Observable<Response<String>> userFind(@Body SysUserDTO sysuserdto);

    @POST("/ioc/user/get")
    Observable<Response<String>> userGet(@Body SysUserEntity sysuserentity);

    @POST("/ioc/user/info/{id}")
    Observable<Response<String>> userInfo(@Path("id") int id);

    @POST("/ioc/user/list")
    Observable<Response<String>> userList(@Body QueryBody body);

    @POST("/ioc/user/lock")
    Observable<Response<String>> userLock(@Body SysUserEntity sysuserentity);

    @POST("/ioc/user/message/add")
    Observable<Response<String>> userMessageAdd(@Body MessageEntity messageentity);

    @POST("/ioc/user/message/delete")
    Observable<Response<String>> userMessageDelete(@Body MessageEntity messageentity);

    @POST("/ioc/user/message/deleteBatch")
    Observable<Response<String>> userMessageDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/user/message/find")
    Observable<Response<String>> userMessageFind(@Body MessageDTO messagedto);

    @POST("/ioc/user/message/info/{id}")
    Observable<Response<String>> userMessageInfo(@Path("id") int id);

    @POST("/ioc/user/message/list")
    Observable<Response<String>> userMessageList(@Body QueryBody body);

    @POST("/ioc/user/message/update")
    Observable<Response<String>> userMessageUpdate(@Body MessageEntity messageentity);

    @POST("/ioc/user/password/update")
    Observable<Response<String>> userPasswordUpdate(@Body PasswordVO passwordvo);

    @POST("/ioc/user/recommend/add")
    Observable<Response<String>> userRecommendAdd(@Body RecommendEntity recommendentity);

    @POST("/ioc/user/recommend/delete")
    Observable<Response<String>> userRecommendDelete(@Body RecommendEntity recommendentity);

    @POST("/ioc/user/recommend/deleteBatch")
    Observable<Response<String>> userRecommendDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/user/recommend/find")
    Observable<Response<String>> userRecommendFind(@Body RecommendDTO recommenddto);

    @POST("/ioc/user/recommend/info/{id}")
    Observable<Response<String>> userRecommendInfo(@Path("id") int id);

    @POST("/ioc/user/recommend/list")
    Observable<Response<String>> userRecommendList(@Body QueryBody body);

    @POST("/ioc/user/recommend/update")
    Observable<Response<String>> userRecommendUpdate(@Body RecommendEntity recommendentity);

    @POST("/ioc/user/update")
    Observable<Response<String>> userUpdate(@Body SysUserEntity sysuserentity);

}