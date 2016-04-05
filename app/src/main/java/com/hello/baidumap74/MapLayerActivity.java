package com.hello.baidumap74;

import android.view.KeyEvent;

/**
 * Created by iwan on 16/4/4.
 */
public class MapLayerActivity extends BaseActivity {
    @Override
    public void init() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode){
            case KeyEvent.KEYCODE_1:
                map.setMapType(map.MAP_TYPE_NORMAL);    //显示普通地图
                map.setTrafficEnabled(false);           //交通图不显示
                break;
            case KeyEvent.KEYCODE_2:
                map.setMapType(map.MAP_TYPE_SATELLITE); //显示卫星图
                map.setTrafficEnabled(false);           //交通图不显示
                break;
            case KeyEvent.KEYCODE_3:
                map.setTrafficEnabled(true);
                break;
        }

        return super.onKeyDown(keyCode, event);
    }
}
