package com.geowind.hunong.map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.geowind.hunong.R;
import com.geowind.hunong.utils.MyConstants;
import com.geowind.hunong.utils.SpTools;

import java.util.ArrayList;

/**
 * Created by zhangwen on 16-7-9.
 */
public class BaiduMapActivity extends Activity {

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private Spinner mSpinner;
    private ArrayList<String> datas = new ArrayList<String>();
    private Overlay mTextOverlay;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private Button bt_testArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
        // 注意该方法要再setContentView方法之前实现
        /**
         * 发送key给服务 校验key是否正确
         */
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.baidumap);
        /**
         * 初始化百度定位
         */
        mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
        mLocationClient.registerLocationListener(myListener); // 注册监听函数
        initView();
        initData();


    }


    /**
     * 初始化数据
     */
    private void initData() {
        System.out.println("debug");
        initLocation();
        mLocationClient.start();

    }

    /**
     * 初始化定位参数
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
        // 定位频率 必须大于1000 单位毫秒
        int span = 5000;
        option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);// 可选，默认false,设置是否使用gps
        option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);// 可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
        option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);// 可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);

    }

    /**
     * 初始化view
     */
    private void initView() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mSpinner = (Spinner) findViewById(R.id.spinner);
        bt_testArea=new Button(getApplicationContext());
        datas.add("功能");
        datas.add("开启交通图");
        datas.add("关闭交通图");
        datas.add("开启卫星图");
        datas.add("关闭卫星图");
        datas.add("添加文字");
        datas.add("移除文字");
        datas.add("回到我的位置");

        mSpinner.setAdapter(new ArrayAdapter<>(this,R.layout.item_spinner_map, datas));
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        mBaiduMap.setTrafficEnabled(true);
                        break;
                    case 2:
                        mBaiduMap.setTrafficEnabled(false);
                        break;
                    case 3:
                        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                        break;
                    case 4:
                        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                        break;
                    case 5:
                        addText();
                        break;
                    case 6:
                        mTextOverlay.remove();
                        break;
                    case 7:
                       showMyLoction();
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 添加文字
     */
    protected void addText() {
        TextOptions textOptions = new TextOptions();
        LatLng latLng = new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLatitude());
        textOptions.position(latLng);
        textOptions.text(mCurrentLocation.getAddrStr());
        textOptions.fontSize(24);
        textOptions.bgColor(0xFF0000);
        mTextOverlay = mBaiduMap.addOverlay(textOptions);



    }

    private BDLocation mCurrentLocation;
    private boolean isFirst = true;

    /**
     * 注册监听事件
     */
    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            mCurrentLocation=location;
            if (isFirst) {
                showMyLoction();//如果是第一次定位，显示到当前的位置
                addMark();//在当ia前定位加上标注
                isFirst = false;
                //保存当前位置
                SpTools.setString(getApplicationContext(), MyConstants.LOCATION,mCurrentLocation.getAddrStr());
            }
        }
    }

    /**
     * 添加标注
     */

    private void addMark() {
        MarkerOptions mark=new MarkerOptions();
        LatLng latLng=new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
        mark.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_start))
                .position(latLng);
        mBaiduMap.addOverlay(mark);
    }

    /**
     * 定位到当前的位置
     */

    private void showMyLoction() {
        // 让地图中心跑到我的真实位置处
        LatLng latLng = new LatLng(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude());
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(latLng, 21);
        // 没有动画效果
        // mBaiduMap.setMapStatus(mapStatusUpdate);
        mBaiduMap.animateMapStatus(mapStatusUpdate, 1000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mLocationClient.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    }

