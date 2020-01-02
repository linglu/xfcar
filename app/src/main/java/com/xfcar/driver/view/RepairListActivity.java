package com.xfcar.driver.view;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.ClaimPayBean;
import com.xfcar.driver.model.adapterbean.RepairBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.view.adapter.RepairAdapter;
import com.xfcar.driver.view.adapter.RepairAdapter;

import java.util.List;

import rx.functions.Action1;

public class RepairListActivity extends BaseActivity implements View.OnClickListener {



    private RecyclerView mRvRepair;
    private RepairAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);
        initView();

        mRequester.getMaintainByUser(mInstance, mDataManager.getUserId(), new ResultCallback<List<RepairBean>>() {
            @Override
            public void onSuccess(List<RepairBean> s) {
                mAdapter.setData(s);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        mRvRepair = findViewById(R.id.rv_repair);
        mRvRepair.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RepairAdapter(this);
        mRvRepair.setAdapter(mAdapter);
        mAdapter.setCallback(new Action1<RepairBean>() {
            @Override
            public void call(RepairBean bean) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("repair_bean", bean);
                startActivity(RepairDetailActivity.class, bundle);
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
