package com.xfcar.driver.model.bean;

public class TrafficViolationEntity {
	public int id; // 页数（默认1）
	public String driver; // 驾驶员
	public String carNo; // 车牌号
	public String date; // 违章时间
	public String site; // 违规地点
	public String violation; // 违章行为
	public String penalty; // 处罚结果
	public String delFlag; // 删除状态：0-正常，1-删除
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
