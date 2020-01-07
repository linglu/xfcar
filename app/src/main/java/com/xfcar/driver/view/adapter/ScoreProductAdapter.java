package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.ScoreProductBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

/**
 * @author Linky
 */
public class ScoreProductAdapter extends RecyclerView.Adapter<ScoreProductAdapter.ScoreProHolder> {

    /**
     * 智能家电
     */
    public List<ScoreProductBean> ScorePros = new ArrayList<>();
    private Context mContext;
    private Action1<ScoreProductBean> mCallback;

    public void setCallback(Action1<ScoreProductBean> callback) {
        this.mCallback = callback;
    }

    public ScoreProductAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<ScoreProductBean> beans) {
        this.ScorePros.clear();
        this.ScorePros.addAll(beans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScoreProHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_score_products, viewGroup, false);
        return new ScoreProHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreProHolder holder, int position) {
        if (ScorePros != null) {
            final ScoreProductBean stb = ScorePros.get(position);
            holder.tvScore.setText(String.format("%s", stb.goodsIntegral));
            holder.tvProdType.setText(stb.content);
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
        return ScorePros == null ? 0 : ScorePros.size();
    }

    public class ScoreProHolder extends RecyclerView.ViewHolder {

        TextView tvScore;
        TextView tvProdType;

        public ScoreProHolder(@NonNull View itemView) {
            super(itemView);
            tvScore = itemView.findViewById(R.id.tv_score);
            tvProdType = itemView.findViewById(R.id.tv_prod_type);
        }
    }
}
