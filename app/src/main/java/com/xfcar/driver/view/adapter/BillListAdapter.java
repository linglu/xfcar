package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.SentBillListBean;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * @author Linky
 */
public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.BillHolder> {

    /**
     * 智能家电
     */
    public List<SentBillListBean> mListBean = new ArrayList<>();
    private Context mContext;
    private Action1<SentBillListBean> mCallback;

    public void setCallback(Action1<SentBillListBean> callback) {
        this.mCallback = callback;
    }

    public BillListAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<SentBillListBean> beans) {
        this.mListBean.clear();
        this.mListBean.addAll(beans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BillHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bill_list, viewGroup, false);
        return new BillHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillHolder holder, int position) {
        if (mListBean != null) {
            final SentBillListBean sblb = mListBean.get(position);
            holder.tvSendDate.setText(String.format("%s", sblb.date));
            holder.tvSendTotal.setText(String.format("¥%s", sblb.total));
            holder.tvSendReward.setText(String.format("¥%s", sblb.reward));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null) {
                        mCallback.call(sblb);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mListBean == null ? 0 : mListBean.size();
    }

    public class BillHolder extends RecyclerView.ViewHolder {

        TextView tvSendDate;
        TextView tvSendTotal;
        TextView tvSendReward;

        public BillHolder(@NonNull View itemView) {
            super(itemView);
            tvSendDate = itemView.findViewById(R.id.tv_send_date);
            tvSendTotal = itemView.findViewById(R.id.tv_send_total);
            tvSendReward = itemView.findViewById(R.id.tv_send_reward);
        }
    }
}
