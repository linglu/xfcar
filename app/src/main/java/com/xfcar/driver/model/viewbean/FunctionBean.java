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
        int[] imgs = new int[]{R.mipmap.rent_car, R.mipmap.yaoqinghaoyou_2, R.mipmap.cheliangguanli_1,
                R.mipmap.chongzhi_6, R.mipmap.yijianbaojing_1};

        String[] names = new String[]{"购车租车", "邀请好友", "车辆管理", "租金充值", "一键报警"};

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
        int[] imgs = new int[]{
                R.mipmap.yijiankaimen,
                R.mipmap.yijianshangsuo,
                R.mipmap.lanyayaoshi,
                R.mipmap.weizhixinxi,
                R.mipmap.lipei,
                R.mipmap.weihuweixu,
                R.mipmap.cheliangxinxi,
                R.mipmap.chelianganquan};

        String[] names = new String[]{"一键开门", "一键上锁","蓝牙钥匙", "位置信息", "理赔财款", "维护维修",
                "车辆信息", "车辆安全"};

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
