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
        String[] items = new String[]{"押金", "收入", "提现", "余额", "转单提成", "月租充值"};
        Float[] f = new Float[] {
                user.deposit,
                -1.0f,
                -1.0f,
                user.balance,
                -1.0f,
                -1.0f
        };

        List<BalanceTypeBean> list = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            BalanceTypeBean btb = new BalanceTypeBean();
            btb.name = items[i];
            btb.price = String.valueOf(f[i]);
            list.add(btb);
        }
        return list;
    }
}
