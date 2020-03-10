package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.BalanceTypeBean;
import com.xfcar.driver.model.viewbean.RewardItemBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.view.adapter.BalanceAdapter;
import com.xfcar.driver.view.adapter.RewardAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BalanceActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRvBalanceItem;
    private RecyclerView mRvRewardRecords;
    private BalanceAdapter mBalanceAdapter;
    private RewardAdapter mRewardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        mRequester.userIntegrallogCounts(this, mDataManager.getUserId(), new ResultCallback<String>() {
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

        mRvBalanceItem = findViewById(R.id.rv_balance_item);
        mRvRewardRecords = findViewById(R.id.rv_reward_records);

        mBalanceAdapter = new BalanceAdapter(this);
        mRvBalanceItem.setLayoutManager(new GridLayoutManager(this, 3));
        mRvBalanceItem.setAdapter(mBalanceAdapter);
        mBalanceAdapter.setData(BalanceTypeBean.mock(mDataManager.getUser()));

        mRewardAdapter = new RewardAdapter(this);
        mRvRewardRecords.setLayoutManager(new LinearLayoutManager(this));
        mRvRewardRecords.setAdapter(mRewardAdapter);
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