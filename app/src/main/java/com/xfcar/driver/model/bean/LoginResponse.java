package com.xfcar.driver.model.bean;

import com.xfcar.driver.model.adapterbean.CarInfoBean;

/**
 * @author linky
 */
public class LoginResponse {

    public String XF_TOKEN;
    public String lock;
    public CarInfoBean carInfo;
    public UserBean user;
}
