package com.xfcar.driver.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;
import com.xfcar.driver.mvp.BaseFragment;

import rx.functions.Action1;

public class BuyCarFragment extends BaseFragment implements Action1<String> {

    private TextView mTvPrice;
    private TextView mTvBuy;

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
        mTvPrice = mRootView.findViewById(R.id.tv_price);
        mTvBuy = mRootView.findViewById(R.id.tv_buy);

        if (getArguments() != null) {
            RentCarInfoBean bean = getArguments().getParcelable("car_info_bean");
            update(bean);
        }
    }

    public void update(RentCarInfoBean bean) {
        mTvPrice.setText(String.format("￥%s", bean.price));
    }

    @Override
    public void call(String s) {

    }
}
