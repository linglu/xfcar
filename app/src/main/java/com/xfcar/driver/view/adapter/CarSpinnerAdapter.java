package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.RentCarInfoBean;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class CarSpinnerAdapter extends BaseAdapter {

    public List<RentCarInfoBean> mCarInfos = new ArrayList<>();
    private Context mContext;
    private Action1<RentCarInfoBean> mCallback;

    public CarSpinnerAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<RentCarInfoBean> beans) {
        this.mCarInfos.clear();
        this.mCarInfos.addAll(beans);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCarInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mCarInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.tv_spinner_item, parent, false);
        String brand = mCarInfos.get(position).brand;
        String modelNo = mCarInfos.get(position).modelNo;
        tv.setText(String.format("%s%s", brand, modelNo));
        tv.setTag(mCarInfos.get(position));
        return tv;
    }

}

