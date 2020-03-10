package com.xfcar.driver.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseFragment;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

public class ShowCarInfoFragment extends BaseFragment implements Action1<String>, View.OnClickListener {

    private TextView mTvCarConfig;
    private View mVCarConfig;
    private TextView mTvCarPic;
    private View mVCarPic;
    private View mVConfigDetail;
    private RecyclerView mRvPics;

    public static ShowCarInfoFragment newInstance(Bundle args) {
        ShowCarInfoFragment fragment = new ShowCarInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_car_info;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return rootView;
    }

    public void init() {
        mTvCarConfig = mRootView.findViewById(R.id.tv_car_config);
        mVCarConfig = mRootView.findViewById(R.id.v_car_config);
        mTvCarPic = mRootView.findViewById(R.id.tv_car_pic);
        mVCarPic = mRootView.findViewById(R.id.v_car_pic);

        mRootView.findViewById(R.id.tv_car_config).setOnClickListener(this);
        mRootView.findViewById(R.id.v_car_config).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_car_pic).setOnClickListener(this);
        mRootView.findViewById(R.id.v_car_pic).setOnClickListener(this);

        mVConfigDetail = mRootView.findViewById(R.id.ll_config_detail);
        mRvPics = mRootView.findViewById(R.id.rv_car_pics);

    }

    @Override
    public void call(String s) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_car_config) {
            mTvCarConfig.setTextColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));
            mVCarConfig.setVisibility(View.VISIBLE);
            mTvCarPic.setTextColor(ContextCompat.getColor(mActivity, R.color.font_gray3));
            mVCarPic.setVisibility(View.INVISIBLE);

            mVConfigDetail.setVisibility(View.VISIBLE);
            mRvPics.setVisibility(View.INVISIBLE);

        } else if (id == R.id.tv_car_pic) {
            mTvCarPic.setTextColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));
            mVCarPic.setVisibility(View.VISIBLE);
            mTvCarConfig.setTextColor(ContextCompat.getColor(mActivity, R.color.font_gray3));
            mVCarConfig.setVisibility(View.INVISIBLE);

            mVConfigDetail.setVisibility(View.INVISIBLE);
            mRvPics.setVisibility(View.VISIBLE);
        }
    }
}
