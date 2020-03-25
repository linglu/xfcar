package com.xfcar.driver.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteLine;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.model.bean.CarPositionBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.widget.OverlayManager;
import com.xfcar.driver.widget.WalkingRouteOverlay;

public class CarPositionActivity extends BaseActivity implements
        View.OnClickListener, SensorEventListener, OnGetRoutePlanResultListener {

    private MapView mMapView;
    private BaiduMap mBaiduMap;

    // 浏览路线节点相关
    int nodeIndex = -1; // 节点索引,供浏览节点时使用
    RouteLine route = null;
    MassTransitRouteLine massroute = null;
    OverlayManager routeOverlay = null;
    boolean useDefaultIcon = false;
    private TextView popupText = null; // 泡泡view

    boolean hasShownDialogue = false;
    // 搜索相关
    RoutePlanSearch mSearch = null;

    WalkingRouteResult nowResultwalk = null;
    private LocationClient mLocClient;
    private SensorManager mSensorManager;
    private MyLocationData locData;
    public MyLocationListener mMyListener = new MyLocationListener();

    boolean isFirstLoc = true; // 是否首次定位
    private Double lastX = 0.0;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccuracy;

    private boolean mIsWalking = false;

    // 车辆位置
    private LatLng mCarPos = null;

    // 我的位置
    private LatLng mMyPos = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_position);
        initView();

        mRequester.appCarGetCarInfoByUser(mInstance, mDataManager.getUserId(), new ResultCallback<CarInfoBean>() {
            @Override
            public void onSuccess(CarInfoBean carInfoBeans) {

                // 获取车辆位置信息
                mRequester.monitorPosition(mInstance, carInfoBeans.objectid, new ResultCallback<CarPositionBean>() {
                    @Override
                    public void onSuccess(CarPositionBean bean) {
                        mCarPos = new LatLng(bean.monitor.latitude, bean.monitor.longitude);

                        if (mMyPos != null && !mIsWalking) {

                            L.i("CarPositionActivity onSuccess show map");

                            mBaiduMap.clear();

                            mIsWalking = true;
                            PlanNode stNode = PlanNode.withLocation(mMyPos);
                            PlanNode enNode = PlanNode.withLocation(mCarPos);
                            mSearch.walkingSearch((new WalkingRoutePlanOption()).from(stNode).to(enNode));
                        }
                    }

                    @Override
                    public void onFail(String msg) {
                    }
                });
            }

            @Override
            public void onFail(String msg) {
                toastMsg(msg);

            }
        });
    }

    private void initView() {

        // 获取传感器管理服务
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        findViewById(R.id.iv_return_back).setOnClickListener(this);

        // 初始化地图
        mMapView = findViewById(R.id.map);
        mBaiduMap = mMapView.getMap();

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(mMyListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

        // 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(3), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onStop() {
        //取消注册传感器监听
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[Sensor.REPORTING_MODE_CONTINUOUS];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccuracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            mBaiduMap.setMyLocationData(locData);
        }
        lastX = x;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult result) {

        if (null == result) {
            return;
        }

        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            AlertDialog.Builder builder = new AlertDialog.Builder(CarPositionActivity.this);
            builder.setTitle("提示");
            builder.setMessage("检索地址有歧义，请重新设置。\n可通过getSuggestAddrInfo()接口获得建议查询信息");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
            return;
        }
        if (result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(CarPositionActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        } else {

            if (result.getRouteLines().size() > 0) {
                // 直接显示
                route = result.getRouteLines().get(0);
                WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
                routeOverlay = overlay;
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();

            } else {
                Log.d("route result", "结果数<0");
            }
        }
    }

    private class MyWalkingRouteOverlay extends WalkingRouteOverlay {

        public MyWalkingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
        }
    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }

            L.json(JSON.toJSONString(location));

            mMyPos = new LatLng(location.getLatitude(), location.getLongitude());
            if (mCarPos != null && !mIsWalking) {

                mBaiduMap.clear();

                L.i("MyLocationListener onReceiveLocation show map");
                mIsWalking = true;
                PlanNode stNode = PlanNode.withLocation(mMyPos);
                PlanNode enNode = PlanNode.withLocation(mCarPos);
                mSearch.walkingSearch((new WalkingRoutePlanOption()).from(stNode).to(enNode));
            }

//            locData = new MyLocationData.Builder()
//                    .accuracy(location.getRadius())
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(mCurrentDirection).latitude(location.getLatitude())
//                    .longitude(location.getLongitude()).build();
//            mBaiduMap.setMyLocationData(locData);

//            if (isFirstLoc) {
//                isFirstLoc = false;
//                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
//                MapStatus.Builder builder = new MapStatus.Builder();
//                builder.target(ll).zoom(18.0f);
//                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
}
