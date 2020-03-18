package com.xfcar.driver.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.wangpeiyuan.cycleviewpager2.CycleViewPager2;
import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.view.adapter.CarInfoSpinnerAdapter;
import com.xfcar.driver.view.adapter.ViewPagerAdapterStr;
import com.xfcar.driver.view.fragment.BuyCarFragment;
import com.xfcar.driver.view.fragment.RentCarFragment;
import com.xfcar.driver.view.fragment.ShowCarInfoFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

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
    private Handler mHandler = new Handler();
    private CycleViewPager2 mCvpPager;
    private ViewPagerAdapterStr mVpAdapter;

    private BuyCarFragment mBuyCarFragment;
    private ShowCarInfoFragment mShowCarInfoFragment;
    private RentCarFragment mRentCarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_or_rent_car);

        mIsBuyPlan = getIntent().getExtras().getBoolean("is_buy_car", false);

        initView();
        mRequester.appCarSellModelGetList(mInstance, new ResultCallback<List<RentCarInfoBean>>() {
//        mRequester.appCarSellModelGetList(mInstance, mDataManager.getUserId(), new ResultCallback<List<RentCarInfoBean>>() {
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
        mCvpPager = findViewById(R.id.cvp_pager);
        mCvpPager.setAutoTurning(5000);
        mCvpPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        mVpAdapter = new ViewPagerAdapterStr(this);
        mCvpPager.setAdapter(mVpAdapter);

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

                String type = "30";
                if (mIsBuyPlan) {
                    type = "20";
                }

                String curDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                mRequester.carShortRentAdd(BuyOrRentCarActivity.this, mDataManager.getUserId(),
                        type, curDate, new ResultCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                toastMsg("预约成功，稍后会有工作人员联系");
                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 1000);
                            }

                            @Override
                            public void onFail(String msg) {
                                toastMsg("提交失败");
                            }
                        });
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
//                showBuyAndRent();

                List<String> pics = new ArrayList<>();
                pics.add(mRentCarInfoBean.picture1);
                pics.add(mRentCarInfoBean.picture2);
                pics.add(mRentCarInfoBean.picture3);
                mVpAdapter.setData(pics);

                Bundle bundle = new Bundle();
                bundle.putParcelable("bean", mRentCarInfoBean);
                if (mShowCarInfoFragment != null) {
                    mShowCarInfoFragment.update(bundle);
                }
                if (mIsBuyPlan) {
                    if (mBuyCarFragment != null) {
                        mBuyCarFragment.update(bundle);
                    }
                } else {
                    if (mRentCarFragment != null) {
                        mRentCarFragment.update(bundle);
                    }
                }
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
                if (mBuyCarFragment == null) {
                    mBuyCarFragment = BuyCarFragment.newInstance(bundle);
                } else {
                    mBuyCarFragment.update(bundle);
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                        mBuyCarFragment, "buy_plan").commit();
            } else {
                if (mRentCarFragment == null) {
                    mRentCarFragment = RentCarFragment.newInstance(bundle);
                } else {
                    mRentCarFragment.update(bundle);
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                        mRentCarFragment, "rent_plan").commit();
            }

        } else if (id == R.id.tv_car_intro) {
            mTvCarIntro.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            mVCarIntro.setVisibility(View.VISIBLE);
            mTvBuyPlan.setTextColor(ContextCompat.getColor(this, R.color.font_gray3));
            mVBuyPlan.setVisibility(View.INVISIBLE);

            Bundle bundle = new Bundle();
            bundle.putParcelable("bean", mRentCarInfoBean);

            if (mShowCarInfoFragment == null) {
                mShowCarInfoFragment = ShowCarInfoFragment.newInstance(bundle);
            } else {
                mShowCarInfoFragment.update(bundle);
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                    mShowCarInfoFragment, "car_info").commit();
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

        List<String> pics = new ArrayList<>();
        pics.add(mRentCarInfoBean.picture1);
        pics.add(mRentCarInfoBean.picture2);
        pics.add(mRentCarInfoBean.picture3);
        mVpAdapter.setData(pics);
    }
}