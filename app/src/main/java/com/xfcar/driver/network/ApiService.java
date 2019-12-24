package com.xfcar.driver.network;

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
    @POST("/ioc/login/mobile")
//    Observable<Response<String>> login(@Field("mobile") String mobile, @Field("smsCode") String smsCode);
    Observable<Response<String>> login(@FieldMap Map<String, String> map);

    @POST("/ioc/alarmRecord/add")
    Observable<Response> alarmRecordAdd(@Body AlarmRecordEntity alarmrecordentity);

    @POST("/ioc/alarmRecord/delete")
    Observable<Response> alarmRecordDelete(@Body AlarmRecordEntity alarmrecordentity);

    @POST("/ioc/alarmRecord/deleteBatch")
    Observable<Response> alarmRecordDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/alarmRecord/find")
    Observable<Response> alarmRecordFind(@Body AlarmRecordDTO alarmrecorddto);

    @POST("/ioc/alarmRecord/info/{id}")
    Observable<Response> alarmRecordInfo(@Path("id") int id);

    @POST("/ioc/alarmRecord/list")
    Observable<Response> alarmRecordList(@Body QueryBody body);

    @POST("/ioc/alarmRecord/update")
    Observable<Response> alarmRecordUpdate(@Body AlarmRecordEntity alarmrecordentity);

    @POST("/ioc/call/car/add")
    Observable<Response> callCarAdd(@Body CallCarEntity callcarentity);

    @POST("/ioc/call/car/delete")
    Observable<Response> callCarDelete(@Body CallCarEntity callcarentity);

    @POST("/ioc/call/car/deleteBatch")
    Observable<Response> callCarDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/call/car/find")
    Observable<Response> callCarFind(@Body CallCarDTO callcardto);

    @POST("/ioc/call/car/info/{id}")
    Observable<Response> callCarInfo(@Path("id") int id);

    @POST("/ioc/call/car/list")
    Observable<Response> callCarList(@Body QueryBody body);

    @POST("/ioc/call/car/update")
    Observable<Response> callCarUpdate(@Body CallCarEntity callcarentity);

    @POST("/ioc/car/add")
    Observable<Response> carAdd(@Body CarInfoEntity carinfoentity);

    @POST("/ioc/car/amountUse/add")
    Observable<Response> carAmountuseAdd(@Body AmountUseEntity amountuseentity);

    @POST("/ioc/car/amountUse/delete")
    Observable<Response> carAmountuseDelete(@Body AmountUseEntity amountuseentity);

    @POST("/ioc/car/amountUse/deleteBatch")
    Observable<Response> carAmountuseDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/car/amountUse/find")
    Observable<Response> carAmountuseFind(@Body AmountUseDTO amountusedto);

    @POST("/ioc/car/amountUse/info/{id}")
    Observable<Response> carAmountuseInfo(@Path("id") int id);

    @POST("/ioc/car/amountUse/list")
    Observable<Response> carAmountuseList(@Body QueryBody body);

    @POST("/ioc/car/amountUse/update")
    Observable<Response> carAmountuseUpdate(@Body AmountUseEntity amountuseentity);

    @POST("/ioc/car/delete")
    Observable<Response> carDelete(@Body CarInfoEntity carinfoentity);

    @POST("/ioc/car/deleteBatch")
    Observable<Response> carDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/car/find")
    Observable<Response> carFind(@Body CarInfoDTO carinfodto);

    @POST("/ioc/car/info/{id}")
    Observable<Response> carInfo(@Path("id") int id);

    @POST("/ioc/car/insurance/add")
    Observable<Response> carInsuranceAdd(@Body CarInsuranceEntity carinsuranceentity);

    @POST("/ioc/car/insurance/delete")
    Observable<Response> carInsuranceDelete(@Body CarInsuranceEntity carinsuranceentity);

    @POST("/ioc/car/insurance/deleteBatch")
    Observable<Response> carInsuranceDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/car/insurance/find")
    Observable<Response> carInsuranceFind(@Body CarInsuranceDTO carinsurancedto);

    @POST("/ioc/car/insurance/info/{id}")
    Observable<Response> carInsuranceInfo(@Path("id") int id);

    @POST("/ioc/car/insurance/list")
    Observable<Response> carInsuranceList(@Body QueryBody body);

    @POST("/ioc/car/insurance/update")
    Observable<Response> carInsuranceUpdate(@Body CarInsuranceEntity carinsuranceentity);

    @POST("/ioc/car/leaseback/add")
    Observable<Response> carLeasebackAdd(@Body LeasebackEntity leasebackentity);

    @POST("/ioc/car/leaseback/find")
    Observable<Response> carLeasebackFind(@Body LeasebackDTO leasebackdto);

    @POST("/ioc/car/leaseback/info/{id}")
    Observable<Response> carLeasebackInfo(@Path("id") int id);

    @POST("/ioc/car/leaseback/list")
    Observable<Response> carLeasebackList(@Body QueryBody body);

    @POST("/ioc/car/leaseback/update")
    Observable<Response> carLeasebackUpdate(@Body LeasebackEntity leasebackentity);

    @POST("/ioc/car/list")
    Observable<Response> carList(@Body QueryBody body);

    @POST("/ioc/car/maintain/add")
    Observable<Response> carMaintainAdd(@Body CarMaintainEntity carmaintainentity);

    @POST("/ioc/car/maintain/delete")
    Observable<Response> carMaintainDelete(@Body CarMaintainEntity carmaintainentity);

    @POST("/ioc/car/maintain/deleteBatch")
    Observable<Response> carMaintainDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/car/maintain/find")
    Observable<Response> carMaintainFind(@Body CarMaintainDTO carmaintaindto);

    @POST("/ioc/car/maintain/info/{id}")
    Observable<Response> carMaintainInfo(@Path("id") int id);

    @POST("/ioc/car/maintain/list")
    Observable<Response> carMaintainList(@Body QueryBody body);

    @POST("/ioc/car/maintain/update")
    Observable<Response> carMaintainUpdate(@Body CarMaintainEntity carmaintainentity);

    @POST("/ioc/car/operate/bound")
    Observable<Response> carOperateBound(@Body Command command);

    @POST("/ioc/car/operate/command")
    Observable<Response> carOperateCommand(@Body Command command);

    @POST("/ioc/car/operate/equipmentList")
    Observable<Response> carOperateEquipmentlist();

    @POST("/ioc/car/operate/find")
    Observable<Response> carOperateFind(@Body CarOperateDTO caroperatedto);

    @POST("/ioc/car/operate/info/{id}")
    Observable<Response> carOperateInfo(@Path("id") int id);

    @POST("/ioc/car/operate/list")
    Observable<Response> carOperateList(@Body QueryBody body);

    @POST("/ioc/car/operate/unBound")
    Observable<Response> carOperateUnbound(@Body Command command);

    @POST("/ioc/car/operate/updateBoundInfo")
    Observable<Response> carOperateUpdateboundinfo(@Body Command command);

    @POST("/ioc/car/update")
    Observable<Response> carUpdate(@Body CarInfoEntity carinfoentity);

    @GET("/code/{type}")
    Observable<Response> code(@Path("type") int type);

    @POST("/ioc/coupon/add")
    Observable<Response> couponAdd(@Body CouponEntity couponentity);

    @POST("/ioc/coupon/delete")
    Observable<Response> couponDelete(@Body CouponEntity couponentity);

    @POST("/ioc/coupon/deleteBatch")
    Observable<Response> couponDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/coupon/find")
    Observable<Response> couponFind(@Body CouponDTO coupondto);

    @POST("/ioc/coupon/info/{id}")
    Observable<Response> couponInfo(@Path("id") int id);

    @POST("/ioc/coupon/list")
    Observable<Response> couponList(@Body QueryBody body);

    @POST("/ioc/coupon/update")
    Observable<Response> couponUpdate(@Body CouponEntity couponentity);

    @POST("/ioc/couponUser/add")
    Observable<Response> couponUserAdd(@Body CouponUserEntity couponuserentity);

    @POST("/ioc/couponUser/delete")
    Observable<Response> couponUserDelete(@Body CouponUserEntity couponuserentity);

    @POST("/ioc/couponUser/deleteBatch")
    Observable<Response> couponUserDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/couponUser/find")
    Observable<Response> couponUserFind(@Body CouponUserDTO couponuserdto);

    @POST("/ioc/couponUser/info/{id}")
    Observable<Response> couponUserInfo(@Path("id") int id);

    @POST("/ioc/couponUser/list")
    Observable<Response> couponUserList(@Body QueryBody body);

    @POST("/ioc/couponUser/update")
    Observable<Response> couponUserUpdate(@Body CouponUserEntity couponuserentity);

    @POST("/ioc/discount/add")
    Observable<Response> discountAdd(@Body DiscountInfoEntity discountinfoentity);

    @POST("/ioc/discount/delete")
    Observable<Response> discountDelete(@Body DiscountInfoEntity discountinfoentity);

    @POST("/ioc/discount/deleteBatch")
    Observable<Response> discountDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/discount/find")
    Observable<Response> discountFind(@Body DiscountInfoDTO discountinfodto);

    @POST("/ioc/discount/info/{id}")
    Observable<Response> discountInfo(@Path("id") int id);

    @POST("/ioc/discount/list")
    Observable<Response> discountList(@Body QueryBody body);

    @POST("/ioc/discount/update")
    Observable<Response> discountUpdate(@Body DiscountInfoEntity discountinfoentity);

    @POST("/ioc/discountBuy/add")
    Observable<Response> discountBuyAdd(@Body DiscountBuyEntity discountbuyentity);

    @POST("/ioc/discountBuy/delete")
    Observable<Response> discountBuyDelete(@Body DiscountBuyEntity discountbuyentity);

    @POST("/ioc/discountBuy/deleteBatch")
    Observable<Response> discountBuyDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/discountBuy/find")
    Observable<Response> discountBuyFind(@Body DiscountBuyDTO discountbuydto);

    @POST("/ioc/discountBuy/info/{id}")
    Observable<Response> discountBuyInfo(@Path("id") int id);

    @POST("/ioc/discountBuy/list")
    Observable<Response> discountBuyList(@Body QueryBody body);

    @POST("/ioc/discountBuy/update")
    Observable<Response> discountBuyUpdate(@Body DiscountBuyEntity discountbuyentity);

    @POST("/ioc/discountItem/add")
    Observable<Response> discountItemAdd(@Body DiscountItemEntity discountitementity);

    @POST("/ioc/discountItem/delete")
    Observable<Response> discountItemDelete(@Body DiscountItemEntity discountitementity);

    @POST("/ioc/discountItem/deleteBatch")
    Observable<Response> discountItemDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/discountItem/find")
    Observable<Response> discountItemFind(@Body DiscountItemDTO discountitemdto);

    @POST("/ioc/discountItem/info/{id}")
    Observable<Response> discountItemInfo(@Path("id") int id);

    @POST("/ioc/discountItem/list")
    Observable<Response> discountItemList(@Body QueryBody body);

    @POST("/ioc/discountItem/update")
    Observable<Response> discountItemUpdate(@Body DiscountItemEntity discountitementity);

    @POST("/ioc/money/voucher/add")
    Observable<Response> moneyVoucherAdd(@Body VoucherEntity voucherentity);

    @POST("/ioc/money/voucher/delete")
    Observable<Response> moneyVoucherDelete(@Body VoucherEntity voucherentity);

    @POST("/ioc/money/voucher/deleteBatch")
    Observable<Response> moneyVoucherDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/money/voucher/find")
    Observable<Response> moneyVoucherFind(@Body VoucherDTO voucherdto);

    @POST("/ioc/money/voucher/info/{id}")
    Observable<Response> moneyVoucherInfo(@Path("id") int id);

    @POST("/ioc/money/voucher/list")
    Observable<Response> moneyVoucherList(@Body QueryBody body);

    @POST("/ioc/money/voucher/update")
    Observable<Response> moneyVoucherUpdate(@Body VoucherEntity voucherentity);

    @POST("/ioc/money/voucherUser/add")
    Observable<Response> moneyVoucheruserAdd(@Body VoucherUserEntity voucheruserentity);

    @POST("/ioc/money/voucherUser/delete")
    Observable<Response> moneyVoucheruserDelete(@Body VoucherUserEntity voucheruserentity);

    @POST("/ioc/money/voucherUser/deleteBatch")
    Observable<Response> moneyVoucheruserDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/money/voucherUser/find")
    Observable<Response> moneyVoucheruserFind(@Body VoucherUserDTO voucheruserdto);

    @POST("/ioc/money/voucherUser/info/{id}")
    Observable<Response> moneyVoucheruserInfo(@Path("id") int id);

    @POST("/ioc/money/voucherUser/list")
    Observable<Response> moneyVoucheruserList(@Body QueryBody body);

    @POST("/ioc/money/voucherUser/update")
    Observable<Response> moneyVoucheruserUpdate(@Body VoucherUserEntity voucheruserentity);

    @POST("/ioc/monitor/position")
    Observable<Response> monitorPosition(@Body Object object);

    @POST("/ioc/recharge/add")
    Observable<Response> rechargeAdd(@Body AmountRechargeEntity amountrechargeentity);

    @POST("/ioc/recharge/delete")
    Observable<Response> rechargeDelete(@Body AmountRechargeEntity amountrechargeentity);

    @POST("/ioc/recharge/deleteBatch")
    Observable<Response> rechargeDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/recharge/find")
    Observable<Response> rechargeFind(@Body AmountRechargeDTO amountrechargedto);

    @POST("/ioc/recharge/info/{id}")
    Observable<Response> rechargeInfo(@Path("id") int id);

    @POST("/ioc/recharge/list")
    Observable<Response> rechargeList(@Body QueryBody body);

    @POST("/ioc/recharge/update")
    Observable<Response> rechargeUpdate(@Body AmountRechargeEntity amountrechargeentity);

    @POST("/ioc/sms/log/list")
    Observable<Response> smsLogList(@Body Object object);

    @POST("/ioc/sys/userDept/add")
    Observable<Response> sysUserdeptAdd(@Body SysUserDeptEntity sysuserdeptentity);

    @POST("/ioc/sys/userDept/delete")
    Observable<Response> sysUserdeptDelete(@Body SysUserDeptEntity sysuserdeptentity);

    @POST("/ioc/sys/userDept/info/{id}")
    Observable<Response> sysUserdeptInfo(@Path("id") int id);

    @POST("/ioc/sys/userDept/list")
    Observable<Response> sysUserdeptList(@Body Object object);

    @POST("/ioc/sys/userDept/update")
    Observable<Response> sysUserdeptUpdate(@Body SysUserDeptEntity sysuserdeptentity);

    @POST("/ioc/sysDict/add")
    Observable<Response> sysDictAdd(@Body SysDictEntity sysdictentity);

    @POST("/ioc/sysDict/delete")
    Observable<Response> sysDictDelete(@Body SysDictEntity sysdictentity);

    @POST("/ioc/sysDict/info/{id}")
    Observable<Response> sysDictInfo(@Path("id") int id);

    @POST("/ioc/sysDict/list")
    Observable<Response> sysDictList(@Body QueryBody body);

    @POST("/ioc/sysDict/type")
    Observable<Response> sysDictType(@Body Object object);

    @POST("/ioc/sysDict/update")
    Observable<Response> sysDictUpdate(@Body SysDictEntity sysdictentity);

    @POST("/ioc/trafficViolation/add")
    Observable<Response> trafficViolationAdd(@Body TrafficViolationEntity trafficviolationentity);

    @POST("/ioc/trafficViolation/delete")
    Observable<Response> trafficViolationDelete(@Body TrafficViolationEntity trafficviolationentity);

    @POST("/ioc/trafficViolation/deleteBatch")
    Observable<Response> trafficViolationDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/trafficViolation/find")
    Observable<Response> trafficViolationFind(@Body TrafficViolationDTO trafficviolationdto);

    @POST("/ioc/trafficViolation/info/{id}")
    Observable<Response> trafficViolationInfo(@Path("id") int id);

    @POST("/ioc/trafficViolation/list")
    Observable<Response> trafficViolationList(@Body QueryBody body);

    @POST("/ioc/trafficViolation/update")
    Observable<Response> trafficViolationUpdate(@Body TrafficViolationEntity trafficviolationentity);

    @POST("/ioc/transaction/add")
    Observable<Response> transactionAdd(@Body TransactionEntity transactionentity);

    @POST("/ioc/transaction/delete")
    Observable<Response> transactionDelete(@Body TransactionEntity transactionentity);

    @POST("/ioc/transaction/deleteBatch")
    Observable<Response> transactionDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/transaction/find")
    Observable<Response> transactionFind(@Body TransactionDTO transactiondto);

    @POST("/ioc/transaction/info/{id}")
    Observable<Response> transactionInfo(@Path("id") int id);

    @POST("/ioc/transaction/list")
    Observable<Response> transactionList(@Body Object object);

    @POST("/ioc/transaction/update")
    Observable<Response> transactionUpdate(@Body TransactionEntity transactionentity);

    @POST("/ioc/user/add")
    Observable<Response<Object>> userAdd(@Body SysUserEntity sysuserentity);

    @POST("/ioc/user/delete")
    Observable<Response> userDelete(@Body SysUserEntity sysuserentity);

    @POST("/ioc/user/find")
    Observable<Response> userFind(@Body SysUserDTO sysuserdto);

    @POST("/ioc/user/get")
    Observable<Response> userGet(@Body SysUserEntity sysuserentity);

    @POST("/ioc/user/info/{id}")
    Observable<Response> userInfo(@Path("id") int id);

    @POST("/ioc/user/list")
    Observable<Response> userList(@Body QueryBody body);

    @POST("/ioc/user/lock")
    Observable<Response> userLock(@Body SysUserEntity sysuserentity);

    @POST("/ioc/user/message/add")
    Observable<Response> userMessageAdd(@Body MessageEntity messageentity);

    @POST("/ioc/user/message/delete")
    Observable<Response> userMessageDelete(@Body MessageEntity messageentity);

    @POST("/ioc/user/message/deleteBatch")
    Observable<Response> userMessageDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/user/message/find")
    Observable<Response> userMessageFind(@Body MessageDTO messagedto);

    @POST("/ioc/user/message/info/{id}")
    Observable<Response> userMessageInfo(@Path("id") int id);

    @POST("/ioc/user/message/list")
    Observable<Response> userMessageList(@Body QueryBody body);

    @POST("/ioc/user/message/update")
    Observable<Response> userMessageUpdate(@Body MessageEntity messageentity);

    @POST("/ioc/user/password/update")
    Observable<Response> userPasswordUpdate(@Body PasswordVO passwordvo);

    @POST("/ioc/user/recommend/add")
    Observable<Response> userRecommendAdd(@Body RecommendEntity recommendentity);

    @POST("/ioc/user/recommend/delete")
    Observable<Response> userRecommendDelete(@Body RecommendEntity recommendentity);

    @POST("/ioc/user/recommend/deleteBatch")
    Observable<Response> userRecommendDeletebatch(@Body List<Integer> ids);

    @POST("/ioc/user/recommend/find")
    Observable<Response> userRecommendFind(@Body RecommendDTO recommenddto);

    @POST("/ioc/user/recommend/info/{id}")
    Observable<Response> userRecommendInfo(@Path("id") int id);

    @POST("/ioc/user/recommend/list")
    Observable<Response> userRecommendList(@Body QueryBody body);

    @POST("/ioc/user/recommend/update")
    Observable<Response> userRecommendUpdate(@Body RecommendEntity recommendentity);

    @POST("/ioc/user/update")
    Observable<Response> userUpdate(@Body SysUserEntity sysuserentity);

}