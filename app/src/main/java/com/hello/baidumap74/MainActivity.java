package com.hello.baidumap74;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

/**
 * 环境
 */
public class MainActivity extends BaseActivity {

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

        //1 隐藏缩放按钮和比例尺
        mMapView.showScaleControl(false);//是否显示比例尺控件
        mMapView.showZoomControls(false);//否显示缩放控件

        //检查网络和key
        registerSDKCheckReceiver();

        map = mMapView.getMap();//获取地图控制器




    }

    //监听百度Key配置是否正确
    private void registerSDKCheckReceiver() {
        receiver = new BroadcastReceiver() {
            @Override //接收器
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR.equals(action)){
                    showToast("网络错误");
                }else if (SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR.equals(action)){
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
