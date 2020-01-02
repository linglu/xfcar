package com.xfcar.driver.model.bean;

public class CarOperateDTO {
	public int id; // 主键ID
	public int userId; // 用户ID
	public String carNo; // 车牌号
	public String company; // 在绑定平台注册的公司名称
	public String operateCode; // 操作编码号
	public String operateName; // 操作名称
	public String operator; // 操作人员：
	public String operatorDate; // 操作时间：
	public String macid; // 设置ID(手机字符串)
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
