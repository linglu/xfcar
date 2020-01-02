package com.xfcar.driver.model.bean;

public class TransactionDTO {
	public int id; // 主键ID
	public int userId; // 购买用户ID
	public String userName; // 购买用户名
	public String discountCode; // 套餐编码
	public float amount; // 交易金额
	public float balance; // 租金余额
	public String transactionType; // 交易类型(1-租车、2-购车、3-续租)
	public int carId; // 车辆ID
	public String carNo; // 车牌号
	public String buyDate; // 购买日期
	public String brand; // 品牌
	public String modelNo; // 型号
	public String status; // 0：有效  1：无效
	public String type; // 1：电动小汽车，2：燃油小汽车
	public String deadline; // 截止时间
	public String delFlag; // 删除状态：0-正常，1-删除
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
