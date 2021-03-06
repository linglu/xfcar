package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.RewardItemBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.view.adapter.RewardAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DedutPercentActivity extends BaseActivity implements View.OnClickListener {


    private Spinner mSpinner;
    private TextView mTvTotalAmount;
    private RecyclerView mRvRewardRecords;
    private RewardAdapter mRewardAdapter;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dedut_percent);
        initView();
        String title = getIntent().getExtras().getString("title");
        mTvTitle.setText(title);
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        findViewById(R.id.rl_zonglan).setOnClickListener(this);
        findViewById(R.id.rl_tixian).setOnClickListener(this);
        mSpinner = findViewById(R.id.sp_month);
        mTvTitle = findViewById(R.id.tv_title);
        mTvTotalAmount = findViewById(R.id.tv_total_amount);
        mRvRewardRecords = findViewById(R.id.rv_reward_records);

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
        } else if (id == R.id.rl_tixian) {

        } else if (id == R.id.rl_zonglan) {
            startActivity(BalanceActivity.class);
        }
    }
}