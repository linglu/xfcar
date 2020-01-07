package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class MySpinnerAdapter<T> extends BaseAdapter {

    public List<T> beans = new ArrayList<>();
    protected Context mContext;

    public MySpinnerAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<T> beans) {
        this.beans.clear();
        this.beans.addAll(beans);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public T getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        return getV(beans.get(position), view, parent);
    }

    public abstract View getV(T bean, View view, ViewGroup parent);
}

