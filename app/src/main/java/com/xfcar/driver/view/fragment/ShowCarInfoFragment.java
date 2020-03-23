package com.xfcar.driver.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;
import com.xfcar.driver.mvp.BaseFragment;
import com.xfcar.driver.view.adapter.CarPicAdapter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

public class ShowCarInfoFragment extends BaseFragment implements Action1<String>, View.OnClickListener {

    private TextView mTvCarConfig;
    private View mVCarConfig;
    private TextView mTvCarPic;
    private View mVCarPic;
    private View mVConfigDetail;
    private RecyclerView mRvPics;
    private int[] ps = {
            R.id.tv_param_1,
            R.id.tv_param_2,
            R.id.tv_param_3,
            R.id.tv_param_4,
            R.id.tv_param_5,
            R.id.tv_param_6
    };

    private TextView[] tvs = new TextView[6];
    private CarPicAdapter adapter;
    private RentCarInfoBean mRentCarInfoBean;
    private Action1<Boolean> mListener;
    private boolean mShowPic;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Type[] is = context.getClass().getGenericInterfaces();
        if (is.length > 0) {
            for (Type i1 : is) {
                if (i1 instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) i1;
                    Type[] types = pt.getActualTypeArguments();

                    if (pt.getRawType() == Action1.class
                            && types[0] == Boolean.class) {
                        mListener = (Action1<Boolean>) context;
                        break;
                    }
                }
            }
        }
    }

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

        mTvCarConfig = mRootView.findViewById(R.id.tv_car_config);
        mVCarConfig = mRootView.findViewById(R.id.v_car_config);
        mTvCarPic = mRootView.findViewById(R.id.tv_car_pic);
        mVCarPic = mRootView.findViewById(R.id.v_car_pic);

        for (int i = 0; i < tvs.length; i++) {
            tvs[i] = mRootView.findViewById(ps[i]);
        }

        mRootView.findViewById(R.id.tv_car_config).setOnClickListener(this);
        mRootView.findViewById(R.id.v_car_config).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_car_pic).setOnClickListener(this);
        mRootView.findViewById(R.id.v_car_pic).setOnClickListener(this);

        mVConfigDetail = mRootView.findViewById(R.id.ll_config_detail);
        mRvPics = mRootView.findViewById(R.id.rv_car_pics);
        adapter = new CarPicAdapter(mActivity);
        mRvPics.setAdapter(adapter);

        update(getArguments(), false);

    }

    @Override
    public void call(String s) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_car_config) {
            showParams();
        } else if (id == R.id.tv_car_pic) {
            showCarPic();
        }
    }

    private void showCarPic() {

        mShowPic = true;
        mListener.call(true);

        mTvCarPic.setTextColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));
        mVCarPic.setVisibility(View.VISIBLE);
        mTvCarConfig.setTextColor(ContextCompat.getColor(mActivity, R.color.font_gray3));
        mVCarConfig.setVisibility(View.INVISIBLE);

        mVConfigDetail.setVisibility(View.INVISIBLE);
        mRvPics.setVisibility(View.VISIBLE);

        adapter.setData(mRentCarInfoBean.carPicture);
    }

    private void showParams() {

        mShowPic = false;
        mListener.call(false);

        mTvCarConfig.setTextColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));
        mVCarConfig.setVisibility(View.VISIBLE);
        mTvCarPic.setTextColor(ContextCompat.getColor(mActivity, R.color.font_gray3));
        mVCarPic.setVisibility(View.INVISIBLE);

        mVConfigDetail.setVisibility(View.VISIBLE);
        mRvPics.setVisibility(View.INVISIBLE);
    }

    public void update(Bundle bundle, boolean hold) {

        mRentCarInfoBean = bundle.getParcelable("bean");

        if (!hold) {
            mShowPic = bundle.getBoolean("show_pic", false);
        }

        String[] is = mRentCarInfoBean.params.substring(1, mRentCarInfoBean.params.length()-1).split(",");
        for (int i = 0; i < is.length; i++) {
            String label = is[i].split(":")[0].replace("\"", "");
            String value = is[i].split(":")[1].replace("\"", "");
            tvs[i].setText(String.format("%s : %s", label, value));
        }

        adapter.setData(mRentCarInfoBean.carPicture);

        if (mShowPic) {
            showCarPic();
        } else {
            showParams();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListener = null;
    }
}
