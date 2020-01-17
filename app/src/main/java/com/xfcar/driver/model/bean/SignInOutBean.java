package com.xfcar.driver.model.bean;

public class SignInOutBean {
	public String code; // 验证码
	public String mobile; // 手机号
	public String password; // 密码

    public SignInOutBean(String mobile, String code) {
        this.code = code;
        this.mobile = mobile;
    }
}
