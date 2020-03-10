package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseActivity;

public class AccountActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        findViewById(R.id.tv_modify_mobile).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        } else if (id == R.id.tv_modify_mobile) {
            startActivity(ModifyMobileActivity.class);
        } else if (id == R.id.tv_modify_pwd) {
            startActivity(ModifyPwdActivity.class);
        }
    }
}