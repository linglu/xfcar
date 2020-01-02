package com.xfcar.driver.model.bean;

public class InviteFriendEntity {
	public int id; // 主键ID
	public int userId; // 邀请用户ID
	public String username; // 用户名
	public String inviteFriendName; // 被邀请人姓名
	public String invitePhone; // 被邀人请电话
	public float reward; // 推荐奖励
	public String status; // 状态 0-未邀请成功  1-处理中  2-邀请成功
	public String delFlag; // 删除状态：0-正常，1-删除
	public String createDate; // 创建时间
	public int createBy; // 创建人
	public String updateDate; // 创建时间
	public int updateBy; // 修改人
}
