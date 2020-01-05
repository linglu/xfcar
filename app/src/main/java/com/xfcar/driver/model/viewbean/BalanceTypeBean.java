package com.xfcar.driver.model.viewbean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linky
 */
public class BalanceTypeBean {

    public String price;
    public String name;

    public static List<BalanceTypeBean> mock() {
        String[] items = new String[]{"押金", "收入", "提现", "余额", "转单提成", "转单提成"};
        List<BalanceTypeBean> list = new ArrayList<>();
        for (String item : items) {
            BalanceTypeBean btb = new BalanceTypeBean();
            btb.name = item;
            btb.price = "2000.00";
            list.add(btb);
        }
        return list;
    }
}
