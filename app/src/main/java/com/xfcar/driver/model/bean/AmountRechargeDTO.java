package com.xfcar.driver.model.bean;

public class AmountRechargeDTO {
	public int id; // 主键ID
	public int userId; // 用户ID
	public String userName; // 充值用户名
	public String plan; // 套餐
	public String pay_date; // 支付日期
	public String business; // 付款业务
	public float amount; // 金额
	public String voucherId; // 代金券ID
	public float voucherAmount; // 代金券金额
	public String payType; // 充值类型
	public String payMode; // 充值方式 (1：现金，2：微信，3：支付宝，4：信用卡，5：借记卡)
	public String payVoucher; // 充值凭证
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
