package com.xfcar.driver.model.bean;

public class BusinessType {
	public Integer id;
    public String businessType;

    public BusinessType(Integer userId, String businessType) {
        this.id = userId;
        this.businessType = businessType;
    }
}
