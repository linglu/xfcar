package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfcar.driver.App;
import com.xfcar.driver.R;
import com.xfcar.driver.model.bean.UserEntity;
import com.xfcar.driver.model.viewbean.RechargeItemBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.ImageLoadHelper;
import com.xfcar.driver.view.adapter.RechargeAdapter;

import java.util.List;

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
        mRequester.appRechargeGetRechargeByUser(this, mDataManager.getUserId(), new ResultCallback<List<RechargeItemBean>>() {
            @Override
            public void onSuccess(List<RechargeItemBean> s) {
                mAdapter.setData(s);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private void initView() {

        ImageView iv = findViewById(R.id.iv_header);
        ImageLoadHelper.loadImage(this, getUser().avatar, iv);

        TextView tv = findViewById(R.id.tv_username);
        tv.setText(getUser().username);

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

    }


    private UserEntity getUser() {
        return App.sInstance.mDataManager.getUser();
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