package com.xfcar.driver.model.bean;

public class SysMenuEntity {
	public int menuId; // 菜单ID
	public int parentId; // 父菜单ID
	public String name; // 菜单名称
	public String permission; // 菜单权限标识
	public String path; // 前端URL
	public String icon; // 图标
	public String component; // VUE页面
	public int sort; // 排序值
	public String keepAlive; // 0-开启，1- 关闭
	public String type; // 菜单类型 （0菜单 1按钮）
	public String createTime; // 创建时间
	public String updateTime; // 更新时间
	public String delFlag; // 逻辑删除标记(0--正常 1--删除)
}
