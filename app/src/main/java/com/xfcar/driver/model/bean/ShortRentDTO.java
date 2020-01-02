package com.xfcar.driver.model.bean;

public class ShortRentDTO {
	public int id; // 主键ID
	public int userId; // 用户ID
	public String username; // 用户名
	public String carNo; // 车牌号
	public String modelNo; // 转租车型号
	public String pageSize; // 页码（默认10）
	public String subletDate; // 转租时间
	public String pageNumber; // 页数（默认1）
	public String status; // 处理状态: 0-未处理  1-已处理
	public String delFlag; // 删除状态：0-正常，1-删除
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
