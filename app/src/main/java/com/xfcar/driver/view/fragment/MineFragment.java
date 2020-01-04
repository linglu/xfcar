package com.xfcar.driver.view.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfcar.driver.App;
import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseFragment;
import com.xfcar.driver.view.BalanceActivity;
import com.xfcar.driver.view.DedutPercentActivity;
import com.xfcar.driver.view.MyMessageActivity;
import com.xfcar.driver.view.MyScoreActivity;
import com.xfcar.driver.view.ReceiptActivity;
import com.xfcar.driver.view.SearchInfoActivity;
import com.xfcar.driver.view.SettingActivity;
import com.xfcar.driver.view.ShareRewardActivity;
import com.xfcar.driver.view.adapter.FunctionAdapter;

/**
 * @author linky
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView mRvFunction;
    private FunctionAdapter mAdapter;
    private ImageView mIvAvatar;
    private TextView mTvUserName;

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
        mIvAvatar = mRootView.findViewById(R.id.iv_avatar);
        mTvUserName = mRootView.findViewById(R.id.tv_username);
//        mTvUserName.setText(App.sInstance.mDataManager.getUserId());

        mRootView.findViewById(R.id.ll_my_wallet).setOnClickListener(this);
        mRootView.findViewById(R.id.ll_balance_account).setOnClickListener(this);
        mRootView.findViewById(R.id.ll_receipt).setOnClickListener(this);
        mRootView.findViewById(R.id.ll_search).setOnClickListener(this);
        mRootView.findViewById(R.id.ll_sent_reward).setOnClickListener(this);
        mRootView.findViewById(R.id.ll_share_reward).setOnClickListener(this);
        mRootView.findViewById(R.id.ll_score).setOnClickListener(this);
        mRootView.findViewById(R.id.ll_message).setOnClickListener(this);
        mRootView.findViewById(R.id.ll_permission).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.ll_my_wallet) {
            // TODO: 2020/1/4 动画特效
        } else if (id == R.id.ll_balance_account) {
            startActivity(BalanceActivity.class);
        } else if (id == R.id.ll_receipt) {
            startActivity(ReceiptActivity.class);
        } else if (id == R.id.ll_search) {
            startActivity(SearchInfoActivity.class);
        } else if (id == R.id.ll_sent_reward) {
            startActivity(DedutPercentActivity.class);
        } else if (id == R.id.ll_share_reward) {
            startActivity(ShareRewardActivity.class);
        } else if (id == R.id.ll_score) {
            startActivity(MyScoreActivity.class);
        } else if (id == R.id.ll_message) {
            startActivity(MyMessageActivity.class);
        } else if (id == R.id.ll_permission) {
            startActivity(SettingActivity.class);
        }
    }
}
