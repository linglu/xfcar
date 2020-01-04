package com.xfcar.driver.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseFragment;

import rx.functions.Action1;

public class ShowCarInfoFragment extends BaseFragment implements Action1<String> {


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
    }

    @Override
    public void call(String s) {

    }
}
