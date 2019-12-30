package com.xfcar.driver.model.viewbean;

import com.xfcar.driver.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linky
 */
public class FunctionBean {

    public int id;
    public int imgRes;
    public String name;

    public static List<FunctionBean> mockWallet() {
        int[] imgs = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher};

        String[] names = new String[]{"余额", "收款", "查询", "转单提成", "推荐奖励"};

        List<FunctionBean> beans = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            FunctionBean fb = new FunctionBean();
            fb.id = i;
            fb.imgRes = imgs[i];
            fb.name = names[i];
            beans.add(fb);
        }

        return beans;
    }

    public static List<FunctionBean> mockMineBeans() {
        int[] imgs = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher};

        String[] names = new String[]{"我的钱包", "我的积分", "我的消息", "权限设置"};

        List<FunctionBean> beans = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            FunctionBean fb = new FunctionBean();
            fb.id = i;
            fb.imgRes = imgs[i];
            fb.name = names[i];
            beans.add(fb);
        }

        return beans;
    }

    public static List<FunctionBean> mockFuncBeans() {
        int[] imgs = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

        String[] names = new String[]{"购车租车", "车辆返租", "车辆管理", "租金充值", "邀请好友", "一键报警"};

        List<FunctionBean> beans = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            FunctionBean fb = new FunctionBean();
            fb.id = i;
            fb.imgRes = imgs[i];
            fb.name = names[i];
            beans.add(fb);
        }

        return beans;
    }

    public static List<FunctionBean> mockCarFuncBeans() {
        int[] imgs = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher};

        String[] names = new String[]{"一键开门", "一键上锁", "位置", "理赔", "维修", "车辆信息", "车辆安全"};

        List<FunctionBean> beans = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            FunctionBean fb = new FunctionBean();
            fb.id = i;
            fb.imgRes = imgs[i];
            fb.name = names[i];
            beans.add(fb);
        }

        return beans;
    }

}
