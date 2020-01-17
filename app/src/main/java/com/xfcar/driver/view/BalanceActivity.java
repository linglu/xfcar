package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.BalanceTypeBean;
import com.xfcar.driver.model.viewbean.RewardItemBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.view.adapter.BalanceAdapter;
import com.xfcar.driver.view.adapter.MonthSpinnerAdapter;
import com.xfcar.driver.view.adapter.RewardAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BalanceActivity extends BaseActivity implements View.OnClickListener {

    private Spinner mSpinner;
    private RecyclerView mRvBalanceItem;
    private RecyclerView mRvRewardRecords;
    private BalanceAdapter mBalanceAdapter;
    private RewardAdapter mRewardAdapter;
    private MonthSpinnerAdapter mSAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);

        mSpinner = findViewById(R.id.sp_month);
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

        mSAdapter = new MonthSpinnerAdapter(this);
        mSpinner.setAdapter(mSAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                L.i("on item selected");
//                RentCarInfoBean mRentCarInfoBean = (RentCarInfoBean) view.getTag();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        List<String> s = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            s.add(String.format("2020-1-%s", String.valueOf(i)));
        }
        mSAdapter.setData(s);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}