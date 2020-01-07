package com.xfcar.driver.model.viewbean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linky
 */
public class MsgBean {

    public String msg;
    public String time;

    public static List<MsgBean> mock() {
        String[] msgs = new String[]{
                "电量余量不足提醒：仅剩20公里电量，请及时充电",
                "租金到期提醒：仅剩3天小车租金即将到期，请及时充值",
                "违章提醒：您好，你的小车在某路段超速30%",
                "维修提醒：车辆需要维修，请及时维修",
                "保养提醒：车辆需要保养，请及时保养",
                "电量余量不足提醒：仅剩20公里电量，请及时充电",
                "电量余量不足提醒：仅剩20公里电量，请及时充电",
                "电量余量不足提醒：仅剩20公里电量，请及时充电",
        };

        List<MsgBean> list = new ArrayList<>();
        for (String msg1 : msgs) {
            MsgBean rib = new MsgBean();
            rib.msg = msg1;
            rib.time = "12:00";
            list.add(rib);
        }
        return list;
    }
}
