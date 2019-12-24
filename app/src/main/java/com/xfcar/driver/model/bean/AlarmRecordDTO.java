package com.xfcar.driver.model.bean;

public class AlarmRecordDTO {
	public int id; // 主键ID
	public String alarmPeople; // 报警人
	public String carNo; // 车牌号
	public String alarmTime; // 报警日期
	public String happenSite; // 报警地区
	public String alertNature; // 报警内容，警情性质
	public String handleInfo; // 处理情况
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
