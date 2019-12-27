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
import com.xfcar.driver.view.MyMessageActivity;
import com.xfcar.driver.view.MyScoreActivity;
import com.xfcar.driver.view.MyWalletActivity;
import com.xfcar.driver.view.SettingActivity;
import com.xfcar.driver.view.adapter.FunctionAdapter;

import rx.functions.Action1;

/**
 * @author linky
 */
public class MineFragment extends BaseFragment {

    private RecyclerView mRvFunction;
    private FunctionAdapter mAdapter;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_mine;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return rootView;
    }

    private void init() {
        mRvFunction = mRootView.findViewById(R.id.rv_function);
        mRvFunction.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mAdapter = new FunctionAdapter(mActivity);
        mAdapter.setCallback(new Action1<FunctionBean>() {
            @Override
            public void call(FunctionBean appBean) {
                switch (appBean.name) {
                    case "我的钱包":
                        startActivity(MyWalletActivity.class);
                        break;
                    case "我的积分":
                        startActivity(MyScoreActivity.class);
                        break;
                    case "我的消息":
                        startActivity(MyMessageActivity.class);
                        break;
                    case "权限设置":
                        startActivity(SettingActivity.class);
                        break;
                }
            }
        });
        mRvFunction.setAdapter(mAdapter);
        mAdapter.setData(FunctionBean.mockMineBeans());
    }
}
