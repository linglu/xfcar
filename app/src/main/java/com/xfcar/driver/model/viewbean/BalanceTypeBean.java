package com.xfcar.driver.model.viewbean;

import com.xfcar.driver.model.bean.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linky
 */
public class BalanceTypeBean {

    public String price;
    public String name;

    public static List<BalanceTypeBean> mock(UserEntity user) {
        String[] items = new String[]{"租车押金", "待交月租", "转单收入", "积分收入", "推荐收入"};
        String[] f = new String[] {
                "888",
                "888",
                "888",
                "888",
                "888"
        };

        List<BalanceTypeBean> list = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            BalanceTypeBean btb = new BalanceTypeBean();
            btb.name = items[i];
            btb.price = f[i];
            list.add(btb);
        }
        return list;
    }
}
