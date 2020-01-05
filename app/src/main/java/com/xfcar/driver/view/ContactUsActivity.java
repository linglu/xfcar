package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.mybean.OursBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.ResultCallback;

public class ContactUsActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvCompanyEmail;
    private TextView mTvCompanyTelphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        initView();
        mRequester.appCompanyInfoFind(this, new ResultCallback<OursBean>() {
            @Override
            public void onSuccess(OursBean s) {
                setData(s);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private void setData(OursBean oursBean) {
        mTvCompanyEmail.setText(String.format("%s", oursBean.companyEmail));
        mTvCompanyTelphone.setText(String.format("%s", oursBean.companyTelphone));
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        mTvCompanyEmail = findViewById(R.id.tv_email);
        mTvCompanyTelphone = findViewById(R.id.tv_mobile);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}