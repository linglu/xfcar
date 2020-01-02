package com.xfcar.driver.model.bean;

public class DiscountBuyEntity {
	public int id; // 主键ID
	public int userId; // 用户ID
	public String discountCode; // 套餐编码
	public String delFlag; // 删除状态：0-正常，1-删除
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
