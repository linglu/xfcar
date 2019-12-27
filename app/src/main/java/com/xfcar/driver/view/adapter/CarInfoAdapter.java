package com.xfcar.driver.view.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarInfoBean;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * @author Linky
 */
public class CarInfoAdapter extends RecyclerView.Adapter<CarInfoAdapter.CarInfoHolder> {

    /**
     * 智能家电
     */
    public List<CarInfoBean> mCarInfos = new ArrayList<>();
    private Context mContext;
    private Action1<CarInfoBean> mCallback;

    public void setCallback(Action1<CarInfoBean> callback) {
        this.mCallback = callback;
    }

    public CarInfoAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<CarInfoBean> beans) {
        this.mCarInfos.clear();
        this.mCarInfos.addAll(beans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CarInfoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_car_info, viewGroup, false);
        return new CarInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarInfoHolder holder, int position) {
        if (mCarInfos != null) {
            final CarInfoBean cib = mCarInfos.get(position);
            holder.tv_car_no.setText(String.format("车牌号:%s", cib.carNo));
            holder.tv_engine_no.setText(String.format("引擎号:%s", cib.engineNo));
            holder.tv_brand.setText(String.format("牌子:%s", cib.brand));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null) {
                        mCallback.call(cib);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mCarInfos == null ? 0 : mCarInfos.size();
    }

    public class CarInfoHolder extends RecyclerView.ViewHolder {

        TextView tv_car_no;
        TextView tv_engine_no;
        TextView tv_brand;

        public CarInfoHolder(@NonNull View itemView) {
            super(itemView);
            tv_car_no = itemView.findViewById(R.id.tv_car_no);
            tv_engine_no = itemView.findViewById(R.id.tv_engine_no);
            tv_brand = itemView.findViewById(R.id.tv_brand);
        }
    }
}
