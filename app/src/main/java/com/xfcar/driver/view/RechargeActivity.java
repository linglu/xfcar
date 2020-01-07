package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.FunctionBean;
import com.xfcar.driver.model.viewbean.RechargeItemBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.Utils;
import com.xfcar.driver.view.adapter.CarManAdapter;
import com.xfcar.driver.view.adapter.RechargeAdapter;
import com.xfcar.driver.widget.MaskedImage;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

public class RechargeActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRvRecharge;
    private RechargeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        initView();
        mRequester.appRechargeGetRechargeByUser(this, mDataManager.getUserId(), new ResultCallback<String>() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private void initView() {
        findViewById(R.id.rl_return_back).setOnClickListener(this);
        findViewById(R.id.btn_recharge).setOnClickListener(this);
        mRvRecharge = findViewById(R.id.rv_recharge_item);

        mRvRecharge.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new RechargeAdapter(this);
        mAdapter.setCallback(new Action1<RechargeItemBean>() {
            @Override
            public void call(RechargeItemBean bean) {
                // TODO: 开始充值
            }
        });
        mRvRecharge.setAdapter(mAdapter);
        mAdapter.setData(RechargeItemBean.mock());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rl_return_back) {
            finish();
        } else if (id == R.id.btn_recharge) {

        }
    }
}