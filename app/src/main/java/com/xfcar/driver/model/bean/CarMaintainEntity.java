package com.xfcar.driver.model.bean;

public class CarMaintainEntity {
	public int id; // 主键ID(为空时表示新增；不为空时表示修改)
	public int userId; // 用户ID
	public String userName; // 用户名
	public String carNo; // 车牌号
	public float amount; // 金额
	public String maintainTime; // 保养时间
	public String maintainName; // 保养点名称
	public String maintainLocation; // 保养点位置
	public String repairItem; // 维修项
	public String delFlag; // 删除状态：0-正常，1-删除
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
