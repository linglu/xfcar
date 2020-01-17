package com.xfcar.driver.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.xfcar.driver.R;
import com.xfcar.driver.model.bean.LoginResponse;
import com.xfcar.driver.model.bean.SysUserEntity;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.SafeHandler;
import com.xfcar.driver.utils.ToastUtils;
import com.xfcar.driver.utils.Utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Linky
 */
public class LoginActivity extends BaseActivity {

    private EditText mEtUserName;
    private EditText mEtVerifyCode;
    private String mPhoneNumber;
    private Button mBtGetCaptcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtUserName = findViewById(R.id.et_username_login);
        mEtVerifyCode = findViewById(R.id.et_code_login);
        mBtGetCaptcha = findViewById(R.id.btn_getCode);

        mEtUserName.setText(mDataManager.getMobile());

        findViewById(R.id.btn_getCode).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //点击获取验证码
                mPhoneNumber = mEtUserName.getText().toString();      //解决无法发送验证码的问题 2015-11-4 by wp.nine
                if (TextUtils.isEmpty(mPhoneNumber)) {
                    toastMsg(R.string.account_hint_name);
                    mEtUserName.requestFocus();
                } else if (!Utils.isPhoneValid(mPhoneNumber)) {
                    toastMsg(R.string.account_tel_pattern_wrong);
                    mEtUserName.setText("");
                    mEtUserName.requestFocus();
                } else {
                    mRequester.getVerifyCode(mInstance, mPhoneNumber, new ResultCallback<String>() {
                        @Override
                        public void onSuccess(String s) {
                            mEtVerifyCode.setText(s);
                            changeGetCodeBtnContent();
                        }

                        @Override
                        public void onFail(String msg) {
                            ToastUtils.show(LoginActivity.this, msg);
                        }
                    });
                }
            }
        });

        findViewById(R.id.tv_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(RegisterActivity.class, 1001);
            }
        });

        findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = mEtUserName.getText().toString();
                String code = mEtVerifyCode.getText().toString();


                if (TextUtils.isEmpty(username)) {
                    toastMsg(R.string.account_hint_name);
                    mEtUserName.setText("");
                    mEtUserName.requestFocus();
                    return;
                }

                if (!Utils.isPhoneValid(username)) {
                    toastMsg(R.string.account_tel_pattern_wrong);
                    mEtUserName.setText("");
                    mEtUserName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(code)) {
                    toastMsg(R.string.account_hint_code_wrong);
                    mEtVerifyCode.setText("");
                    mEtVerifyCode.requestFocus();
                    return;
                }

                executeLogin(username, code);
            }
        });

        findViewById(R.id.tv_agreement_register).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            if (data != null && data.getExtras() != null) {
                String mobile = data.getExtras().getString("mobile");
                mEtUserName.setText(mobile);
            }
        }
    }

    private void executeLogin(String username, String code) {
        mRequester.login(mInstance, username, code, new ResultCallback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse s) {
                toastMsg("登录成功");
                mDataManager.setUserId(s.user.userId);
                mDataManager.setMobile(s.user.mobile);
                mDataManager.setUser(JSON.toJSONString(s.user));
                mDataManager.setCarInfo(JSON.toJSONString(s.carInfo));
                mDataManager.setToken(s.XF_TOKEN);
                mDataManager.setLock(s.lock);
                setResult(RESULT_OK);
                finish();
            }

            @Override
            public void onFail(String msg) {
                if ("401".equals(msg)) {

                } else {
                    toastMsg(msg);
                }
            }
        });
    }


    private final static class CustomHandler extends SafeHandler<LoginActivity> {

        public CustomHandler(LoginActivity objs) {
            super(objs);
        }

        @Override
        public void handlerMessageAction(Message msg) {
            LoginActivity helper = getObj();
            if (helper.mCanGetCode) {
                helper.mBtGetCaptcha.setText(helper.mGetCodeBtnTitle);
                helper.mBtGetCaptcha.setBackgroundResource(R.drawable.shape_button_green);
                helper.mBtGetCaptcha.setEnabled(true);
                helper.mTimer.cancel();
                helper.DurRetGetCode = 60;
            } else {
                helper.mBtGetCaptcha.setBackgroundResource(R.drawable.shape_button_green);
                helper.mBtGetCaptcha.setEnabled(false);
                String s = String.format(helper.getResources().getString(R.string.account_msg_send_again), helper.DurRetGetCode--);
                helper.mBtGetCaptcha.setText(s);
                if (helper.DurRetGetCode <= 0) {
                    helper.mCanGetCode = true;
                }
            }
        }
    }

    private int DurRetGetCode = 60;
    private boolean mCanGetCode = true;
    private Timer mTimer;
    private CustomHandler mHandler = new CustomHandler(this);
    private String mGetCodeBtnTitle;

    private void changeGetCodeBtnContent() {
        mGetCodeBtnTitle = mBtGetCaptcha.getText().toString();
        if (mCanGetCode) {
            mCanGetCode = false;
            mTimer = new Timer();
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message msg = new Message();
                    mHandler.sendMessage(msg);
                }
            }, 100, 1000);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mTimer != null) {
            mBtGetCaptcha.setText(mGetCodeBtnTitle);
            mBtGetCaptcha.setBackgroundResource(R.drawable.shape_button_green);
            mBtGetCaptcha.setEnabled(true);
            mTimer.cancel();
            DurRetGetCode = 120;
        }
    }
}
