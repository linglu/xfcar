package com.xfcar.driver;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

/**
 * @author Linky
 */
public class App extends LibApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        // 默认本地个性化地图初始化方法
        SDKInitializer.initialize(this);

        //设置您使用的坐标类型. 包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }
}
