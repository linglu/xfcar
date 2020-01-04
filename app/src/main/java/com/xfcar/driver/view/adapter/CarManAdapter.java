package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.FunctionBean;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * @author Linky
 */
public class CarManAdapter extends RecyclerView.Adapter<CarManAdapter.FuncHolder> {

    /**
     * 智能家电
     */
    public List<FunctionBean> mFuncApps = new ArrayList<>();
    private Context mContext;
    private Action1<FunctionBean> mCallback;

    public void setCallback(Action1<FunctionBean> callback) {
        this.mCallback = callback;
    }

    public CarManAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<FunctionBean> beans) {
        this.mFuncApps.clear();
        this.mFuncApps.addAll(beans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FuncHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_car_function, viewGroup, false);
        return new FuncHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FuncHolder holder, int position) {
        if (mFuncApps != null) {
            final FunctionBean fb = mFuncApps.get(position);
            holder.ivFunc.setImageResource(fb.imgRes);
            holder.tvName.setText(fb.name);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.call(fb);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mFuncApps == null ? 0 : mFuncApps.size();
    }

    public class FuncHolder extends RecyclerView.ViewHolder {

        ImageView ivFunc;
        TextView tvName;

        public FuncHolder(@NonNull View itemView) {
            super(itemView);
            ivFunc = itemView.findViewById(R.id.iv_car_func);
            tvName = itemView.findViewById(R.id.tv_car_func);
        }
    }
}
