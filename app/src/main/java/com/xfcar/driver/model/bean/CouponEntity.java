package com.xfcar.driver.model.bean;

public class CouponEntity {
	public int id; // 主键ID
	public String couponName; // 优惠券名称
	public float amount; //  优惠券金额
	public int count; //  优惠券发行数量
	public int stock; // 剩余优惠券数量
	public float orderAmount; // 优惠券使用的订单金额，订单满足该金额时才可以使用该优惠券
	public String type; // 优惠卷类型,1、抵扣  2、折扣(打折）
	public String startTime; // 优惠券生效时间
	public String endTime; //  优惠券失效时间
	public String status; //  优惠券信息状态，默认为0，使用后为1，2为过期
	public int pictureId; //  优惠券图片
	public String delFlag; // 删除状态：0-正常，1-删除
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
