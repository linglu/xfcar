package com.xfcar.driver.model.bean;

public class CouponUserEntity {
	public int id; // ID
	public int couponId; // 优惠券ID
	public String couponSn; // 优惠卷编号
	public int userId; // 优惠券使用户id
	public String userName; // 优惠券使用户名称
	public String status; // 是否使用：0-未使用  1-已使用  2-已过期
	public String delFlag; // 是删除状态：0-正常，1-删除
	public String addTime; // 添加时间
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
