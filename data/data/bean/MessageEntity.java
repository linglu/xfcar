package com.xfcar.driver.model.bean;

public class MessageEntity {
	public int id; // 主键
	public int userId; // 用户ID
	public String msgType; // 消息类型
	public String msgTitle; // 消息标题
	public String msgContent; // 消息内容
	public String status; // 消息状态(默认为0，0：新消息  1：使用中 -1：为过期)
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
	public String delFlag; // 删除状态：0-正常，1-删除
}
