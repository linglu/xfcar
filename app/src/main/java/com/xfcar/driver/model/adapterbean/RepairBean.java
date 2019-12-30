package com.xfcar.driver.model.adapterbean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author linky
 */
public class RepairBean implements Parcelable {
    public String id;
    public int userId;
    public String userName;
    public String carNo;
    public String amount;
    public String maintainTime;
    public String maintainName;
    public String maintainLocation;
    public String repairItem;
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
        dest.writeInt(this.userId);
        dest.writeString(this.userName);
        dest.writeString(this.carNo);
        dest.writeString(this.amount);
        dest.writeString(this.maintainTime);
        dest.writeString(this.maintainName);
        dest.writeString(this.maintainLocation);
        dest.writeString(this.repairItem);
        dest.writeString(this.delFlag);
        dest.writeString(this.createBy);
        dest.writeString(this.createDate);
        dest.writeString(this.updateBy);
        dest.writeString(this.updateDate);
    }

    public RepairBean() {
    }

    protected RepairBean(Parcel in) {
        this.id = in.readString();
        this.userId = in.readInt();
        this.userName = in.readString();
        this.carNo = in.readString();
        this.amount = in.readString();
        this.maintainTime = in.readString();
        this.maintainName = in.readString();
        this.maintainLocation = in.readString();
        this.repairItem = in.readString();
        this.delFlag = in.readString();
        this.createBy = in.readString();
        this.createDate = in.readString();
        this.updateBy = in.readString();
        this.updateDate = in.readString();
    }

    public static final Creator<RepairBean> CREATOR = new Creator<RepairBean>() {
        @Override
        public RepairBean createFromParcel(Parcel source) {
            return new RepairBean(source);
        }

        @Override
        public RepairBean[] newArray(int size) {
            return new RepairBean[size];
        }
    };
}
