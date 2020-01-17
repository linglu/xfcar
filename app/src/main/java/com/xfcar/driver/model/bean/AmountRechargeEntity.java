package com.xfcar.driver.model.bean;

public class AmountRechargeEntity {
	public Integer id; // 为空时表示新增；不为空时表示修改
	public String tradeNo; // 订单编号
	public Integer userId; // 用户ID
	public String userName; // 充值用户名
	public String plan; // 套餐
	public String payType; // 充值类型(1：)
	public String payMode; // 充值方式 (1：现金，2：微信，3：支付宝，4：信用卡，5：借记卡)
	public String payVoucher; // 充值凭证
	public String payDate; // 支付日期
	public String business; // 付款业务
	public Float amount; // 金额
	public String voucherId; // 代金券ID
	public Float voucherAmount; // 代金券金额
	public String status; // 0-有效 1-无效
	public String delFlag; // 删除状态：0-正常，1-删除
	public Integer createBy; // 创建人
	public String createDate; // 创建时间
	public Integer updateBy; // 修改人
	public String updateDate; // 修改时间
}
