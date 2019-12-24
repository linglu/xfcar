package com.xfcar.driver.model.bean;

public class CarInfoDTO {
	public int id; // 主键ID
	public String carNo; // 车牌号
	public String macId; // 设备号
	public String engineNo; // 发动机号
	public String brand; // 品牌
	public String modelNo; // 型号
	public String type; // 机动车类型
	public String username; // 借租人
	public String company; // 在绑定平台注册的公司名称
	public String objectid; // 绑定后生成的ID
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
