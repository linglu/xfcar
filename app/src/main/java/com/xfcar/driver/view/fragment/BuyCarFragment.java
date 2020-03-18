package com.xfcar.driver.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;
import com.xfcar.driver.model.bean.BuyRentItemBean;
import com.xfcar.driver.mvp.BaseFragment;
import com.xfcar.driver.view.adapter.BuyRentItemAdapter;

import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

public class BuyCarFragment extends BaseFragment implements Action1<String> {

    private RecyclerView mRvList;
    private BuyRentItemAdapter mAdapter;

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
        mAdapter = new BuyRentItemAdapter(mActivity, true);
        mAdapter.setCallback(new Action1<BuyRentItemBean>() {
            @Override
            public void call(BuyRentItemBean b) {

            }
        });
        mRvList.setAdapter(mAdapter);
        mAdapter.setData(BuyRentItemBean.mockBuy());
    }

    @Override
    public void call(String s) {

    }

    public void update(Bundle bundle) {

    }
}
