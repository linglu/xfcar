package com.xfcar.driver.model.bean;

public class CouponUserDTO {
	public int id; // ID
	public int couponId; // 优惠券ID
	public String couponSn; // 优惠卷编号
	public int userId; // 用户id
	public String userName; // 优惠券使用户名称
	public String status; // 是否使用：0-未使用  1-已使用  2-已过期
	public String addTime; // 添加时间
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
