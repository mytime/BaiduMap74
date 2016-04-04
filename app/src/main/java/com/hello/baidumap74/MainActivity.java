package com.hello.baidumap74;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.map.MapView;

/**
 * 环境
 */
public class MainActivity extends AppCompatActivity {

    private MapView mMapView;

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

        //2 获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
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
