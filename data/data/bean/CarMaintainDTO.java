package com.xfcar.driver.model.bean;

public class CarMaintainDTO {
	public int id; // 为空时表示新增；不为空时表示修改
	public int userId; // 用户ID
	public String userName; // 用户名
	public String carNo; // 车牌号
	public float amount; // 金额
	public String maintainTime; // 保养时间
	public String maintainName; // 保养点名称
	public String maintainLocation; // 保养点位置
	public String repairItem; // 维修项
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
