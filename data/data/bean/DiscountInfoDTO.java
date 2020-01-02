package com.xfcar.driver.model.bean;

public class DiscountInfoDTO {
	public int id; // 主键ID
	public String discountCode; // 套餐编码
	public String discountName; // 套餐名称
	public String discountType; // 套餐类型：1-租车，2-购车
	public String discountContent; // 套餐内容
	public String groupId; // 套餐组别ID
	public String groupName; // 套餐组别名称
	public String payNumber; // 分多少期支付
	public float payAmount; // 每期支付金额
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
