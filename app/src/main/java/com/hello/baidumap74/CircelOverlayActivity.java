package com.hello.baidumap74;

import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by iwan on 16/4/4.
 */
public class CircelOverlayActivity extends BaseActivity{

    LatLng gzFoc = new LatLng(23.112229, 113.330995); //广州塔坐标，维度在前，经度在后,位置颠倒地图不显示

    @Override
    public void init() {
        CircleOptions options = new CircleOptions();    //2 开始一个圆形覆盖物参数
        options.center(gzFoc)   //设置圆心是广州塔
                .radius(50)   //半径是50米
                .stroke(new Stroke(20,0x55FF0000))       //线条宽度，颜色（16进制的颜色）
                .fillColor(0x55FF0000);   //原型的填充颜色
        map.addOverlay(options);   //1 添加一个覆盖物,arg:传入参数类型
    }
}
