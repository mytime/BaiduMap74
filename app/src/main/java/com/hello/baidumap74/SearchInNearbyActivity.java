package com.hello.baidumap74;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;

/**
 *  周边搜索
 */
public class SearchInNearbyActivity extends BaseActivityForPoiSearch {

    LatLng gzFoc = new LatLng(23.112229, 113.330995); //广州塔坐标，维度在前，经度在后

    @Override
    protected void poiSearchInit() {
        poiSearch.searchNearby(getSearchParams()); //周边搜索
    }

    /**
     * 周边搜索参数
     * @return
     */
    private PoiNearbySearchOption getSearchParams() {
        PoiNearbySearchOption params = new PoiNearbySearchOption();
        params.location(gzFoc);         //指定位置
        params.radius(1000);            //搜索半径
        params.keyword("中国银行");      //搜索内容

        return params;
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }
}
