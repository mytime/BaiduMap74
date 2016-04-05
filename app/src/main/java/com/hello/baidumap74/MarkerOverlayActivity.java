package com.hello.baidumap74;

import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * Marker标志覆盖物
 */
public class MarkerOverlayActivity extends BaseActivity {
    LatLng gzFoc = new LatLng(23.112229, 113.330995); //广州塔坐标，维度在前，经度在后
    private TextView tv_title;
    private View pop;

    @Override
    public void init() {
        initMarker();
        //Marker点击监听器
        map.setOnMarkerClickListener(mOnMarkerClickListener);
        //Marker拖拽监听器

        map.setOnMarkerDragListener(mOnMarkerDragListener);
    }

    /**
     * Marker拖拽监听器
     */
    BaiduMap.OnMarkerDragListener mOnMarkerDragListener = new BaiduMap.OnMarkerDragListener() {
        @Override
        public void onMarkerDrag(Marker marker) {
            mMapView.updateViewLayout(pop, createLayoutParams(marker.getPosition()));
        }

        @Override
        public void onMarkerDragEnd(Marker marker) {
            mMapView.updateViewLayout(pop, createLayoutParams(marker.getPosition()));
        }

        @Override
        public void onMarkerDragStart(Marker marker) {
            mMapView.updateViewLayout(pop, createLayoutParams(marker.getPosition()));
        }
    };
    /**
     * Marker点击监听器
     */
    BaiduMap.OnMarkerClickListener mOnMarkerClickListener = new BaiduMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            //显示一个泡泡
            if (pop == null) {
                //实例化一个新布局
                pop = View.inflate(MarkerOverlayActivity.this, R.layout.pop, null);    //填充的布局
                tv_title = (TextView) pop.findViewById(R.id.tv_title);
                mMapView.addView(pop, createLayoutParams(marker.getPosition()));
                return true;
            } else {
                //更新布局
                mMapView.updateViewLayout(pop, createLayoutParams(marker.getPosition()));
            }
            tv_title.setText(marker.getTitle());
            return true;
        }
    };

    /**
     * 布局构建器
     *
     * @param position 经纬度
     * @return 构建结果
     */
    private MapViewLayoutParams createLayoutParams(LatLng position) {
        MapViewLayoutParams.Builder builder = new MapViewLayoutParams.Builder();    //初始化一个布局构建器
        builder.layoutMode(MapViewLayoutParams.ELayoutMode.mapMode);    //指定坐标方式为经纬度
        builder.position(position); //设置坐标的位置
        builder.yOffset(-65);   //向上偏移量
        MapViewLayoutParams build = builder.build();    //执行构建
        return build;
    }

    /**
     * 初始化标志
     */
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
        options.position(new LatLng(gzFoc.latitude + 0.001, gzFoc.longitude))     //覆盖物的位置
                .title("向北0.001")    //title
                .icon(icon)          //图标
                .draggable(true);   //图标可拖动
        map.addOverlay(options);
        //第3个覆盖物
        options = new MarkerOptions();    //初始化图标覆盖物选项
        options.position(new LatLng(gzFoc.latitude, gzFoc.longitude + 0.001))     //覆盖物的位置
                .title("向东0.001")    //title
                .icon(icon)          //图标
                .draggable(true);   //图标可拖动
        map.addOverlay(options);
        //第4个覆盖物
        options = new MarkerOptions();    //初始化图标覆盖物选项
        options.position(new LatLng(gzFoc.latitude - 0.001, gzFoc.longitude))     //覆盖物的位置
                .title("向南0.001")    //title
                .icon(icon)          //图标
                .draggable(true);   //图标可拖动
        map.addOverlay(options);
        //第5个覆盖物
        options = new MarkerOptions();    //初始化图标覆盖物选项
        options.position(new LatLng(gzFoc.latitude, gzFoc.longitude - 0.001))     //覆盖物的位置
                .title("向西0.001")    //title
                .icon(icon)          //图标
                .draggable(true);   //图标可拖动
        map.addOverlay(options);
    }
}
