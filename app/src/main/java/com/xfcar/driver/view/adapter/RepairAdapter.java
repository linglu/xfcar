package com.xfcar.driver.view.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.RepairBean;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * @author Linky
 */
public class RepairAdapter extends RecyclerView.Adapter<RepairAdapter.RepairHolder> {

    /**
     * 智能家电
     */
    public List<RepairBean> mRepairs = new ArrayList<>();
    private Context mContext;
    private Action1<RepairBean> mCallback;

    public void setCallback(Action1<RepairBean> callback) {
        this.mCallback = callback;
    }

    public RepairAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<RepairBean> beans) {
        this.mRepairs.clear();
        this.mRepairs.addAll(beans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RepairHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_maintain, viewGroup, false);
        return new RepairHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepairHolder holder, int position) {
        if (mRepairs != null) {
            final RepairBean cpb = mRepairs.get(position);
            holder.tv_car_no.setText(String.format("车牌号:%s", cpb.carNo));
            holder.tv_amount.setText(String.format("金额:%s", cpb.amount));
            holder.tv_maintain_time.setText(String.format("维修时间:%s", cpb.maintainTime));
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
        return mRepairs == null ? 0 : mRepairs.size();
    }

    public class RepairHolder extends RecyclerView.ViewHolder {

        TextView tv_car_no;
        TextView tv_amount;
        TextView tv_maintain_time;

        public RepairHolder(@NonNull View itemView) {
            super(itemView);
            tv_car_no = itemView.findViewById(R.id.tv_car_no);
            tv_amount = itemView.findViewById(R.id.tv_amount);
            tv_maintain_time = itemView.findViewById(R.id.tv_maintain_time);
        }
    }
}
