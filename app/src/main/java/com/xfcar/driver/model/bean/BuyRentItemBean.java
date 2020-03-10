package com.xfcar.driver.model.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linky
 */
public class BuyRentItemBean {

    public String value1;
    public String value2;
    public String value3;

    public BuyRentItemBean(String value1, String value2, String value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public static List<BuyRentItemBean> mockBuy() {
        List<BuyRentItemBean> bribs = new ArrayList<>();
        bribs.add(new BuyRentItemBean("1万", "2000元", "36期"));
        return bribs;
    }

    public static List<BuyRentItemBean> mockRent() {

        List<BuyRentItemBean> bribs = new ArrayList<>();
        for (int i = 3; i < 36; i += 3) {
            bribs.add(new BuyRentItemBean("7000", "2000元", i + "期"));
        }
        return bribs;
    }
}
