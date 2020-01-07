package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.RechargeItemBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

/**
 * @author Linky
 */
public class RechargeAdapter extends RecyclerView.Adapter<RechargeAdapter.RechargeHolder> {

    /**
     * 智能家电
     */
    public List<RechargeItemBean> mRechargeApps = new ArrayList<>();
    private Context mContext;
    private Action1<RechargeItemBean> mCallback;

    public void setCallback(Action1<RechargeItemBean> callback) {
        this.mCallback = callback;
    }

    public RechargeAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<RechargeItemBean> beans) {
        this.mRechargeApps.clear();
        this.mRechargeApps.addAll(beans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RechargeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recharge, viewGroup, false);
        return new RechargeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RechargeHolder holder, int position) {
        if (mRechargeApps != null) {
            final RechargeItemBean fb = mRechargeApps.get(position);
            holder.tvRechargeAmount.setText(String.format("%s", fb.amount));
            holder.tvRechargeItem1.setText(fb.item1);
            holder.tvRechargeItem2.setText(fb.item2);
            holder.tvRecharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null) {
                        mCallback.call(fb);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mRechargeApps == null ? 0 : mRechargeApps.size();
    }

    public class RechargeHolder extends RecyclerView.ViewHolder {

        TextView tvRechargeAmount;
        TextView tvRechargeItem1;
        TextView tvRechargeItem2;
        TextView tvRecharge;

        public RechargeHolder(@NonNull View itemView) {
            super(itemView);
            tvRechargeAmount = itemView.findViewById(R.id.tv_recharge_amount);
            tvRechargeItem1 = itemView.findViewById(R.id.tv_recharge_item1);
            tvRechargeItem2 = itemView.findViewById(R.id.tv_recharge_item2);
            tvRecharge = itemView.findViewById(R.id.tv_recharge);
        }
    }
}
