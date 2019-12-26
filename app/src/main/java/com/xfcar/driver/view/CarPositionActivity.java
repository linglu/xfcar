package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;

import java.util.List;

public class CarPositionActivity extends BaseActivity implements View.OnClickListener {

    private DataManager mDataManager;
    private Requester mRequester = new Requester();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_position);
        initView();

        mDataManager = new DataManager(this);
        mRequester.getCarInfoByUser(mDataManager.getUserId(), new ResultCallback<List<CarInfoBean>>() {
            @Override
            public void onSuccess(List<CarInfoBean> carInfoBeans) {
                mRequester.monitorPosition(carInfoBeans.get(0).objectid, new ResultCallback<String>() {

                    @Override
                    public void onSuccess(String s) {

                    }

                    @Override
                    public void onFail(String msg) {

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
