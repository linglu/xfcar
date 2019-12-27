package com.xfcar.driver.view.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.ClaimPayBean;


import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * @author Linky
 */
public class ClaimAdapter extends RecyclerView.Adapter<ClaimAdapter.ClaimPayHolder> {

    /**
     * 智能家电
     */
    public List<ClaimPayBean> mClaimPays = new ArrayList<>();
    private Context mContext;
    private Action1<ClaimPayBean> mCallback;

    public void setCallback(Action1<ClaimPayBean> callback) {
        this.mCallback = callback;
    }

    public ClaimAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<ClaimPayBean> beans) {
        this.mClaimPays.clear();
        this.mClaimPays.addAll(beans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClaimPayHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_claim_pay, viewGroup, false);
        return new ClaimPayHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClaimPayHolder holder, int position) {
        if (mClaimPays != null) {
            final ClaimPayBean cpb = mClaimPays.get(position);
            holder.tv_car_no.setText(String.format("车牌号:%s", cpb.carNo));
            holder.tv_amount.setText(String.format("金额:%s", cpb.amount));
            holder.tv_accident_time.setText(String.format("事故时间:%s", cpb.accidentTime));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null) {
                        mCallback.call(cpb);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mClaimPays == null ? 0 : mClaimPays.size();
    }

    public class ClaimPayHolder extends RecyclerView.ViewHolder {

        TextView tv_car_no;
        TextView tv_amount;
        TextView tv_accident_time;

        public ClaimPayHolder(@NonNull View itemView) {
            super(itemView);
            tv_car_no = itemView.findViewById(R.id.tv_car_no);
            tv_amount = itemView.findViewById(R.id.tv_amount);
            tv_accident_time = itemView.findViewById(R.id.tv_accident_time);
        }
    }
}
