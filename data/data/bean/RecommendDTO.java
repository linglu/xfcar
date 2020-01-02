package com.xfcar.driver.model.bean;

public class RecommendDTO {
	public int id; // 为空时表示新增；不为空时表示修改
	public int userId; // 用户ID
	public String userName; // 用户名
	public String mobile; // 被推荐人手机号
	public String email; // 被推荐人邮箱
	public String idCardName; // 被推荐人身份证上的名字
	public String idCardNumber; // 被推荐人身份证号
	public String pageNumber; // 页数（默认1）
	public String pageSize; // 页码（默认10）
}
