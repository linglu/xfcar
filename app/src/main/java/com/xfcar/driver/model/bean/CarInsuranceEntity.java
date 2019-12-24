package com.xfcar.driver.model.bean;

public class CarInsuranceEntity {
	public int id; // 为空时表示新增；不为空时表示修改
	public int userId; // 用户ID
	public String userName; // 用户名
	public String idCardNumber; // 身份证号
	public String carNo; // 车牌号
	public float amount; // 理赔金额
	public String accidentTime; // 事故时间
	public String accidentLocation; // 事故地点
	public String repairLocation; // 维修地点
	public int repairDays; // 维修天数
	public String delFlag; // 删除状态：0-正常，1-删除
	public int createBy; // 创建人
	public String createDate; // 创建时间
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
