package com.hello.baidumap74;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by Administrator on 2016/4/5.
 */
public class MarkerOverlayActivity extends BaseActivity{
    LatLng gzFoc = new LatLng(23.112229, 113.330995); //广州塔坐标，维度在前，经度在后
    @Override
    public void init() {
        initMarker();
    }
    /** 初始化标志 */
    private void initMarker() {
        //第1个覆盖物
        MarkerOptions options = new MarkerOptions();    //初始化图标覆盖物选项
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.icon_eat);
        options.position(gzFoc)     //覆盖物的位置
                .title("广州搭")    //title
                .icon(icon)          //图标
                .draggable(true);   //图标可拖动
        map.addOverlay(options);

        //第2个覆盖物
        options = new MarkerOptions();    //初始化图标覆盖物选项
        options.position(new LatLng(gzFoc.latitude+0.001,gzFoc.longitude))     //覆盖物的位置
                .title("向北0.001")    //title
                .icon(icon)          //图标
                .draggable(true);   //图标可拖动
        map.addOverlay(options);
        //第3个覆盖物
        options = new MarkerOptions();    //初始化图标覆盖物选项
        options.position(new LatLng(gzFoc.latitude,gzFoc.longitude+0.001))     //覆盖物的位置
                .title("向东0.001")    //title
                .icon(icon)          //图标
                .draggable(true);   //图标可拖动
        map.addOverlay(options);
        //第4个覆盖物
        options = new MarkerOptions();    //初始化图标覆盖物选项
        options.position(new LatLng(gzFoc.latitude-0.001,gzFoc.longitude))     //覆盖物的位置
                .title("向南0.001")    //title
                .icon(icon)          //图标
                .draggable(true);   //图标可拖动
        map.addOverlay(options);
        //第5个覆盖物
        options = new MarkerOptions();    //初始化图标覆盖物选项
        options.position(new LatLng(gzFoc.latitude,gzFoc.longitude-0.001))     //覆盖物的位置
                .title("向西0.001")    //title
                .icon(icon)          //图标
                .draggable(true);   //图标可拖动
        map.addOverlay(options);
    }
}
