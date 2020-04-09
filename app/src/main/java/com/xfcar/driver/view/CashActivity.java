package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.BalanceTypeBean;
import com.xfcar.driver.model.viewbean.RewardItemBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.view.adapter.BalanceAdapter;
import com.xfcar.driver.view.adapter.RewardAdapter;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CashActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvBalance;
    private TextView mTvCanCash;
    private TextView mTvCash;
    private RecyclerView mRvCashRecords;

    private RewardAdapter mRewardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);
        initView();

        mRequester.carDrawMoneyAdd(this, mDataManager.getUserId(), mDataManager.getUser().username, "", new ResultCallback<String>() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onFail(String msg) {

            }
        });

    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);

        mTvBalance = findViewById(R.id.tv_balance);
        mTvCanCash = findViewById(R.id.tv_can_cash);
        mTvCash = findViewById(R.id.tv_cash);
        mRvCashRecords = findViewById(R.id.rv_cash_records);

        mRewardAdapter = new RewardAdapter(this);
        mRvCashRecords.setLayoutManager(new LinearLayoutManager(this));
        mRvCashRecords.setAdapter(mRewardAdapter);
        mRewardAdapter.setData(RewardItemBean.mock());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }

}