package com.xfcar.driver.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.Utils;

public class InviteActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtInviteName;
    private TextView mTvNumber;
    private EditText mEtInvitedName;
    private EditText mEtInvitedMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        initView();
        mTvNumber.setText(String.format("XF%s", mDataManager.getUserId()));
    }

    private void initView() {
        findViewById(R.id.rl_return_back).setOnClickListener(this);
        mEtInviteName = findViewById(R.id.et_invite_name);
        mTvNumber = findViewById(R.id.tv_work_code);
        mEtInvitedName = findViewById(R.id.et_invited_name);
        mEtInvitedMobile = findViewById(R.id.et_invited_mobile);
        findViewById(R.id.btn_submit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rl_return_back) {
            finish();
        } else if (id == R.id.btn_submit) {
            String inviteName = mEtInviteName.getText().toString();
            String invitedName = mEtInvitedName.getText().toString();

            String mobile = mEtInvitedMobile.getText().toString();

            if (TextUtils.isEmpty(inviteName.trim())) {
                toastMsg("请输入邀请人用户名");
            } else if (inviteName.length() > 50) {
                toastMsg("邀请人用户名过长");
            } else if (TextUtils.isEmpty(invitedName.trim())) {
                toastMsg("请输入被邀请人用户名");
            } else if (invitedName.length() > 50) {
                toastMsg("被邀请人用户名过长");
            } else if (!Utils.isPhoneValid(mobile.trim())) {
                toastMsg(R.string.account_tel_pattern_wrong);
                mEtInvitedMobile.setText("");
                mEtInvitedMobile.requestFocus();
            } else {
                mRequester.appUserInviteFriendAdd(this, mDataManager.getUserId(), inviteName,
                        invitedName, mobile, new ResultCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        toastMsg("邀请成功");
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