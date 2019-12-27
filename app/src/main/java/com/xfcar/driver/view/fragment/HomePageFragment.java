package com.xfcar.driver.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.FunctionBean;
import com.xfcar.driver.mvp.BaseFragment;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.view.BuyOrRentCarActivity;
import com.xfcar.driver.view.CarManageActivity;
import com.xfcar.driver.view.CarReRentActivity;
import com.xfcar.driver.view.InviteActivity;
import com.xfcar.driver.view.OneKeyAlarmActivity;
import com.xfcar.driver.view.RechargeActivity;
import com.xfcar.driver.view.adapter.FunctionAdapter;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import rx.functions.Action1;

/**
 * @author Linky
 */
public class HomePageFragment extends BaseFragment {

    private RecyclerView mRvFunction;
    private FunctionAdapter mAdapter;
    private ViewFlipper mVfAdvDisplay;
    private String[] mNotices = {"广告1", "广告2", "广告3"};
    private ViewPager2 mVpFlipHrznt;

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
                switch (appBean.name) {
                    case "车辆管理":
                        startActivity(CarManageActivity.class);
                        break;
                    case "购车租车":
                        startActivity(BuyOrRentCarActivity.class);
                        break;
                    case "车辆返租":
                        startActivity(CarReRentActivity.class);
                        break;
                    case "租金充值":
                        startActivity(RechargeActivity.class);
                        break;
                    case "邀请好友":
                        startActivity(InviteActivity.class);
                        break;
                    case "一键报警":
                        startActivity(OneKeyAlarmActivity.class);
                        break;
                }
            }
        });
        mRvFunction.setAdapter(mAdapter);
        mAdapter.setData(FunctionBean.mockFuncBeans());

        mVfAdvDisplay = mRootView.findViewById(R.id.view_flipper);

        for (int i = 0; i < 3; i++) {
            View view = getLayoutInflater().inflate(R.layout.view_flip_item, mVfAdvDisplay, false);
            TextView tvNotice = view.findViewById(R.id.tv_notice);
            tvNotice.setText(mNotices[i]);

            mVfAdvDisplay.addView(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    L.i("HomePageFragment onClick ");
                }
            });
        }
        mVfAdvDisplay.setFlipInterval(2000);
        mVfAdvDisplay.startFlipping();


//        mVpFlipHrznt = mRootView.findViewById(R.id.vp_flip_horizontal);
//        mVpFlipHrznt.setAdapter(mAdapter);
    }

}
