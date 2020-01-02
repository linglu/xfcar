package com.xfcar.driver.model.bean;

public class DiscountInfoEntity {
	public int id; // 为空时表示新增；不为空时表示修改
	public String discountCode; // 套餐编码
	public String discountName; // 套餐名称
	public String discountType; // 套餐类型：1-租车，2-购车
	public String discountContent; // 套餐内容
	public String groupId; // 套餐组别ID
	public String groupName; // 套餐组别名称
	public String payNumber; // 分多少期支付
	public float payAmount; // 每期支付金额
	public int days; // 期限 -1：无限期
	public float amount; // 金额
	public int times; // 金额
	public String modelNo; // 车辆型号
	public String delFlag; // 删除状态：0-正常，1-删除
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
