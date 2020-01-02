package com.xfcar.driver.model.bean;

public class DocumentEntity {
	public int id; // 主键ID
	public String title; //  文章标题
	public String mark; //  文章标识
	public String content; //  文章内容
	public String addTime; // 添加时间
	public String delFlag; // 删除状态：0-正常，1-删除
	public int createBy; // 创建人
	public int updateBy; // 修改人
	public String updateDate; // 创建时间
}
