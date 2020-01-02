package com.xfcar.driver.model.bean;

public class SysUserDTO {
	public int userId; // 主键ID
	public String username; // 用户登录名
	public String password; // 密码
	public String idCardName; // 身份证名称
	public String idCardNumber; // 身份证号码
	public String mobile; // 手机号
	public String email; // 邮箱
	public int deptId; // 部门ID
	public String lockFlag; // 0-正常，9-锁定
	public String wxOpenid; // 微信openid
	public String qqOpenid; // QQ openid
	public String userType; // 用户类型(10:司机; 20:乘客; 30:公司员工)
	public String contactName; // 紧急联系人
	public String contactMobile; // 紧急联系人手机号
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
