package com.xfcar.driver.view;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.ClaimPayBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.view.adapter.ClaimAdapter;

import java.util.List;

import rx.functions.Action1;

public class ClaimPayListActivity extends BaseActivity implements View.OnClickListener {

    private Requester mRequester = new Requester();
    private DataManager mDataManager;
    private RecyclerView mRvClaim;
    private ClaimAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_pay);
        initView();

        mDataManager = new DataManager(this);
        mRequester.getInsuranceByUser(mDataManager.getUserId(), new ResultCallback<List<ClaimPayBean>>() {
            @Override
            public void onSuccess(List<ClaimPayBean> s) {
                mAdapter.setData(s);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        mRvClaim = findViewById(R.id.rv_claim_pay);
        mRvClaim.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ClaimAdapter(this);
        mRvClaim.setAdapter(mAdapter);
        mAdapter.setCallback(new Action1<ClaimPayBean>() {
            @Override
            public void call(ClaimPayBean claimPayBean) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("claim_pay_bean", claimPayBean);
                startActivity(ClaimPayDetailActivity.class, bundle);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}
