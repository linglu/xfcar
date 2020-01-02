package com.xfcar.driver.model.bean;

public class AmountUseEntity {
	public int id; // 为空时表示新增；不为空时表示修改
	public int userId; // 用户ID
	public int businessId; // 用户ID
	public String businessType; // 业务类型：1-优惠券 2-钱
	public float amount; // 金额
	public String delFlag; // 删除状态：0-正常，1-删除
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
