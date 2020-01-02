package com.xfcar.driver.model.bean;

public class DiscountItemEntity {
	public int id; // 为空时表示新增，不为空时表示修改
	public String discountCode; // 套餐编码
	public String startDate; // 开始时间
	public String endDate; // 结束时间
	public String payDate; // 付款时间
	public String status; // 0：未付款，1：已付款
	public int phase; // 0：未付款，1：已付款
	public String delFlag; // 删除状态：0-正常，1-删除
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
