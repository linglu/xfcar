package com.xfcar.driver.model.adapterbean;

import java.util.ArrayList;
import java.util.List;

public class SentBillListBean {

    public String date;
    public String total;
    public String reward;

    public static List<SentBillListBean> mockList() {

        List<SentBillListBean> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            SentBillListBean sblb = new SentBillListBean();
            sblb.date = "8/13 20:20";
            sblb.total = "100";
            sblb.reward = "10";
            list.add(sblb);
        }

        return list;
    }
}
