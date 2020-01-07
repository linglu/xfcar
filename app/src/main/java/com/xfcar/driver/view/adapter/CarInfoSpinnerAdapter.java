package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class CarInfoSpinnerAdapter extends MySpinnerAdapter<RentCarInfoBean> {

    public CarInfoSpinnerAdapter(Context context) {
        super(context);
    }

    @Override
    public View getV(RentCarInfoBean bean, View view, ViewGroup parent) {
        TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.tv_spinner_item, parent, false);
        String brand = bean.brand;
        String modelNo = bean.modelNo;
        tv.setText(String.format("%s%s", brand, modelNo));
        tv.setTag(bean);
        return tv;
    }
}

