package com.xfcar.driver.model.adapterbean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class RentCarInfoBean implements Parcelable {

    public String id;
    public String carCode;
    public String carName;
    public String brand;
    public String modelNo;
    public String color;
    public String carPrice;
    public String params;
    public List<CarPicBean> carPicture;
    public List<CarBizBean> carBizPlan;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.carCode);
        dest.writeString(this.carName);
        dest.writeString(this.brand);
        dest.writeString(this.modelNo);
        dest.writeString(this.color);
        dest.writeString(this.carPrice);
        dest.writeString(this.params);
        dest.writeList(this.carPicture);
        dest.writeList(this.carBizPlan);
    }

    public RentCarInfoBean() {
    }

    protected RentCarInfoBean(Parcel in) {
        this.id = in.readString();
        this.carCode = in.readString();
        this.carName = in.readString();
        this.brand = in.readString();
        this.modelNo = in.readString();
        this.color = in.readString();
        this.carPrice = in.readString();
        this.params = in.readString();
        this.carPicture = new ArrayList<CarPicBean>();
        in.readList(this.carPicture, CarPicBean.class.getClassLoader());
        this.carBizPlan = new ArrayList<CarBizBean>();
        in.readList(this.carBizPlan, CarBizBean.class.getClassLoader());
    }

    public static final Creator<RentCarInfoBean> CREATOR = new Creator<RentCarInfoBean>() {
        @Override
        public RentCarInfoBean createFromParcel(Parcel source) {
            return new RentCarInfoBean(source);
        }

        @Override
        public RentCarInfoBean[] newArray(int size) {
            return new RentCarInfoBean[size];
        }
    };
}