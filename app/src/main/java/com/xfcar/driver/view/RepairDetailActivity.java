package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.RepairBean;
import com.xfcar.driver.mvp.BaseActivity;

public class RepairDetailActivity extends BaseActivity implements View.OnClickListener {

    private RepairBean mRepairBean;
    private TextView mTvRepairLocation;
    private TextView mTvRepairTime;
    private TextView mTvRepairFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_detail);
        if (getIntent().getExtras() != null) {
            mRepairBean = getIntent().getExtras().getParcelable("repair_bean");

            initView();
            initData();
        } else {
            finish();
        }
    }

    private void initData() {
        mTvRepairLocation.setText(mRepairBean.maintainLocation);
        mTvRepairTime.setText(mRepairBean.maintainTime);
        mTvRepairFee.setText(mRepairBean.amount);
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);

        mTvRepairLocation = findViewById(R.id.tv_repair_location);
        mTvRepairTime = findViewById(R.id.tv_repair_time);
        mTvRepairFee = findViewById(R.id.tv_repair_fee);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}
