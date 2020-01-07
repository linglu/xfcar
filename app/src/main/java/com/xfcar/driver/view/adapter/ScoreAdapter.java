package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.ScoreTypeBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

/**
 * @author Linky
 */
public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.FuncHolder> {

    /**
     * 智能家电
     */
    public List<ScoreTypeBean> ScoreTps = new ArrayList<>();
    private Context mContext;
    private Action1<ScoreTypeBean> mCallback;

    public void setCallback(Action1<ScoreTypeBean> callback) {
        this.mCallback = callback;
    }

    public ScoreAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<ScoreTypeBean> beans) {
        this.ScoreTps.clear();
        this.ScoreTps.addAll(beans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FuncHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_score_type, viewGroup, false);
        return new FuncHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FuncHolder holder, int position) {
        if (ScoreTps != null) {
            final ScoreTypeBean stb = ScoreTps.get(position);
            switch (stb.content) {
                case "订单积分":
                    holder.ivScoreType.setImageResource(R.mipmap.dingdanjifen);
                    break;
                case "充电积分":
                    holder.ivScoreType.setImageResource(R.mipmap.chongdian);
                    break;
                case "保修积分":
                    holder.ivScoreType.setImageResource(R.mipmap.baoxiujifen);
                    break;
                case "保养积分":
                    holder.ivScoreType.setImageResource(R.mipmap.baoyangjifen);
                    break;
                default:
                    holder.ivScoreType.setImageResource(R.mipmap.baoyangjifen);
                    break;
            }
            holder.tvScore.setText(String.format("%s积分", stb.integral));
            holder.tvScoreType.setText(stb.content);
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

    public class FuncHolder extends RecyclerView.ViewHolder {

        TextView tvScore;
        TextView tvScoreType;
        ImageView ivScoreType;

        public FuncHolder(@NonNull View itemView) {
            super(itemView);
            tvScore = itemView.findViewById(R.id.tv_score);
            tvScoreType = itemView.findViewById(R.id.tv_score_type);
            ivScoreType = itemView.findViewById(R.id.iv_score_type);
        }
    }
}
