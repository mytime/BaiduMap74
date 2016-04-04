package com.hello.baidumap74;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * 需要在Manifast中注册
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SDKInitializer.initialize(getApplicationContext());

    }
}
