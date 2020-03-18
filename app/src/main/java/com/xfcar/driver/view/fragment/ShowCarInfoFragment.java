package com.xfcar.driver.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;
import com.xfcar.driver.mvp.BaseFragment;
import com.xfcar.driver.view.adapter.ViewPagerAdapterStr;

import java.util.ArrayList;
import java.util.List;

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
    private int[] ps = {
            R.id.tv_param_1,
            R.id.tv_param_2,
            R.id.tv_param_3,
            R.id.tv_param_4,
            R.id.tv_param_5,
            R.id.tv_param_6
    };

    private TextView[] tvs = new TextView[6];
    private ViewPagerAdapterStr adapter;

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


        RentCarInfoBean rcib = getArguments().getParcelable("bean");

        mTvCarConfig = mRootView.findViewById(R.id.tv_car_config);
        mVCarConfig = mRootView.findViewById(R.id.v_car_config);
        mTvCarPic = mRootView.findViewById(R.id.tv_car_pic);
        mVCarPic = mRootView.findViewById(R.id.v_car_pic);

        String[] is = rcib.introduction.split("  ");
        for (int i = 0; i < is.length; i++) {
            tvs[i] = mRootView.findViewById(ps[i]);
            String label = is[i].split("：")[0];
            String value = is[i].split("：")[1];
            tvs[i].setText(String.format("%s : %s", label, value));
        }

        mRootView.findViewById(R.id.tv_car_config).setOnClickListener(this);
        mRootView.findViewById(R.id.v_car_config).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_car_pic).setOnClickListener(this);
        mRootView.findViewById(R.id.v_car_pic).setOnClickListener(this);

        mVConfigDetail = mRootView.findViewById(R.id.ll_config_detail);
        mRvPics = mRootView.findViewById(R.id.rv_car_pics);
        adapter = new ViewPagerAdapterStr(mActivity);
        mRvPics.setAdapter(adapter);

        List<String> pics = new ArrayList<>();
        pics.add(rcib.picture1);
        pics.add(rcib.picture2);
        pics.add(rcib.picture3);
        adapter.setData(pics);
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

    public void update(Bundle bundle) {

        RentCarInfoBean rcib = bundle.getParcelable("bean");

        String[] is = rcib.introduction.split("  ");
        for (int i = 0; i < is.length; i++) {
            String label = is[i].split("：")[0];
            String value = is[i].split("：")[1];
            tvs[i].setText(String.format("%s : %s", label, value));
        }

        List<String> pics = new ArrayList<>();
        pics.add(rcib.picture1);
        pics.add(rcib.picture2);
        pics.add(rcib.picture3);
        adapter.setData(pics);
    }
}
