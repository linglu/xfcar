package com.xfcar.driver.model.bean;

import java.util.List;

public class SysUserEntity {
	public int userId; // 主键ID
	public String username; // 用户登录名
	public String password; // 密码
	public String idCardName; // 身份证名称
	public String idCardNumber; // 身份证号码
	public float balance; // 余额
	public float deposit; // 押金
	public String mobile; // 手机号
	public String email; // 邮箱
	public int recommendUserId; // 推荐人ID
	public String recommendCode; // 推荐码
	public String salt; // 随机盐
	public String avatar; // 头像
	public int deptId; // 部门ID
	public String lockFlag; // 0-正常，9-锁定
	public String delFlag; // 0-正常，1-删除
	public String wxOpenid; // 微信openid
	public String qqOpenid; // QQ openid
	public String validateCode; // 验证码
	public String invitationCode; // 邀请码
	public String userType; // 用户类型(10:司机; 20:乘客; 30:公司员工)
	public String contactName; // 紧急联系人
	public String contactMobile; // 紧急联系人手机号
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
	public List<String> dept; // 部门
	public List<SysRoleEntity> roles; // 角色列表
	public List<SysMenuEntity> menu; // 菜单列表
	public List<String> permission; // 权限列表
}
