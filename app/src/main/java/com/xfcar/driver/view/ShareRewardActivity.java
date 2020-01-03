package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;

import com.xfcar.driver.R;
import com.xfcar.driver.model.bean.InviteRewardBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;

import java.util.List;

public class ShareRewardActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_reward);
        initView();
//        mRequester.appUserInviteFriendInviteReward(this, mDataManager.getUserId(), new ResultCallback<List<InviteRewardBean>>() {
//            @Override
//            public void onSuccess(List<InviteRewardBean> s) {
//
//            }
//
//            @Override
//            public void onFail(String msg) {
//                toastMsg(msg);
//            }
//        });

        mRequester.appUserInviteFriendByMonth(this, mDataManager.getUserId(), new ResultCallback<String>() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}