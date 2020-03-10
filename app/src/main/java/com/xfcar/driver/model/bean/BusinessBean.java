package com.xfcar.driver.model.bean;

import com.xfcar.driver.model.adapterbean.PagerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linky
 */
public class BusinessBean {

    public Integer id;
    public Integer userId;
    public String businessType;
    public String typeDesc;
    public String title;
    public String content;
    public String picUrl;
    public String delFlag;
    public Integer createBy;
    public String createDate;
    public Integer updateBy;
    public String updateDate;

    public static List<BusinessBean> mockBusinessBeans() {

        String[] a = new String[] {
                "https://iph.href.lu/720x300?text=%E6%9A%82%E6%97%A0%E5%9B%BE%E7%89%87",
                "https://iph.href.lu/720x300?text=%E6%9A%82%E6%97%A0%E5%9B%BE%E7%89%87"
        };

        List<BusinessBean> pbs = new ArrayList<>();
        for (String aa : a) {
            BusinessBean pb = new BusinessBean();
            pb.id = -1;
            pb.picUrl = aa;
            pbs.add(pb);
        }

        return pbs;
    }
}
