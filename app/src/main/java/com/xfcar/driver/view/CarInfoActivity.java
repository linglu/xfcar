package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;

public class CarInfoActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvCarNo;
    private TextView mTvModelNo;
    private TextView mTvEngineNo;
    private TextView mTvElectricQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        initView();
        initData(mDataManager.getCarInfo());
    }

    private void initData(CarInfoBean carInfoBean) {
        mTvCarNo.setText(carInfoBean.carNo);
        mTvModelNo.setText(String.format("%s%s",carInfoBean.brand, carInfoBean.modelNo));
        mTvEngineNo.setText(carInfoBean.engineNo);
        mTvElectricQuantity.setText(carInfoBean.status);
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        mTvCarNo = findViewById(R.id.tv_car_no);
        mTvModelNo = findViewById(R.id.tv_model_no);
        mTvEngineNo = findViewById(R.id.tv_engine_no);
        mTvElectricQuantity = findViewById(R.id.tv_electric_quantity);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}
