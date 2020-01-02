package com.xfcar.driver.model.bean;

public class CarInfoEntity {
	public String carNo; // 车牌号
	public int id; // 为空时表示新增，不为空时表示修改
	public String engineNo; // 发动机号
	public String brand; // 品牌
	public String modelNo; // 型号
	public String status; // 0：空闲中，1：租借中，2：维修中
	public String type; // 1：电动小汽车，2：燃油小汽车
	public String username; // 借租人,必需是已注册用户（空闲中此字段为空）
	public int userId; // 借租人,必需是已注册用户（空闲中此字段为空）
	public String company; // '在绑定平台注册的公司名称
	public String macid; // 设备号
	public String objectid; // 绑定后生成的ID
	public String delFlag; // 0-正常，1-删除
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
