package com.xfcar.driver.model.bean;

public class PaymentSourceEntity {
	public int id; // ID
	public int type; // 1-支付宝 2-微信 3-银行卡
	public String name; // 昵称
	public String account; // 银行卡号
	public String bankName; // 开户银行支行
	public String qrCode; // 二维码（支付宝/微信）
	public String contactMobile; // 手机号
	public String isEnable; // 启用状态 0:启用 1:禁用
	public String delFlag; // 删除状态 0:未删除 1：删除
	public String createTime; // 创建时间
	public int createBy; // 创建人
	public String updateTime; // 修改时间
	public int updateBy; // 修改人
}
