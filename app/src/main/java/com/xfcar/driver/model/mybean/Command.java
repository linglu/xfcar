package com.xfcar.driver.model.mybean;

public class Command {
	public String company; // 在绑定平台注册的公司名称
	public String carNo; // 车牌号
	public String macid; // 设备号（IMEI手机串号）
	public String command;

    public Command(String company, String carNo, String macid, String command) {
        this.company = company;
        this.carNo = carNo;
        this.macid = macid;
        this.command = command;
    }
    
    public static final String SN_SAFEOFF = "SN_SAFEOFF";//车门解锁
    public static final String SN_OPENTRUNK = "SN_OPENTRUNK";//座位锁
    public static final String SN_STALLING = "SN_STALLING";//断电
    public static final String SN_IGNITION = "SN_IGNITION";//上电
    public static final String SN_SAFEON = "SN_SAFEON";//车门上锁
    public static final String SN_FINDCAR = "SN_FINDCAR"; //寻车
}
