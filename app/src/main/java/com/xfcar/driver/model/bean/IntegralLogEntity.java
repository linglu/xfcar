package com.xfcar.driver.model.bean;

public class IntegralLogEntity {
	public int id; // 主键ID
	public String addTime; // 添加时间，这里为长时间格式
	public String content; //  操作说明
	public float integral; //  积分
	public String type; // 操作类型，包括reg：注册赠送，system：管理员操作, login:用户登录,order:订单获得
	public int userId; //  积分用户
	public String delFlag; // 删除状态：0-正常，1-删除
	public int updateBy; // 修改人
	public String updateDate; // 修改时间
}
