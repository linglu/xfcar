package com.xfcar.driver.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.SentBillListBean;
import com.xfcar.driver.mvp.BaseFragment;
import com.xfcar.driver.view.SbscrbShortActivity;
import com.xfcar.driver.view.SendOthersActivity;
import com.xfcar.driver.view.adapter.BillListAdapter;

/**
 * @author Linky
 */
public class SendBillFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView mRvList;
    private BillListAdapter mAdapter;

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
        mRootView.findViewById(R.id.ll_send_others).setOnClickListener(this);
        mRootView.findViewById(R.id.ll_subscribe_short).setOnClickListener(this);
        mRvList = mRootView.findViewById(R.id.rv_bill_list);
        mRvList.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new BillListAdapter(mActivity);
        mRvList.setAdapter(mAdapter);
        mAdapter.setData(SentBillListBean.mockList());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_send_others:
                startActivity(SendOthersActivity.class);
                break;
            case R.id.ll_subscribe_short:
                startActivity(SbscrbShortActivity.class);
                break;

        }
    }
}
