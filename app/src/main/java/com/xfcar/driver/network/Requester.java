package com.xfcar.driver.network;


import com.xfcar.driver.App;
import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.model.adapterbean.ClaimPayBean;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;
import com.xfcar.driver.model.adapterbean.RepairBean;
import com.xfcar.driver.model.bean.CarObjectId;
import com.xfcar.driver.model.bean.CarSecurityBean;
import com.xfcar.driver.model.bean.ContactDTO;
import com.xfcar.driver.model.bean.EnLogoGram;
import com.xfcar.driver.model.bean.ExchangeGoods;
import com.xfcar.driver.model.bean.IntegralGoodsVo;
import com.xfcar.driver.model.bean.InviteFriendEntity;
import com.xfcar.driver.model.bean.InviteRewardBean;
import com.xfcar.driver.model.bean.MarkBean;
import com.xfcar.driver.model.bean.PasswordVO;
import com.xfcar.driver.model.bean.QRCodeBean;
import com.xfcar.driver.model.bean.ShortRentEntity;
import com.xfcar.driver.model.bean.SysUserEntity;
import com.xfcar.driver.model.bean.UserEntity;
import com.xfcar.driver.model.bean.Command;
import com.xfcar.driver.model.mybean.InviteRecordQueryBean;
import com.xfcar.driver.model.mybean.OursBean;
import com.xfcar.driver.model.viewbean.ScoreProductBean;
import com.xfcar.driver.model.viewbean.ScoreTypeBean;
import com.xfcar.driver.network.converter.FastjsonConverterFactory;
import com.xfcar.driver.utils.DialogLoading;
import com.xfcar.driver.utils.L;

import java.io.IOException;
import java.util.List;

import androidx.fragment.app.FragmentActivity;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
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

    public void appUserUpdatePwByUser(FragmentActivity act,
                                      int userId, String oldPassword, String newPassword, String mobile,
                                      final ResultCallback<String> callback) {
        showLoadingDialog(act);
        PasswordVO pvo = new PasswordVO();
        pvo.userId = userId;
        pvo.oldPassword = oldPassword;
        pvo.newPassword = newPassword;
        pvo.mobile = mobile;

        service.appUserUpdatePwByUser(pvo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appCarLeasebackOneKey(FragmentActivity act, int userId, final ResultCallback<String> callback) {
        showLoadingDialog(act);
        service.appCarLeasebackOneKey(new UserEntity(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void monitorPosition(FragmentActivity act, String objectId, final ResultCallback<String> callback) {
        showLoadingDialog(act);
        service.appMonitorPosition(new CarObjectId(objectId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appCarSellModelGetList(FragmentActivity act, final ResultCallback<List<RentCarInfoBean>> callback) {
        showLoadingDialog(act);
        service.appCarSellModelGetList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<List<RentCarInfoBean>>() {

                    @Override
                    public void onSuccess(List<RentCarInfoBean> o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appGetPaymentSource(FragmentActivity act, final ResultCallback<QRCodeBean> callback) {
        showLoadingDialog(act);
        service.appGetPaymentSource(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<QRCodeBean>() {

                    @Override
                    public void onSuccess(QRCodeBean o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appCarShortRentAdd(FragmentActivity act, int userId, String carNo, String date, final ResultCallback<String> callback) {
        showLoadingDialog(act);
        ShortRentEntity entity = new ShortRentEntity();
        entity.userId = userId;
        entity.carNo = carNo;
        entity.subletDate = date;
        service.appCarShortRentAdd(entity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appCarGetSecurityInfo(FragmentActivity act, int userId, final ResultCallback<CarSecurityBean> callback) {
        showLoadingDialog(act);
        service.appCarGetSecurityInfo(new UserEntity(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<CarSecurityBean>() {

                    @Override
                    public void onSuccess(CarSecurityBean o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void carOperateCommand(FragmentActivity act, String company, String carNo, String macid, String carCmd,
                                  final ResultCallback<String> callback) {
        showLoadingDialog(act);
        Command cmd = new Command(company, carNo, macid, carCmd);
        service.appCarOperateCommand(cmd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appCompanyInfoFind(FragmentActivity act, final ResultCallback<OursBean> callback) {
        showLoadingDialog(act);
        EnLogoGram elg = new EnLogoGram();
        service.appCompanyInfoFind(elg)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<OursBean>() {

                    @Override
                    public void onSuccess(OursBean o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appDocumentInfo(FragmentActivity act, final ResultCallback<String> callback) {
        showLoadingDialog(act);
        MarkBean markBean = new MarkBean("LawRegulations");
        service.appDocumentInfo(markBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appUserIntegralLogList(int userId, final ResultCallback<List<ScoreTypeBean>> callback) {
        service.appUserIntegralLogList(new UserEntity(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<List<ScoreTypeBean>>() {

                    @Override
                    public void onSuccess(List<ScoreTypeBean> o) {
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        callback.onFail(msg);
                    }
                });
    }

    public void appUserInviteFriendAdd(FragmentActivity act, int userId, String userName,
                                       String inviteFriendName, String invitePhone, final ResultCallback<String> callback) {
        showLoadingDialog(act);
        InviteFriendEntity ife = new InviteFriendEntity();
        ife.userId = userId;
        ife.username = userName;
        ife.inviteFriendName = inviteFriendName;
        ife.invitePhone = invitePhone;
        service.appUserInviteFriendAdd(ife)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appIntegralGoodsFindByGoodsTag(int userId, final ResultCallback<List<ScoreProductBean>> callback) {
        IntegralGoodsVo integralGoodsVo = new IntegralGoodsVo();
        integralGoodsVo.userId = userId;
        integralGoodsVo.goodsTag = "RentFree";
        service.appIntegralGoodsFindByGoodsTag(integralGoodsVo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<List<ScoreProductBean>>() {

                    @Override
                    public void onSuccess(List<ScoreProductBean> o) {
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        callback.onFail(msg);
                    }
                });
    }

    public void appUserIntegralGoodsOrderExchange(FragmentActivity act, int userId, String goodsName,
                                                  final ResultCallback<String> callback) {
        showLoadingDialog(act);
        ExchangeGoods eg = new ExchangeGoods();
        eg.goodsName = goodsName;
        eg.userId = String.valueOf(userId);
        service.appUserIntegralGoodsOrderExchange(eg)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appUserInviteFriendByMonth(FragmentActivity act, int userId, final ResultCallback<String> callback) {
        showLoadingDialog(act);
        service.appUserInviteFriendByMonth(new InviteRecordQueryBean(String.valueOf(userId), "2020-01-01"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appUserInviteFriendInviteReward(FragmentActivity act, int userId, final ResultCallback<List<InviteRewardBean>> callback) {
        showLoadingDialog(act);
        service.appUserInviteFriendInviteReward(new UserEntity(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<List<InviteRewardBean>>() {

                    @Override
                    public void onSuccess(List<InviteRewardBean> o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appRechargeGetRechargeByUser(FragmentActivity act, int userId, final ResultCallback<String> callback) {
        showLoadingDialog(act);
        service.appRechargeGetRechargeByUser(new UserEntity(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appCarGetCarInfoByUser(FragmentActivity act, int userId, final ResultCallback<CarInfoBean> callback) {
        showLoadingDialog(act);
        service.appCarGetCarInfoByUser(new UserEntity(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<CarInfoBean>() {

                    @Override
                    public void onSuccess(CarInfoBean o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void getMaintainByUser(FragmentActivity act, int userId, final ResultCallback<List<RepairBean>> callback) {
        showLoadingDialog(act);
        service.appCarMaintainGetMaintainByUser(new UserEntity(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<List<RepairBean>>() {

                    @Override
                    public void onSuccess(List<RepairBean> o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appCarInsuranceGetInsuranceByUser(FragmentActivity act, int userId, final ResultCallback<List<ClaimPayBean>> callback) {
        showLoadingDialog(act);
        service.appCarInsuranceGetInsuranceByUser(new UserEntity(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<List<ClaimPayBean>>() {

                    @Override
                    public void onSuccess(List<ClaimPayBean> o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void logout(FragmentActivity act, final ResultCallback<String> callback) {
        showLoadingDialog(act);
        service.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String o) {
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void appUserUpdateContactByUser(FragmentActivity act, int userId, String name, String mobile, final ResultCallback<String> callback) {
        showLoadingDialog(act);
        ContactDTO entity = new ContactDTO();
        entity.userId = userId;
        entity.contactMobile = mobile;
        entity.contactName = name;
//      entity.username = name;
//      entity.mobile = mobile;

        service.appUserUpdateContactByUser(entity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {
                    @Override
                    public void onSuccess(String s) {
                        dismissLoadingDialog();
                        callback.onSuccess(s);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
        }

    // 注册
    public void register(FragmentActivity act, String name, String mobile, String pwd, int userType, final ResultCallback<Object> callback) {
        showLoadingDialog(act);
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
                        dismissLoadingDialog();
                        callback.onSuccess(o);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void login(FragmentActivity act, String mobile, String code, final ResultCallback<SysUserEntity> callback) {
        showLoadingDialog(act);
        service.login(mobile, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<SysUserEntity>() {

                    @Override
                    public void onSuccess(SysUserEntity res) {
                        dismissLoadingDialog();
                        callback.onSuccess(res);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    public void getVerifyCode(FragmentActivity act, String mobile, final ResultCallback<String> callback) {
        showLoadingDialog(act);
        service.verifyCode(mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new NetworkSubscriber<String>() {

                    @Override
                    public void onSuccess(String res) {
                        dismissLoadingDialog();
                        callback.onSuccess(res);
                    }

                    @Override
                    public void onFail(String msg) {
                        dismissLoadingDialog();
                        callback.onFail(msg);
                    }
                });
    }

    private void showLoadingDialog(FragmentActivity act) {
        DialogLoading.show(act.getSupportFragmentManager());
    }

    private void dismissLoadingDialog() {
        DialogLoading.dismiss();
    }
}
