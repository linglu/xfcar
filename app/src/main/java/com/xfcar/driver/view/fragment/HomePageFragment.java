package com.xfcar.driver.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.FunctionBean;
import com.xfcar.driver.mvp.BaseFragment;
import com.xfcar.driver.view.CarManageActivity;
import com.xfcar.driver.view.adapter.FunctionAdapter;

import rx.functions.Action1;

/**
 * @author Linky
 */
public class HomePageFragment extends BaseFragment {

    private RecyclerView mRvFunction;
    private FunctionAdapter mAdapter;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return rootView;
    }

    private void init() {
        mRvFunction = mRootView.findViewById(R.id.rv_function);
        mRvFunction.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mAdapter = new FunctionAdapter(mActivity);
        mAdapter.setCallback(new Action1<FunctionBean>() {
            @Override
            public void call(FunctionBean appBean) {
                // 车辆管理
                if (appBean.id == 2) {
                    startActivity(CarManageActivity.class);
                }
            }
        });
        mRvFunction.setAdapter(mAdapter);
        mAdapter.setData(FunctionBean.mockFuncBeans());
    }

}
