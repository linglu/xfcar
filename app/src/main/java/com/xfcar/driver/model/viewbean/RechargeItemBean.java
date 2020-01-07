package com.xfcar.driver.model.viewbean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linky
 */
public class RechargeItemBean {

    public String amount;
    public String item1;
    public String item2;

    public static List<RechargeItemBean> mock() {
        List<RechargeItemBean> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            RechargeItemBean rib = new RechargeItemBean();
            rib.amount = "1000.0";
            rib.item1 = "包月9折";
            rib.item2 = "续费1天";
            list.add(rib);
        }
        return list;
    }
}