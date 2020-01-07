package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarInfoBean;

public class MonthSpinnerAdapter extends MySpinnerAdapter<String> {

    public MonthSpinnerAdapter(Context context) {
        super(context);
    }

    @Override
    public View getV(String bean, View view, ViewGroup parent) {
        TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.tv_spinner_month, parent, false);
        tv.setText(String.format("%s", bean));
        return tv;
    }
}

