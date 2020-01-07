package com.xfcar.driver.view;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.SafeHandler;
import com.xfcar.driver.utils.Utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author linky
 */
public class ModifyPwdActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtOldPwd;
    private EditText mEtNewPwd;
    private EditText mEtMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pwd);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);

        mEtOldPwd = findViewById(R.id.et_old_pwd);
        mEtNewPwd = findViewById(R.id.et_new_pwd);
        mEtMobile = findViewById(R.id.et_mobile);
        findViewById(R.id.btn_submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        } else if (id == R.id.btn_submit) {
            //点击获取验证码
            String mobile = mEtMobile.getText().toString();
            String newPwd = mEtNewPwd.getText().toString();
            String oldPwd = mEtOldPwd.getText().toString();
            if (TextUtils.isEmpty(mobile)) {
                toastMsg(R.string.account_hint_name);
                mEtMobile.requestFocus();
            } else if (!Utils.isPhoneValid(mobile)) {
                toastMsg(R.string.account_tel_pattern_wrong);
                mEtMobile.setText("");
                mEtMobile.requestFocus();
            } else if (newPwd.length() < 6) {
                toastMsg("新密码太短");
                mEtNewPwd.setText("");
                mEtNewPwd.requestFocus();
            } else if (oldPwd.length() < 6) {
                toastMsg("旧密码太短");
                mEtOldPwd.setText("");
                mEtOldPwd.requestFocus();
            } else {
                mRequester.appUserUpdatePwByUser(this, mDataManager.getUserId(),
                        oldPwd, newPwd, mobile, new ResultCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        toastMsg("修改成功");
                        finish();
                    }

                    @Override
                    public void onFail(String msg) {
                        toastMsg(msg);
                    }
                });
            }
        }
    }
}
