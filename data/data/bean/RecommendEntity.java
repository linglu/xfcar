package com.xfcar.driver.model.bean;

public class RecommendEntity {
	public int id; // 主键(为空时表示新增；不为空时表示修改)
	public int userId; // 用户ID
	public String userName; // 用户名
	public String mobile; // 被推荐人手机号
	public String email; // 被推荐人邮箱
	public String idCardName; // 被推荐人身份证上的名字
	public String idCardNumber; // 被推荐人身份证号
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateby; // 修改人
	public String updatedate; // 修改时间
	public String delFlag; // 删除状态：0-正常，1-删除
}
