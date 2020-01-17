package com.xfcar.driver.model.bean;

public class CarMaintainEntity {
	public Integer id; // 主键ID(为空时表示新增；不为空时表示修改)
	public Integer userId; // 用户ID
	public String userName; // 用户名
	public String carNo; // 车牌号
	public Float amount; // 金额
	public String maintainTime; // 保养时间
	public String maintainName; // 保养点名称
	public String maintainLocation; // 保养点位置
	public String repairItem; // 维修项
	public String delFlag; // 删除状态：0-正常，1-删除
	public Integer createBy; // 创建人
	public String createDate; // 创建时间
	public Integer updateBy; // 修改人
	public String updateDate; // 修改时间
}
