package com.xfcar.driver.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseFragment;
import com.xfcar.driver.view.SbscrbShortActivity;
import com.xfcar.driver.view.SendOthersActivity;

/**
 * @author Linky
 */
public class SendBillFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.fragment_send_bill;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        init();
        return rootView;
    }

    private void init() {
        mRootView.findViewById(R.id.tv_send_others).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_subscribe_short).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send_others:
                startActivity(SendOthersActivity.class);
                break;
            case R.id.tv_subscribe_short:
                startActivity(SbscrbShortActivity.class);
                break;

        }
    }
}
