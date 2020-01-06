package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.ClaimPayBean;
import com.xfcar.driver.mvp.BaseActivity;

public class ClaimPayDetailActivity extends BaseActivity implements View.OnClickListener {

    private ClaimPayBean mClaimPayBean;
    private TextView mTvAccidentLocation;
    private TextView mTvClaimTime;
    private TextView mTvClaimFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_pay_detail);
        if (getIntent().getExtras() != null) {
            mClaimPayBean = getIntent().getExtras().getParcelable("claim_pay_bean");

            initView();
            initData();
        } else {
            finish();
        }
    }

    private void initData() {
        mTvAccidentLocation.setText(mClaimPayBean.repairLocation);
        mTvClaimTime.setText(mClaimPayBean.accidentTime);
        mTvClaimFee.setText(mClaimPayBean.amount);
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);

        mTvAccidentLocation = findViewById(R.id.tv_accident_location);
        mTvClaimTime = findViewById(R.id.tv_claim_time);
        mTvClaimFee = findViewById(R.id.tv_claim_fee);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}
