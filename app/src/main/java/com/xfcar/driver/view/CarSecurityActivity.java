package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.model.bean.CarSecurityBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;

public class CarSecurityActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvEngineNo;
    private TextView mTvMobile;
    private TextView mTvCarInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_security);
        initView();

        mRequester.appCarGetSecurityInfo(mInstance, mDataManager.getUserId(), new ResultCallback<CarSecurityBean>() {
            @Override
            public void onSuccess(CarSecurityBean bean) {
                mTvEngineNo.setText(String.format("%s", bean.engineNo));
                mTvMobile.setText(String.format("%s", bean.mobile));
                mTvCarInfo.setText(String.format("%s", bean.carInfo));
            }

            @Override
            public void onFail(String msg) {
                toastMsg(msg);
            }
        });
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);;
        mTvEngineNo = findViewById(R.id.tv_engine_no);
        mTvMobile = findViewById(R.id.tv_mobile);
        mTvCarInfo = findViewById(R.id.tv_car_info);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}
