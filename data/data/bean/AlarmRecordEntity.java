package com.xfcar.driver.model.bean;

public class AlarmRecordEntity {
	public int id; // 主键ID
	public String alarmPeople; // 报警人
	public int userId; // 用户ID
	public String carNo; // 车牌号
	public String alarmTime; // 报警日期
	public String happenSite; // 报警地区
	public String alertNature; // 报警内容，警情性质
	public String handleInfo; // 处理情况
	public String delFlag; // 是删除状态：0-正常，1-删除
	public String createDate; // 创建时间
	public int createBy; // 创建人
	public String updateDate; // 修改时间
	public int updateBy; // 修改人
}
