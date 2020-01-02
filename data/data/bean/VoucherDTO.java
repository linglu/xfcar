package com.xfcar.driver.model.bean;

public class VoucherDTO {
	public int id; // 主键ID
	public String voucherName; // 代金卷名称
	public float amount; // 金额
	public String validateStartDate; // 有效时间开始时间
	public String validateEndDate; // 有效时间结束时间
	public String pageSize; // 页码（默认10）
	public String status; // 是否使用：0-未使用  1-已使用  2-已过期
	public String pageNumber; // 页数（默认1）
	public String remark; // 备注
}
