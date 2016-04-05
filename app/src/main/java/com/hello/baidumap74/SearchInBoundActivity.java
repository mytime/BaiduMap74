package com.hello.baidumap74;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

/**
 * 范围内搜索
 */
public class SearchInBoundActivity extends BaseActivityForPoiSearch {

    @Override
    protected void poiSearchInit() {
        poiSearch.searchInBound(getSearchParams());         //范围内检索，搜索结果需要监听器来接收
    }

    /**
     * 搜索参数
     */
    private PoiBoundSearchOption getSearchParams() {
        //  该POI范围内检索参数对象
        PoiBoundSearchOption params = new PoiBoundSearchOption();
        //  搜索范围，由一个西南点和一个东北点组成的范围
        LatLngBounds bounds = new LatLngBounds.Builder().include(new LatLng(40.048459, 116.302072))
                .include(new LatLng(40.050675, 116.304317)).build();
        params.bound(bounds); //搜索范围
        params.keyword("加油站");  //搜索内容
        return params;
    }

    /** 获取兴趣点详细信息,poi 详情查询结果回调*/
    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }


}
