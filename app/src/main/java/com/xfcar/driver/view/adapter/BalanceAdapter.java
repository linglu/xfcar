package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.BalanceTypeBean;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * @author Linky
 */
public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.BalanceHolder> {

    /**
     * 智能家电
     */
    public List<BalanceTypeBean> ScoreTps = new ArrayList<>();
    private Context mContext;
    private Action1<BalanceTypeBean> mCallback;

    public void setCallback(Action1<BalanceTypeBean> callback) {
        this.mCallback = callback;
    }

    public BalanceAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<BalanceTypeBean> beans) {
        this.ScoreTps.clear();
        this.ScoreTps.addAll(beans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BalanceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_balance, viewGroup, false);
        return new BalanceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BalanceHolder holder, int position) {
        if (ScoreTps != null) {
            final BalanceTypeBean stb = ScoreTps.get(position);
            holder.tvItemNumber.setText(String.format("￥%s", stb.price));
            holder.tvItemName.setText(stb.name);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.call(stb);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return ScoreTps == null ? 0 : ScoreTps.size();
    }

    public class BalanceHolder extends RecyclerView.ViewHolder {

        TextView tvItemNumber;
        TextView tvItemName;

        public BalanceHolder(@NonNull View itemView) {
            super(itemView);
            tvItemNumber = itemView.findViewById(R.id.tv_item_number);
            tvItemName = itemView.findViewById(R.id.tv_item_name);
        }
    }
}
