package com.xfcar.driver.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.wangpeiyuan.cycleviewpager2.CycleViewPager2;
import com.xfcar.driver.App;
import com.xfcar.driver.R;
import com.xfcar.driver.model.bean.BusinessBean;
import com.xfcar.driver.model.bean.Message;
import com.xfcar.driver.model.viewbean.FunctionBean;
import com.xfcar.driver.mvp.BaseFragment;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.view.BuyOrRentCarActivity;
import com.xfcar.driver.view.CarManageActivity;
import com.xfcar.driver.view.InviteActivity;
import com.xfcar.driver.view.RechargeActivity;
import com.xfcar.driver.view.adapter.FunctionAdapter;
import com.xfcar.driver.view.adapter.ViewPagerAdapter;
import com.xfcar.driver.view.dialog.ConfirmDialog;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import rx.functions.Action1;

/**
 * @author Linky
 */
public class HomePageFragment extends BaseFragment implements Action1<String> {

    private RecyclerView mRvFunction;
    private FunctionAdapter mAdapter;
    private ViewPagerAdapter mVpAdapter;
    private ViewFlipper mVfAdvDisplay;
    private CycleViewPager2 mCvpPager;

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
        mRvFunction.setLayoutManager(new GridLayoutManager(mActivity, 4));
        mAdapter = new FunctionAdapter(mActivity);
        mAdapter.setCallback(new Action1<FunctionBean>() {
            @Override
            public void call(FunctionBean appBean) {
                switch (appBean.name) {
                    case "车辆管理":
                        startActivity(CarManageActivity.class);
                        break;
                    case "购车":
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("is_buy_car", true);
                        startActivity(BuyOrRentCarActivity.class, bundle);
                        break;
                    case "租车":
                        bundle = new Bundle();
                        bundle.putBoolean("is_buy_car", false);
                        startActivity(BuyOrRentCarActivity.class, bundle);
                        break;
                    case "租金充值":
                        startActivity(RechargeActivity.class);
                        break;
                    case "邀请好友":
                        startActivity(InviteActivity.class);
                        break;
                    case "一键报警":
                        bundle = new Bundle();
                        bundle.putString("title", "一键报警");
                        ConfirmDialog dlg = ConfirmDialog.newInstance(bundle);
                        dlg.show(getChildFragmentManager(), "CNFRM_DLG");
                        break;
                }
            }
        });
        mRvFunction.setAdapter(mAdapter);
        mAdapter.setData(FunctionBean.mockFuncBeans());

        mVfAdvDisplay = mRootView.findViewById(R.id.view_flipper);
        mCvpPager = mRootView.findViewById(R.id.cvp_pager);
        mVpAdapter = new ViewPagerAdapter(mActivity);
        mCvpPager.setAdapter(mVpAdapter);
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

        Requester requester = new Requester();
        DataManager dataManager = App.sInstance.mDataManager;

        requester.userMessageGet(mActivity, false, dataManager.getUserId(), new ResultCallback<List<Message>>() {

            @Override
            public void onSuccess(List<Message> s) {
                for (int i = 0; i < s.size(); i++) {
                    View view = getLayoutInflater().inflate(R.layout.view_flip_item, mVfAdvDisplay, false);
                    TextView tvNotice = view.findViewById(R.id.tv_notice);
                    tvNotice.setTextColor(Color.parseColor("#C0B085"));
                    tvNotice.setText(s.get(i).msgContent);

                    mVfAdvDisplay.addView(view);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            L.i("HomePageFragment onClick ");
                        }
                    });
                }

                if (s.size() > 0) {
                    mVfAdvDisplay.setFlipInterval(2000);
                    mVfAdvDisplay.startFlipping();
                }
            }

            @Override
            public void onFail(String msg) {
                L.i("HomePageFragment onFail " + msg);
            }
        });

        requester.businessConfigFind(mActivity, dataManager.getUserId(), new ResultCallback<List<BusinessBean>>() {
            @Override
            public void onSuccess(List<BusinessBean> s) {

                if (s.size() == 0) {
                    mVpAdapter.setData(BusinessBean.mockBusinessBeans());
                } else {
                    mVpAdapter.setData(s);
                }

                mVpAdapter.setCallback(new Action1<BusinessBean>() {
                    @Override
                    public void call(BusinessBean bean) {
                        if (bean.id != -1) {
                            // TODO: 点击 banner 图片后的跳转
                        }
                    }
                });

                //设置指示符
//                mCvpPager.setIndicator(indicator);

                //设置自动轮播间隔
                mCvpPager.setAutoTurning(5000);

                //设置切换效果，可以多个效果组合
//              mCvpPager.setPageTransformer(compositePageTransformer);

                //添加间距
//              mCvpPager.addItemDecoration(itemDecoration);

                //添加切换监听
//              mCvpPager.registerOnPageChangeCallback(pageChangeCallback);
//              mCvpPager.setOffscreenPageLimit(limit);
                mCvpPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    @Override
    public void call(String s) {
        Requester requester = new Requester();
        DataManager dataManager = App.sInstance.mDataManager;

        switch (s) {
            case "车辆返租":
                requester.appCarLeasebackOneKey(mActivity, dataManager.getUserId(), new ResultCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        toastMsg("提交成功");
                    }

                    @Override
                    public void onFail(String msg) {
                        toastMsg("提交失败");
                    }
                });
                break;
            case "一键报警":
                requester.appCarLeasebackOneKey(mActivity, dataManager.getUserId(), new ResultCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        toastMsg("报警成功");
                    }

                    @Override
                    public void onFail(String msg) {
                        toastMsg("报警失败");
                    }
                });
                break;
        }


    }
}
