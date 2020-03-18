package com.xfcar.driver.model.bean;

public class ShortRentBean {

	public Integer userId; // 用户ID
    public String appointmentType;  // (10:返租预约;  20:买车预约;  30:租车预约)
	public String subletDate; // 转租时间

    public ShortRentBean(Integer userId, String appointmentType, String subletDate) {
        this.userId = userId;
        this.appointmentType = appointmentType;
        this.subletDate = subletDate;
    }
}
