package com.xfcar.driver.model.bean;

public class DiscountItemDTO {
	public int id; // 主键ID
	public int buyId; // 套餐ID
	public String startDate; // 开始时间
	public String endDate; // 结束时间
	public String status; // 0：未付款，1：已付款
	public int phase; // 0：未付款，1：已付款
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
