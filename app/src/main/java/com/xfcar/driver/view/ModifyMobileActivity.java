package com.xfcar.driver.view;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.SafeHandler;
import com.xfcar.driver.utils.Utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author linky
 */
public class ModifyMobileActivity extends BaseActivity implements View.OnClickListener {

    private Requester mRequester = new Requester();
    private DataManager mDataManager;
    private EditText mEtMobile;
    private EditText mEtCode;
    private Button mBtGetCaptcha;
    private String mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_mobile);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);

        mEtMobile = findViewById(R.id.et_new_mobile);
        mEtCode = findViewById(R.id.et_code);
        mBtGetCaptcha = findViewById(R.id.btn_getCode);
        findViewById(R.id.btn_getCode).setOnClickListener(this);
        findViewById(R.id.btn_modify).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        } else if (id == R.id.btn_getCode) {
            //点击获取验证码
            mPhoneNumber = mEtMobile.getText().toString();
            if (TextUtils.isEmpty(mPhoneNumber)) {
                toastMsg(R.string.account_hint_name);
                mEtMobile.requestFocus();
            } else if (!Utils.isPhoneValid(mPhoneNumber)) {
                toastMsg(R.string.account_tel_pattern_wrong);
                mEtMobile.setText("");
                mEtMobile.requestFocus();
            } else {
                mRequester.appUserUpdatebyuser(mInstance, mEtMobile.getText().toString(), mEtMobile.getText().toString(), 1,
                        new ResultCallback<String>() {
                            @Override
                            public void onSuccess(String o) {
                                toastMsg("修改成功");
                                mDataManager.setMobile(mPhoneNumber);
                            }

                            @Override
                            public void onFail(String msg) {
                                toastMsg(msg);
                            }
                        });
            }
        } else if (id == R.id.btn_modify) {
        }
    }

    private final static class CustomHandler extends SafeHandler<ModifyMobileActivity> {

        public CustomHandler(ModifyMobileActivity objs) {
            super(objs);
        }

        @Override
        public void handlerMessageAction(Message msg) {
            ModifyMobileActivity helper = getObj();
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
