package com.hello.baidumap74;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.hello.baidumap74.utils.Utils;

/**
 * 抽取出来的BaseActivity,
 */
public abstract class BaseActivity extends Activity {

    private static final String TAG = "BaseActivity";
    protected MapView mMapView;
    protected BaiduMap map;

    /**
     * 这里加final是为了不让子类重写onCreate，只能使用init()抽象方法来初始化，
     * 预防这里有的类还没初始化就被调用，导致出现空指针异常
     * 使用public abstract void init()来规范引用onCreate
     */
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 1
         * 注意：在百度SDK各功能组件使用之前都需要调用
         * SDKInitializer.initialize(getApplicationContext());，
         * 因此我们建议该方法放在Application的初始化方法中
         * 新建MyApplication extends Application
         * 重写onCreate方法，在onCreate方法中调用
         * SDKInitializer.initialize(getApplicationContext())初始化百度地图
         *
         */

        setContentView(R.layout.activity_main);

        //初始化：获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        map = mMapView.getMap();//获取地图控制器


        //1 隐藏缩放按钮和比例尺
//        mMapView.showScaleControl(false);//是否显示比例尺控件
//        mMapView.showZoomControls(false);//否显示缩放控件


        //2 获取地图最大最小缩放级别
        float maxZoomLevel = map.getMaxZoomLevel();//获取地图最大缩放级别
        float minZoomLevel = map.getMinZoomLevel();//获取地图最小缩放级别
        Log.i(TAG, "最大：" + maxZoomLevel + " 最小：" + minZoomLevel);

        //3 设置地图中心点为广州塔，坐标: 经度113.330995, 维度23.112229
        LatLng gzFoc = new LatLng(23.112229, 113.330995); //维度在前，经度在后,位置颠倒地图不显示
        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(gzFoc);//设置地图新中心点
        map.setMapStatus(update);//改变地图状态

        //4 设置地图缩放级别为18级
        update = MapStatusUpdateFactory.zoomTo(18);
        map.setMapStatus(update);

        //6 隐藏指南针
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setCompassEnabled(false); //不显示指南针

        init();

    }

    /**
     * 这个抽象方法让子类实现
     */
    public abstract void init();


    /**
     * 在屏幕中央显示一个Toast
     *
     * @param text
     */
    public void showToast(CharSequence text) {
        Utils.showToast(this, text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }


}
