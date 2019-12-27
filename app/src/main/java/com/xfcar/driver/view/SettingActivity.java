package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private Requester mRequester = new Requester();
    private DataManager mDataManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        findViewById(R.id.tv_account).setOnClickListener(this);
        findViewById(R.id.tv_emergency).setOnClickListener(this);
        findViewById(R.id.tv_contact_us).setOnClickListener(this);
        findViewById(R.id.tv_about).setOnClickListener(this);

        findViewById(R.id.bt_logout).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               final DataManager dataManager = new DataManager(SettingActivity.this);
                mRequester.logout(new ResultCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        toastMsg("退出登录成功");
                        dataManager.setUserId(null);
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
        } else if (id == R.id.tv_account) {
            startActivity(AccountActivity.class);
        } else if (id == R.id.tv_emergency) {
            startActivity(EmergencyActivity.class);
        } else if (id == R.id.tv_contact_us) {
            startActivity(ContactUsActivity.class);
        } else if (id == R.id.tv_about) {
            startActivity(AboutActivity.class);
        }
    }
}