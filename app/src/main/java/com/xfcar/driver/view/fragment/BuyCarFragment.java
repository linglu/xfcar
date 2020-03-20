package com.xfcar.driver.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarBizBean;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;
import com.xfcar.driver.mvp.BaseFragment;
import com.xfcar.driver.view.adapter.BuyRentItemAdapter;

import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

public class BuyCarFragment extends BaseFragment implements Action1<String> {

    private RecyclerView mRvList;
    private BuyRentItemAdapter mAdapter;
    private ImageView mIvContainTax;
    private ImageView mIvFirstInsurance;
    private ImageView mIvFactoryEnsure;

    public static BuyCarFragment newInstance(Bundle args) {
        BuyCarFragment fragment = new BuyCarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_buy_car;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return rootView;
    }

    public void init() {

        mRvList = mRootView.findViewById(R.id.rv_list);
        mIvContainTax = mRootView.findViewById(R.id.iv_contain_tax);
        mIvFirstInsurance = mRootView.findViewById(R.id.iv_first_insurance);
        mIvFactoryEnsure = mRootView.findViewById(R.id.iv_factory_ensure);
        mAdapter = new BuyRentItemAdapter(mActivity, true);
        mAdapter.setCallback(new Action1<CarBizBean>() {
            @Override
            public void call(CarBizBean b) {

            }
        });
        mRvList.setAdapter(mAdapter);
        update(getArguments());
    }

    private void setChecked(CarBizBean biz) {

        if (biz.containTax.equals("1")) {
            mIvContainTax.setImageResource(R.mipmap.ic_buy_select);
        } else {
            mIvContainTax.setImageResource(R.mipmap.ic_buy_not_select);
        }

        if (biz.containInsurance.equals("1")) {
            mIvFirstInsurance.setImageResource(R.mipmap.ic_buy_select);
        } else {
            mIvFirstInsurance.setImageResource(R.mipmap.ic_buy_not_select);
        }

        if (biz.containWarranty.equals("1")) {
            mIvFactoryEnsure.setImageResource(R.mipmap.ic_buy_select);
        } else {
            mIvFactoryEnsure.setImageResource(R.mipmap.ic_buy_not_select);
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
