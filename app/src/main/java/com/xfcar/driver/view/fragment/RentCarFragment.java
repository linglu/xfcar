package com.xfcar.driver.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarBizBean;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;
import com.xfcar.driver.model.bean.BuyRentItemBean;
import com.xfcar.driver.mvp.BaseFragment;
import com.xfcar.driver.view.adapter.BuyRentItemAdapter;

import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

public class RentCarFragment extends BaseFragment implements Action1<String> {

    private boolean mIsBuyPlan = true;
    private RecyclerView mRvList;
    private BuyRentItemAdapter mAdapter;
    private ImageView mIvUpPlate;
    private ImageView mIvInsurance;
    private ImageView mIvMaintain;

    public static RentCarFragment newInstance(Bundle args) {
        RentCarFragment fragment = new RentCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_rent_car;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return rootView;
    }

    public void init() {

        RentCarInfoBean rcib = getArguments().getParcelable("bean");
        mRvList = mRootView.findViewById(R.id.rv_list);
        mIvUpPlate = mRootView.findViewById(R.id.iv_up_plate);
        mIvInsurance = mRootView.findViewById(R.id.iv_insurance);
        mIvMaintain = mRootView.findViewById(R.id.iv_maintain);
        mAdapter = new BuyRentItemAdapter(mActivity, false);
        mAdapter.setCallback(new Action1<CarBizBean>() {
            @Override
            public void call(CarBizBean b) {

            }
        });
        mRvList.setAdapter(mAdapter);
        mAdapter.setData(rcib.carBizPlan);

        if (rcib.carBizPlan.size() > 0) {
            setChecked(rcib.carBizPlan.get(0));
        }

        update(getArguments());
    }

    private void setChecked(CarBizBean biz) {

        if (biz.containTax.equals("1")) {
            mIvUpPlate.setImageResource(R.mipmap.ic_buy_select);
        } else {
            mIvUpPlate.setImageResource(R.mipmap.ic_buy_not_select);
        }

        if (biz.containInsurance.equals("1")) {
            mIvInsurance.setImageResource(R.mipmap.ic_buy_select);
        } else {
            mIvInsurance.setImageResource(R.mipmap.ic_buy_not_select);
        }

        if (biz.containWarranty.equals("1")) {
            mIvMaintain.setImageResource(R.mipmap.ic_buy_select);
        } else {
            mIvMaintain.setImageResource(R.mipmap.ic_buy_not_select);
        }
    }

    @Override
    public void call(String s) {

    }

    public void update(Bundle bundle) {
        RentCarInfoBean rcib = bundle.getParcelable("bean");
        mAdapter.setData(rcib.carBizPlan);

        if (rcib.carBizPlan.size() > 0) {
            setChecked(rcib.carBizPlan.get(0));
        }
    }
}
