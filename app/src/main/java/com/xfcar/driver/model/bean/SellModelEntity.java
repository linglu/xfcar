package com.xfcar.driver.model.bean;

public class SellModelEntity {
	public int id; // 主键ID
	public float price; // 销售价格
	public String brand; // 品牌
	public String modelNo; // 车辆型号
	public String type; // 1：电动小汽车，2：燃油小汽车
	public float twelveMonth; // 12个月
	public float twentyFourMonth; // 24个月
	public float thirtySixMonth; // 36个月
	public String delFlag; // 删除状态：0-正常，1-删除
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
