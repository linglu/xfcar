package com.xfcar.driver.model.bean;

public class CallCarDTO {
	public int id; // 主键ID
	public int userId; // 用户ID
	public String useTime; // 用车时间
	public String sourcePlace; // 出发地
	public String destination; // 目的地
	public String updateBy; // 修改人
	public String updateDate; // 修改时间
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
