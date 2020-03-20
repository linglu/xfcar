package com.xfcar.driver.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarPicBean;
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
public class CarPicAdapter extends RecyclerView.Adapter<CarPicAdapter.FuncHolder> {

    /**
     * 智能家电
     */
    public List<CarPicBean> mCarPicBeans = new ArrayList<>();
    private Context mContext;
    private Action1<CarPicBean> mCallback;

    public void setCallback(Action1<CarPicBean> callback) {
        this.mCallback = callback;
    }

    public CarPicAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<CarPicBean> beans) {
        this.mCarPicBeans.clear();
        this.mCarPicBeans.addAll(beans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FuncHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_pager, viewGroup, false);
        return new FuncHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FuncHolder holder, int position) {
        if (mCarPicBeans != null) {
            final CarPicBean pb = mCarPicBeans.get(position);
            L.i("CarPicAdapter onBindViewHolder picUrl : " + pb.picUrl);
            ImageLoadHelper.loadImage(mContext, pb.picUrl, holder.ivPager);
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

    @Override
    public int getItemCount() {
        return mCarPicBeans == null ? 0 : mCarPicBeans.size();
    }

    public class FuncHolder extends RecyclerView.ViewHolder {

        ImageView ivPager;

        public FuncHolder(@NonNull View itemView) {
            super(itemView);
            ivPager = itemView.findViewById(R.id.iv_pager);
        }
    }
}
