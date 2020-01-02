package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.model.bean.Command;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.Utils;

public class OneKeyLockActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_operate);
        initView();


        mRequester.appCarGetCarInfoByUser(mInstance, mDataManager.getUserId(), new ResultCallback<CarInfoBean>() {
            @Override
            public void onSuccess(CarInfoBean carInfo) {
                mRequester.carOperateCommand(mInstance,
                        carInfo.company, carInfo.carNo,
                        Utils.getIMEI(OneKeyLockActivity.this),
                        Command.SN_SAFEON,
                        new ResultCallback<String>() {

                    @Override
                    public void onSuccess(String s) {

                    }

                    @Override
                    public void onFail(String msg) {
                        toastMsg(msg);
                    }
                });
            }

            @Override
            public void onFail(String msg) {
                toastMsg(msg);

            }
        });
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}
