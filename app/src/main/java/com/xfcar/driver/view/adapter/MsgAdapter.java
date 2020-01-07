package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.MsgBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

/**
 * @author Linky
 */
public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.RewardHolder> {

    /**
     * 智能家电
     */
    public List<MsgBean> MsgTps = new ArrayList<>();
    private Context mContext;
    private Action1<MsgBean> mCallback;

    public void setCallback(Action1<MsgBean> callback) {
        this.mCallback = callback;
    }

    public MsgAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<MsgBean> beans) {
        this.MsgTps.clear();
        this.MsgTps.addAll(beans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RewardHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_msg, viewGroup, false);
        return new RewardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardHolder holder, int position) {
        if (MsgTps != null) {
            final MsgBean stb = MsgTps.get(position);
            holder.tvMsg.setText(stb.msg);
            holder.tvTime.setText(stb.time);
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
        return MsgTps == null ? 0 : MsgTps.size();
    }

    public class RewardHolder extends RecyclerView.ViewHolder {

        TextView tvMsg;
        TextView tvTime;

        public RewardHolder(@NonNull View itemView) {
            super(itemView);
            tvMsg = itemView.findViewById(R.id.tv_message);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }
}
