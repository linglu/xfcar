package com.xfcar.driver.model.adapterbean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Linky
 */
public class ClaimPayBean implements Parcelable {
    public String id;
    public String userId;
    public String userName;
    public String idCardNumber;
    public String carNo;
    public String amount;
    public String accidentTime;
    public String accidentLocation;
    public String repairLocation;
    public String repairDays;
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
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.idCardNumber);
        dest.writeString(this.carNo);
        dest.writeString(this.amount);
        dest.writeString(this.accidentTime);
        dest.writeString(this.accidentLocation);
        dest.writeString(this.repairLocation);
        dest.writeString(this.repairDays);
        dest.writeString(this.delFlag);
        dest.writeString(this.createBy);
        dest.writeString(this.createDate);
        dest.writeString(this.updateBy);
        dest.writeString(this.updateDate);
    }

    public ClaimPayBean() {
    }

    protected ClaimPayBean(Parcel in) {
        this.id = in.readString();
        this.userId = in.readString();
        this.userName = in.readString();
        this.idCardNumber = in.readString();
        this.carNo = in.readString();
        this.amount = in.readString();
        this.accidentTime = in.readString();
        this.accidentLocation = in.readString();
        this.repairLocation = in.readString();
        this.repairDays = in.readString();
        this.delFlag = in.readString();
        this.createBy = in.readString();
        this.createDate = in.readString();
        this.updateBy = in.readString();
        this.updateDate = in.readString();
    }

    public static final Creator<ClaimPayBean> CREATOR = new Creator<ClaimPayBean>() {
        @Override
        public ClaimPayBean createFromParcel(Parcel source) {
            return new ClaimPayBean(source);
        }

        @Override
        public ClaimPayBean[] newArray(int size) {
            return new ClaimPayBean[size];
        }
    };
}
