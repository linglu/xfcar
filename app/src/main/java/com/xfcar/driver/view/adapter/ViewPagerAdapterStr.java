package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wangpeiyuan.cycleviewpager2.adapter.CyclePagerAdapter;
import com.xfcar.driver.R;
import com.xfcar.driver.utils.ImageLoadHelper;
import com.xfcar.driver.utils.L;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

/**
 * @author Linky
 */
public class ViewPagerAdapterStr extends CyclePagerAdapter<ViewPagerAdapterStr.ViewPageHolder> {

    /**
     * 智能家电
     */
    public List<String> mViewPageApps = new ArrayList<>();
    private Context mContext;
    private Action1<String> mCallback;

    public void setCallback(Action1<String> callback) {
        this.mCallback = callback;
    }

    public ViewPagerAdapterStr(Context context) {
        mContext = context;
    }

    public void setData(List<String> beans) {
        this.mViewPageApps.clear();
        this.mViewPageApps.addAll(beans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewPageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_pager, viewGroup, false);
        return new ViewPageHolder(view);
    }

    @Override
    public int getRealItemCount() {
        return mViewPageApps == null ? 0 : mViewPageApps.size();
    }

    @Override
    public void onBindRealViewHolder(@NonNull ViewPageHolder holder, int position) {
        if (mViewPageApps != null) {
            final String pb = mViewPageApps.get(position);
            L.i("ViewPagerAdapterStr onBindRealViewHolder str " + pb);
            ImageLoadHelper.loadImage(mContext, pb, holder.ivPager);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null) {
                        mCallback.call(pb);
                    }
                }
            });
        }
    }

    public class ViewPageHolder extends RecyclerView.ViewHolder {

        ImageView ivPager;

        public ViewPageHolder(@NonNull View itemView) {
            super(itemView);
            ivPager = itemView.findViewById(R.id.iv_pager);
        }
    }
}

