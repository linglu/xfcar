package com.xfcar.driver.model.bean;

public class Command {
	public String company; // 在绑定平台注册的公司名称
	public String carNo; // 车牌号
	public String macid; // 设备号（IMEI手机串号）
	public String command; // "SN_FINDCAR"//寻车;"SN_OPENTRUNK"//座位锁;"SN_STALLING"//断电;"SN_IGNITION"//上电;"SN_SAFEON"//车门上锁;"SN_SAFEOFF"//车门解锁;
}
