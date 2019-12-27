package com.xfcar.driver.view;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.xfcar.driver.R;
import com.xfcar.driver.model.bean.Command;
import com.xfcar.driver.model.viewbean.FunctionBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.view.adapter.FunctionAdapter;

import rx.functions.Action1;

public class MyWalletActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRvFunction;
    private FunctionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        mRvFunction = findViewById(R.id.rv_function);
        mRvFunction.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new FunctionAdapter(this);
        mAdapter.setCallback(new Action1<FunctionBean>() {
            @Override
            public void call(FunctionBean appBean) {

                switch (appBean.name) {
                    case "余额":
                        startActivity(BalanceActivity.class);
                        break;
                    case "收款":
                        startActivity(ReceiptActivity.class);
                        break;
                    case "查询":
                        startActivity(SearchInfoActivity.class);
                        break;
                    case "转单提成":
                        startActivity(DedutPercentActivity.class);
                        break;
                    case "推荐奖励":
                        startActivity(ShareRewardActivity.class);
                        break;
                }
            }});

        mRvFunction.setAdapter(mAdapter);
        mAdapter.setData(FunctionBean.mockWallet());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}