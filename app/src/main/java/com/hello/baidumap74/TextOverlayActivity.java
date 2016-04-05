package com.hello.baidumap74;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * 文本覆盖物
 */
public class TextOverlayActivity extends BaseActivity {
//    LatLng latLng = new LatLng(23.134614,113.262679);//光孝寺
    LatLng gzFoc = new LatLng(23.112229, 113.330995); //广州塔坐标，维度在前，经度在后
    @Override
    public void init() {
        TextOptions options = new TextOptions();//初始化文本选项
        options.position(gzFoc) //文本显示的位置（维度，经度），
                .text("广州塔")    //显示文本的内容
                .fontSize(40)   //文字大小
                .fontColor(0XFF000000)  //文字颜色
                .bgColor(0X55FF0000)    //背景颜色
                .rotate(30);    //旋转角度
        map.addOverlay(options);    //添加覆盖物

    }
}
