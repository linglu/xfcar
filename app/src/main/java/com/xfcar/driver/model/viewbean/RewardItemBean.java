package com.xfcar.driver.model.viewbean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linky
 */
public class RewardItemBean {

    public String type;
    public String amount;
    public String receipt;
    public String date;

    public static List<RewardItemBean> mock() {
        List<RewardItemBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RewardItemBean rib = new RewardItemBean();
            rib.type = "转单收入";
            rib.amount = "100";
            rib.receipt = "收款方: xxx";
            rib.date = "2020-1-1";
            list.add(rib);
        }
        return list;
    }
}
