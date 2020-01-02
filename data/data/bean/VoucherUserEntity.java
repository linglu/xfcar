package com.xfcar.driver.model.bean;

public class VoucherUserEntity {
	public int id; // 主键ID
	public int voucherId; // 代金卷ID
	public String voucherSn; // 代金卷SN
	public int userId; // 用户ID
	public String amount; // 金额
	public String status; // 是否使用：0-未使用  1-已使用  2-已过期
	public String delFlag; // 删除状态：0-正常，1-删除
	public String validateStartDate; // 有效期开始时间
	public String validateEndDate; // 有效期结束时间
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
