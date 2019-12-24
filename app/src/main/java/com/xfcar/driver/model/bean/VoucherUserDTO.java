package com.xfcar.driver.model.bean;

public class VoucherUserDTO {
	public int id; // 主键ID
	public int voucherId; // 代金卷ID
	public int userId; // 用户ID
	public String amount; // 金额
	public String status; // 是否使用：0-未使用  1-已使用  2-已过期
	public String validateStartDate; // 有效期开始时间
	public String validateEndDate; // 有效期结束时间
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
