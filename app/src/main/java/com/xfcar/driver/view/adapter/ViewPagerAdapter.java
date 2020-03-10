package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wangpeiyuan.cycleviewpager2.adapter.CyclePagerAdapter;
import com.xfcar.driver.R;
import com.xfcar.driver.model.bean.BusinessBean;
import com.xfcar.driver.utils.ImageLoadHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

/**
 * @author Linky
 */
public class ViewPagerAdapter extends CyclePagerAdapter<ViewPagerAdapter.ViewPageHolder> {

    /**
     * 智能家电
     */
    public List<BusinessBean> mViewPageApps = new ArrayList<>();
    private Context mContext;
    private Action1<BusinessBean> mCallback;

    public void setCallback(Action1<BusinessBean> callback) {
        this.mCallback = callback;
    }

    public ViewPagerAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<BusinessBean> beans) {
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
            final BusinessBean pb = mViewPageApps.get(position);
            ImageLoadHelper.loadImage(mContext, pb.picUrl, holder.ivPager);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.call(pb);
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

