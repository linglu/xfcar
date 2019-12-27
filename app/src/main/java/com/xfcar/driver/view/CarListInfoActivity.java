package com.xfcar.driver.view;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.view.adapter.CarInfoAdapter;

import java.util.List;

import rx.functions.Action1;

public class CarListInfoActivity extends BaseActivity implements View.OnClickListener {

    private Requester mRequester = new Requester();
    private DataManager mDataManager;
    private RecyclerView mRvCarInfo;
    private CarInfoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_pay);
        initView();

        mDataManager = new DataManager(this);
        mRequester.getCarInfoByUser(mDataManager.getUserId(), new ResultCallback<List<CarInfoBean>>() {
            @Override
            public void onSuccess(List<CarInfoBean> s) {
                mAdapter.setData(s);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        mRvCarInfo = findViewById(R.id.rv_claim_pay);
        mRvCarInfo.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CarInfoAdapter(this);
        mRvCarInfo.setAdapter(mAdapter);
        mAdapter.setCallback(new Action1<CarInfoBean>() {
            @Override
            public void call(CarInfoBean bean) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("car_info_bean", bean);
                startActivity(CarInfoActivity.class, bundle);
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
