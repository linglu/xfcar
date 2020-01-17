package com.xfcar.driver.model.bean;

public class UserEntity {
	public Integer userId; // 主键ID
	public String username; // 用户登录名
	public String sex; // 性别
	public String idCardName; // 身份证名称
	public String idCardNumber; // 身份证号码
	public String number; // 驾驶证编号
	public String kind; // 驾驶证类别
	public Float balance; // 余额（与购车租车表同步）
	public Float deposit; // 押金
	public Float integral; // 用户积分
	public Float userGoodsFee; // 该用户总商品消费金额，用于计算用户等级，消费越高，等级越高。
	public String mobile; // 手机号
	public String email; // 邮箱
	public String avatar; // 头像
	public Integer recommendUserId; // 推荐人ID
	public String recommendCode; // 推荐码
	public Integer deptId; // 部门ID
	public String lockFlag; // 0-正常，9-锁定
	public String wxOpenid; // 微信登录 openid
	public String qqOpenid; // QQ登录 openid
	public String validateCode; // 验证码
	public String invitationCode; // 邀请码
	public String userType; // 用户类型(10:司机; 20:乘客; 30:公司员工)
	public String contactName; // 紧急联系人
	public String contactMobile; // 紧急联系人手机号
	public String delFlag; // 0-正常，1-删除
	public Integer createBy; // 创建人
	public String createDate; // 创建时间
	public Integer updateBy; // 更新时间
	public String updateDate; // 修改时间
}
