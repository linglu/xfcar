package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        findViewById(R.id.ll_account).setOnClickListener(this);
        findViewById(R.id.ll_emergency).setOnClickListener(this);
        findViewById(R.id.ll_contact_us).setOnClickListener(this);
        findViewById(R.id.ll_about).setOnClickListener(this);

        findViewById(R.id.tv_logout).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

                mRequester.logout(mInstance, new ResultCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        toastMsg("退出登录成功");
                        mDataManager.setUserId(0);
                        startActivity(LoginActivity.class);
                        finish();
                    }

                    @Override
                    public void onFail(String msg) {
                        toastMsg(msg);
                    }
                });
           }
       });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        } else if (id == R.id.ll_account) {
            startActivity(ModifyPwdActivity.class);
        } else if (id == R.id.ll_emergency) {
            startActivity(EmergencyActivity.class);
        } else if (id == R.id.ll_contact_us) {
            startActivity(ContactUsActivity.class);
        } else if (id == R.id.ll_about) {
            startActivity(AboutActivity.class);
        }
    }
}