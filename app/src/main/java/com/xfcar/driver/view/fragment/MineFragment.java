package com.xfcar.driver.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseFragment;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.view.LoginActivity;

/**
 * @author linky
 */
public class MineFragment extends BaseFragment {

    private Requester mRequester = new Requester();

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

       mRootView.findViewById(R.id.bt_logout).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               final DataManager dataManager = new DataManager(mActivity);
                mRequester.logout(new ResultCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        toastMsg("退出登录成功");
                        dataManager.setUserId(null);
                        startActivity(LoginActivity.class);
                        mActivity.finish();
                    }

                    @Override
                    public void onFail(String msg) {
                        toastMsg(msg);
                    }
                });
           }
       });
    }
}
