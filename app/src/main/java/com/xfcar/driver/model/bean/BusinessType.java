package com.xfcar.driver.model.bean;

public class BusinessType {
	public Integer id;
    public String businessType;
    public String userId;

    public BusinessType(Integer userId, String businessType) {
        this.id = userId;
        this.businessType = businessType;
    }

    public BusinessType(String userId, String businessType) {
        this.userId = userId;
        this.businessType = businessType;
    }
}
