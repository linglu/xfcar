package com.xfcar.driver.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.model.bean.Command;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.Utils;

import java.util.List;

public class CarOperateActivity extends BaseActivity implements View.OnClickListener {


    private DataManager mDataManager;
    private Requester mRequester = new Requester();
    private String mCmd;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_operate);

        if (getIntent() != null && getIntent().getExtras() != null) {
            mCmd = getIntent().getExtras().getString("CMD_OPERATE", "");
            if (TextUtils.isEmpty(mCmd)) {
                return;
            }
        }

        initView();
        mDataManager = new DataManager(this);
        mRequester.getCarInfoByUser(mInstance, mDataManager.getUserId(), new ResultCallback<CarInfoBean>() {
            @Override
            public void onSuccess(CarInfoBean carInfo) {
                mRequester.carOperateCommand(mInstance,
                        carInfo.company, carInfo.carNo,
                        Utils.getIMEI(CarOperateActivity.this),
                        mCmd,
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
        mTvTitle = findViewById(R.id.tv_title);

        if (mCmd.equals(Command.SN_SAFEON)) {
            mTvTitle.setText("一键上锁");
        } else if (mCmd.equals(Command.SN_SAFEOFF)) {
            mTvTitle.setText("一键开门");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}
