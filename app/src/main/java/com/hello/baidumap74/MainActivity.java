package com.hello.baidumap74;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.model.LatLng;

/**
 * 环境
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //检查网络和key
        registerSDKCheckReceiver();
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



}
