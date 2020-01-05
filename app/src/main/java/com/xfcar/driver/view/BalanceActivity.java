package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.BalanceTypeBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.view.adapter.BalanceAdapter;

public class BalanceActivity extends BaseActivity implements View.OnClickListener {

    private Spinner mSpMonth;
    private RecyclerView mRvBalanceItem;
    private RecyclerView mRvRewardRecords;
    private BalanceAdapter mBalanceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);

        mSpMonth = findViewById(R.id.sp_month);
        mRvBalanceItem = findViewById(R.id.rv_balance_item);
        mRvRewardRecords = findViewById(R.id.rv_reward_records);

        mBalanceAdapter = new BalanceAdapter(this);
        mRvBalanceItem.setLayoutManager(new GridLayoutManager(this, 3));
        mRvBalanceItem.setAdapter(mBalanceAdapter);
        mBalanceAdapter.setData(BalanceTypeBean.mock());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}