package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.view.adapter.CarInfoSpinnerAdapter;
import com.xfcar.driver.view.fragment.BuyCarFragment;
import com.xfcar.driver.view.fragment.RentCarFragment;
import com.xfcar.driver.view.fragment.ShowCarInfoFragment;

import java.util.List;

import androidx.core.content.ContextCompat;

public class BuyOrRentCarActivity extends BaseActivity implements View.OnClickListener {

    private Spinner mSpinner;
    private CarInfoSpinnerAdapter mSAdapter;
    private TextView mTvBuyPlan;
    private View mVBuyPlan;
    private TextView mTvCarIntro;
    private View mVCarIntro;
    private RentCarInfoBean mRentCarInfoBean;
    private boolean mIsBuyPlan = true;
    private TextView mTvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_or_rent_car);

        mIsBuyPlan = getIntent().getExtras().getBoolean("is_buy_car", false);

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
        mTvPrice = findViewById(R.id.tv_total_price);
        TextView tvTitle = findViewById(R.id.tv_title);
        if (mIsBuyPlan) {
            tvTitle.setText("购车");
        } else {
            tvTitle.setText("租车");
        }

        findViewById(R.id.tv_pre_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        findViewById(R.id.tv_buy_plan).setOnClickListener(this);
        findViewById(R.id.v_buy_plan).setOnClickListener(this);
        findViewById(R.id.tv_car_intro).setOnClickListener(this);
        findViewById(R.id.v_car_intro).setOnClickListener(this);

        mTvBuyPlan = findViewById(R.id.tv_buy_plan);
        mTvBuyPlan.setText(getPlanName());

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
                mTvPrice.setText(String.format("¥%s", mRentCarInfoBean.price));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private String getPlanName() {
        if (mIsBuyPlan) {
            return "购车方案";
        } else {
            return "租车方案";
        }

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

            Bundle bundle = new Bundle();
            bundle.putParcelable("bean", mRentCarInfoBean);

            if (mIsBuyPlan) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                        BuyCarFragment.newInstance(bundle), "buy_plan").commit();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                        RentCarFragment.newInstance(bundle), "rent_plan").commit();
            }

        } else if (id == R.id.tv_car_intro) {
            mTvCarIntro.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            mVCarIntro.setVisibility(View.VISIBLE);
            mTvBuyPlan.setTextColor(ContextCompat.getColor(this, R.color.font_gray3));
            mVBuyPlan.setVisibility(View.INVISIBLE);

            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                    ShowCarInfoFragment.newInstance(null), "car_info").commit();
        }
    }

    private void showBuyAndRent() {

        Bundle bundle = new Bundle();
        bundle.putParcelable("bean", mRentCarInfoBean);
        if (mIsBuyPlan) {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_container,
                    BuyCarFragment.newInstance(bundle), "buy").commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_container,
                    RentCarFragment.newInstance(bundle), "rent").commit();
        }

    }
}