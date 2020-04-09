package com.xfcar.driver.model.bean;

public class ShortRentEntity {
	public Integer id; // 主键ID
	public Integer userId; // 用户ID
	public String username; // 用户名
	public String carNo; // 车牌号
	public String modelNo; // 转租车型号
	public String subletDate; // 转租时间
	public String status; // 处理状态: 0-未处理  1-已处理
	public String delFlag; // 删除状态：0-正常，1-删除
	public Integer createBy; // 创建人
	public String createDate; // 创建时间
	public Integer updateBy; // 修改人
	public String updateDate; // 修改时间
	public String appointmentType; // 预约类型(10:返租预约;  20:买车预约;  30:租车预约)
	public String remark; 	// 2020-04-05~2020-04-15
}
