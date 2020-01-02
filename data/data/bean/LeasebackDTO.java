package com.xfcar.driver.model.bean;

public class LeasebackDTO {
	public int id; // 主键ID
	public int userId; // 用户ID
	public String username; // 用户名
	public String carNo; // 车牌号
	public String modelNo; // 租车型号
	public String status; // 处理状态: 0-未处理  1-已处理
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
