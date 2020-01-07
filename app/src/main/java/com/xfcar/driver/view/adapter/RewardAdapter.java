package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.RewardItemBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

/**
 * @author Linky
 */
public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.RewardHolder> {

    /**
     * 智能家电
     */
    public List<RewardItemBean> RewardTps = new ArrayList<>();
    private Context mContext;
    private Action1<RewardItemBean> mCallback;

    public void setCallback(Action1<RewardItemBean> callback) {
        this.mCallback = callback;
    }

    public RewardAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<RewardItemBean> beans) {
        this.RewardTps.clear();
        this.RewardTps.addAll(beans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RewardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_reward, viewGroup, false);
        return new RewardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardHolder holder, int position) {
        if (RewardTps != null) {
            final RewardItemBean stb = RewardTps.get(position);
            holder.tvType.setText(stb.type);
            holder.tvAmount.setText(stb.amount);
            holder.tvReceipt.setText(stb.receipt);
            holder.tvDate.setText(stb.date);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null) {
                        mCallback.call(stb);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return RewardTps == null ? 0 : RewardTps.size();
    }

    public class RewardHolder extends RecyclerView.ViewHolder {

        TextView tvType;
        TextView tvAmount;
        TextView tvReceipt;
        TextView tvDate;

        public RewardHolder(@NonNull View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tv_type);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvReceipt = itemView.findViewById(R.id.tv_receipt);
            tvDate = itemView.findViewById(R.id.tv_date);

        }
    }
}
