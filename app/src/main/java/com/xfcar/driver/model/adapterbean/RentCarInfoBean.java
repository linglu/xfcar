package com.xfcar.driver.model.adapterbean;

import android.os.Parcel;
import android.os.Parcelable;

public class RentCarInfoBean implements Parcelable {
    public String id;
    public String price;
    public String brand;
    public String modelNo;
    public String type;
    public String delFlag;
    public String createBy;
    public String createDate;
    public String updateBy;
    public String updateDate;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.price);
        dest.writeString(this.brand);
        dest.writeString(this.modelNo);
        dest.writeString(this.type);
        dest.writeString(this.delFlag);
        dest.writeString(this.createBy);
        dest.writeString(this.createDate);
        dest.writeString(this.updateBy);
        dest.writeString(this.updateDate);
    }

    public RentCarInfoBean() {
    }

    protected RentCarInfoBean(Parcel in) {
        this.id = in.readString();
        this.price = in.readString();
        this.brand = in.readString();
        this.modelNo = in.readString();
        this.type = in.readString();
        this.delFlag = in.readString();
        this.createBy = in.readString();
        this.createDate = in.readString();
        this.updateBy = in.readString();
        this.updateDate = in.readString();
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
