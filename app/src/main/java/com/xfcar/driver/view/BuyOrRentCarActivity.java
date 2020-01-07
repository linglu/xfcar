package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.view.adapter.CarInfoSpinnerAdapter;
import com.xfcar.driver.view.fragment.BuyCarFragment;
import com.xfcar.driver.view.fragment.ShowCarInfoFragment;

import java.util.List;

public class BuyOrRentCarActivity extends BaseActivity implements View.OnClickListener {

    private Spinner mSpinner;
    private CarInfoSpinnerAdapter mSAdapter;
    private TextView mTvBuyPlan;
    private View mVBuyPlan;
    private TextView mTvCarIntro;
    private View mVCarIntro;
    private RentCarInfoBean mRentCarInfoBean;
    private boolean mIsBuyPlanShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_or_rent_car);
        initView();
        mRequester.appCarSellModelGetList(mInstance, new ResultCallback<List<RentCarInfoBean>>() {
            @Override
            public void onSuccess(List<RentCarInfoBean> s) {
                mSAdapter.setData(s);

                if (s != null && s.size() > 0) {
                    mRentCarInfoBean = s.get(0);
                    showBuyAndRent();
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private void initView() {
        findViewById(R.id.rl_return_back).setOnClickListener(this);
        mSpinner = findViewById(R.id.sp_rent_car);

        findViewById(R.id.tv_buy_plan).setOnClickListener(this);
        findViewById(R.id.v_buy_plan).setOnClickListener(this);
        findViewById(R.id.tv_car_intro).setOnClickListener(this);
        findViewById(R.id.v_car_intro).setOnClickListener(this);

        mTvBuyPlan = findViewById(R.id.tv_buy_plan);
        mVBuyPlan = findViewById(R.id.v_buy_plan);
        mTvCarIntro = findViewById(R.id.tv_car_intro);
        mVCarIntro = findViewById(R.id.v_car_intro);

        mSAdapter = new CarInfoSpinnerAdapter(this);
        mSpinner.setAdapter(mSAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                L.i("on item selected");
                mRentCarInfoBean = (RentCarInfoBean) view.getTag();
//                mTvBuy.setText(String.format("全额购买: ￥%s", bean.price));
                if (mIsBuyPlanShow) {
                        Fragment fragment = getSupportFragmentManager().findFragmentByTag("buy_or_rent");
                    if (fragment != null) {
                        ((BuyCarFragment) fragment).update(mRentCarInfoBean);
                    }
                } else {
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag("car_info");
                    if (fragment != null) {
//                        ((ShowCarInfoFragment) fragment).update(mRentCarInfoBean);
                    }
                }
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
        } else if (id == R.id.tv_buy_plan) {
            mTvBuyPlan.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            mVBuyPlan.setVisibility(View.VISIBLE);
            mTvCarIntro.setTextColor(ContextCompat.getColor(this, R.color.font_gray3));
            mVCarIntro.setVisibility(View.INVISIBLE);
            mIsBuyPlanShow = true;

            Bundle args = new Bundle();
            args.putParcelable("car_info_bean", mRentCarInfoBean);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                    BuyCarFragment.newInstance(args), "buy_or_rent").commit();

        } else if (id == R.id.tv_car_intro) {
            mTvCarIntro.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            mVCarIntro.setVisibility(View.VISIBLE);
            mTvBuyPlan.setTextColor(ContextCompat.getColor(this, R.color.font_gray3));
            mVBuyPlan.setVisibility(View.INVISIBLE);
            mIsBuyPlanShow = false;

            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                    ShowCarInfoFragment.newInstance(null), "car_info").commit();
        }
    }

    private void showBuyAndRent() {
        Bundle args = new Bundle();
        args.putParcelable("car_info_bean", mRentCarInfoBean);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container,
                BuyCarFragment.newInstance(args), "buy_or_rent").commit();
    }
}