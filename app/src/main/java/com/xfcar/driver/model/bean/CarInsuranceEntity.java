package com.xfcar.driver.model.bean;

public class CarInsuranceEntity {
	public Integer id; // 为空时表示新增；不为空时表示修改
	public Integer userId; // 用户ID
	public String userName; // 用户名
	public String idCardNumber; // 身份证号
	public String carNo; // 车牌号
	public Float amount; // 理赔金额
	public String accidentTime; // 事故时间
	public String accidentLocation; // 事故地点
	public String repairTime; // 维修时间
	public String repairLocation; // 维修地点
	public Integer repairDays; // 维修天数
	public String delFlag; // 删除状态：0-正常，1-删除
	public Integer createBy; // 创建人
	public String createDate; // 创建时间
	public Integer updateBy; // 修改人
	public String updateDate; // 修改时间
}
