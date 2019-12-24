package com.xfcar.driver.model.bean;

public class TrafficViolationDTO {
	public int id; // 主键ID
	public String driver; // 驾驶员
	public String carNo; // 车牌号
	public String date; // 违章时间
	public String site; // 违规地点
	public String violation; // 违章行为
	public String penalty; // 处罚结果
	public String delFlag; // 删除状态：0-正常，1-删除
	public String pageSize; // 页码（默认10）
	public String pageNumber; // 页数（默认1）
}
