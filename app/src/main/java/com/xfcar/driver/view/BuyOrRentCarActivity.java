package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.view.adapter.CarSpinnerAdapter;

import java.util.List;

public class BuyOrRentCarActivity extends BaseActivity implements View.OnClickListener {

    private Requester mRequester = new Requester();
    private DataManager mDataManager;
    private Spinner mSpinner;
    private CarSpinnerAdapter mSAdapter;
    private TextView mTvBuy;
    private TextView mTvRent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_or_rent_car);
        initView();
        mRequester.appCarSellModelGetList(mInstance, new ResultCallback<List<RentCarInfoBean>>() {
            @Override
            public void onSuccess(List<RentCarInfoBean> s) {
                mSAdapter.setData(s);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private void initView() {
        findViewById(R.id.rl_return_back).setOnClickListener(this);
        mSpinner = findViewById(R.id.sp_rent_car);
        mTvBuy = findViewById(R.id.tv_buy);
        mTvRent = findViewById(R.id.tv_rent);

        mSAdapter = new CarSpinnerAdapter(this);
        mSpinner.setAdapter(mSAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                RentCarInfoBean bean = (RentCarInfoBean) view.getTag();
                mTvBuy.setText(String.format("全额购买: ￥%s", bean.price));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rl_return_back) {
            finish();
        }
    }
}