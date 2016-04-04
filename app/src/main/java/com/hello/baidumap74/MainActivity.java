package com.hello.baidumap74;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

/**
 * 环境
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private MapView mMapView;
    private BroadcastReceiver receiver;
    private BaiduMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 1
         * 注意：在百度SDK各功能组件使用之前都需要调用
         * SDKInitializer.initialize(getApplicationContext());，
         * 因此我们建议该方法放在Application的初始化方法中
         * 新建MyApplication extends Application,
         * 重写onCreate方法，在onCreate方法中调用
         * SDKInitializer.initialize(getApplicationContext())初始化百度地图
         *
         */

        setContentView(R.layout.activity_main);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        map = mMapView.getMap();//获取地图控制器

        //1 隐藏缩放按钮和比例尺
//        mMapView.showScaleControl(false);//是否显示比例尺控件
//        mMapView.showZoomControls(false);//否显示缩放控件

        //检查网络和key
        registerSDKCheckReceiver();

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

        //5 更新地图状态


    }
    //5 更新地图状态

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        MapStatusUpdate update = null;
        switch (keyCode) {
            case KeyEvent.KEYCODE_1://缩小
                update = MapStatusUpdateFactory.zoomOut();
                break;
            case KeyEvent.KEYCODE_2://放大
                update = MapStatusUpdateFactory.zoomIn();
                break;
            case KeyEvent.KEYCODE_3://每次在原来的基础上再旋转30度(0~360)
                MapStatus currentMapStatus = map.getMapStatus(); //地图当前状态
                float rotate = currentMapStatus.rotate + 30; //当前状态再加30度
                Log.i(TAG, "角度：" + rotate);
                MapStatus mapStatus = new MapStatus.Builder().rotate(rotate).build();
                update = MapStatusUpdateFactory.newMapStatus(mapStatus);
                break;
            case KeyEvent.KEYCODE_4://每次在原来的基础上再俯仰-5度(0~45);
                float overlook = map.getMapStatus().overlook;
                float v = overlook-5;
                mapStatus = new MapStatus.Builder().overlook(v).build();
                update = MapStatusUpdateFactory.newMapStatus(mapStatus);
                break;
            case KeyEvent.KEYCODE_5://移动 到 中信广场 坐标 113.330352,23.148398
                LatLng zxFoc = new LatLng(23.148398,113.330352);
                update = MapStatusUpdateFactory.newLatLng(zxFoc);
                map.animateMapStatus(update,2000); //用动画形式移动坐标
                return super.onKeyDown(keyCode, event); //不再往下执行。
        }

        map.setMapStatus(update);
        return super.onKeyDown(keyCode, event);
    }

    //监听百度Key配置是否正确
    private void registerSDKCheckReceiver() {
        receiver = new BroadcastReceiver() {
            @Override //接收器
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR.equals(action)) {
                    showToast("网络错误");
                } else if (SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR.equals(action)) {
                    showToast("key验证失败");
                }

            }
        };
        //开始一个过滤器
        IntentFilter filter = new IntentFilter();
        //监听网络错误
        filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        //监听百度地图SDK的key是否错误
        filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        //注册接收器
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        //解除广播
        unregisterReceiver(receiver);
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
