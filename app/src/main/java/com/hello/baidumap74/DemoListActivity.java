package com.hello.baidumap74;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.baidu.mapapi.SDKInitializer;
import com.hello.baidumap74.utils.Utils;

/**
 * 导航列表
 */
public class DemoListActivity extends ListActivity {

    private BroadcastReceiver receiver;

    ClassAndName[] datas = {
            new ClassAndName(MainActivity.class, "HelloBaiduMap"),
            new ClassAndName(MapLayerActivity.class, "地图图层"),
            new ClassAndName(CircelOverlayActivity.class, "圆形覆盖物"),
            new ClassAndName(TextOverlayActivity.class, "文字覆盖物"),
            new ClassAndName(MarkerOverlayActivity.class, "Marker图标覆盖物"),
            new ClassAndName(SearchInBoundActivity.class, "范围内搜索"),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //检查网络和key
        registerSDKCheckReceiver();

        ArrayAdapter<ClassAndName> adapter =
                new ArrayAdapter<DemoListActivity.ClassAndName>(
                        this, android.R.layout.simple_list_item_1, datas);

        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
        //取出单击位置的ClassAndName;
        ClassAndName classAndName = (ClassAndName) l.getItemAtPosition(position);
        startActivity(new Intent(this, classAndName.cls)); //取字节码
    }

    //    实体类
    class ClassAndName {
        /**
         * 用于保存Activity的字节码
         */
        public Class<?> cls;
        /**
         * 用于保存Activity对应的名字
         */
        public String name;

        public ClassAndName(Class<?> cls, String name) {
            this.cls = cls;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }


    //监听百度Key配置是否正确
    private void registerSDKCheckReceiver() {
        receiver = new BroadcastReceiver() {
            @Override //接收器
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR.equals(action)) {
                    Utils.showToast(DemoListActivity.this,"网络错误");
                } else if (SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR.equals(action)) {
                    Utils.showToast(DemoListActivity.this,"key验证失败");
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
        //解除广播
        unregisterReceiver(receiver);
    }

}
