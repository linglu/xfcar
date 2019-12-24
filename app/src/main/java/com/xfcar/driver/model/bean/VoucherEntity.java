package com.xfcar.driver.model.bean;

public class VoucherEntity {
	public int id; // 为空时表示新增；不为空时表示修改
	public String voucherName; // 代金卷名称
	public float amount; // 金额
	public String status; // 是否使用：0-未使用  1-已使用  2-已过期
	public String validateStartDate; // 有效时间开始时间
	public String validateEndDate; // 有效时间结束时间
	public String remark; // 备注
	public String delFlag; // 删除状态：0-正常，1-删除
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
