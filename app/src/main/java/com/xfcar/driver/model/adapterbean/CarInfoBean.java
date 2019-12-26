package com.xfcar.driver.model.adapterbean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author linky
 */
public class CarInfoBean implements Parcelable {
    public String id;
    public String carNo;
    public String engineNo;
    public String brand;
    public String modelNo;
    public String status;
    public String type;
    public String username;
    public String userId;
    public String company;
    public String macid;
    public String objectid;
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
        dest.writeString(this.carNo);
        dest.writeString(this.engineNo);
        dest.writeString(this.brand);
        dest.writeString(this.modelNo);
        dest.writeString(this.status);
        dest.writeString(this.type);
        dest.writeString(this.username);
        dest.writeString(this.userId);
        dest.writeString(this.company);
        dest.writeString(this.macid);
        dest.writeString(this.objectid);
        dest.writeString(this.delFlag);
        dest.writeString(this.createBy);
        dest.writeString(this.createDate);
        dest.writeString(this.updateBy);
        dest.writeString(this.updateDate);
    }

    public CarInfoBean() {
    }

    protected CarInfoBean(Parcel in) {
        this.id = in.readString();
        this.carNo = in.readString();
        this.engineNo = in.readString();
        this.brand = in.readString();
        this.modelNo = in.readString();
        this.status = in.readString();
        this.type = in.readString();
        this.username = in.readString();
        this.userId = in.readString();
        this.company = in.readString();
        this.macid = in.readString();
        this.objectid = in.readString();
        this.delFlag = in.readString();
        this.createBy = in.readString();
        this.createDate = in.readString();
        this.updateBy = in.readString();
        this.updateDate = in.readString();
    }

    public static final Creator<CarInfoBean> CREATOR = new Creator<CarInfoBean>() {
        @Override
        public CarInfoBean createFromParcel(Parcel source) {
            return new CarInfoBean(source);
        }

        @Override
        public CarInfoBean[] newArray(int size) {
            return new CarInfoBean[size];
        }
    };
}
