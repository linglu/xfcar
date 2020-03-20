package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarBizBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

/**
 * @author Linky
 */
public class BuyRentItemAdapter extends RecyclerView.Adapter<BuyRentItemAdapter.BalanceHolder> {

    /**
     * 智能家电
     */
    public List<CarBizBean> mBribs = new ArrayList<>();
    private Context mContext;
    private Action1<CarBizBean> mCallback;
    private boolean mIsBuyPlan = true;

    public void setCallback(Action1<CarBizBean> callback) {
        this.mCallback = callback;
    }

    public BuyRentItemAdapter(Context context, boolean isBuyPlan) {
        mContext = context;
        this.mIsBuyPlan = isBuyPlan;
    }

    public void setData(List<CarBizBean> beans) {
        this.mBribs.clear();
        this.mBribs.addAll(beans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BalanceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_buy_rent_plan, viewGroup, false);
        return new BalanceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BalanceHolder holder, int position) {
        if (mBribs != null) {
            final CarBizBean stb = mBribs.get(position);
            holder.mTvItemName.setText(mIsBuyPlan ? "首付" : "押金");
            holder.mTvItemValue.setText(stb.deposit);
            holder.mTvItemValue2.setText(stb.paymentAmount);
            holder.mTvItemValue3.setText(stb.monthCount);
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
        return mBribs == null ? 0 : mBribs.size();
    }

    public class BalanceHolder extends RecyclerView.ViewHolder {

        TextView mTvItemName;
        TextView mTvItemValue;
        TextView mTvItemName2;
        TextView mTvItemValue2;
        TextView mTvItemName3;
        TextView mTvItemValue3;

        public BalanceHolder(@NonNull View itemView) {
            super(itemView);
            mTvItemName = itemView.findViewById(R.id.tv_item_name);
            mTvItemValue = itemView.findViewById(R.id.tv_item_value);
            mTvItemName2 = itemView.findViewById(R.id.tv_item_name_2);
            mTvItemValue2 = itemView.findViewById(R.id.tv_item_value_2);
            mTvItemName3 = itemView.findViewById(R.id.tv_item_name_3);
            mTvItemValue3 = itemView.findViewById(R.id.tv_item_value_3);

        }
    }
}
