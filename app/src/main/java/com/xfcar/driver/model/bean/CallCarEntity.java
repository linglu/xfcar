package com.xfcar.driver.model.bean;

public class CallCarEntity {
	public int id; // 主键ID
	public int userId; // 用户ID
	public String useTime; // useTime
	public String sourcePlace; // 出发地
	public String destination; // 目的地
	public String delFlag; // 删除状态：0-正常，1-删除
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
