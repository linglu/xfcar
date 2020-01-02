package com.xfcar.driver.model.bean;

public class CarInsuranceDTO {
	public int id; // 主键ID
	public int userId; // 用户ID
	public String userName; // 用户名
	public String carNo; // 车牌号
	public float amount; // 理赔金额
	public String accidentTime; // 事故时间
	public String accidentLocation; // 事故地点
	public String repairLocation; // 维修地点
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
