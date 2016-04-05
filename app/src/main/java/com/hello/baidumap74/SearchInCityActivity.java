package com.hello.baidumap74;

import android.view.KeyEvent;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;

/**
 * 城市搜索
 */
public class SearchInCityActivity extends BaseActivityForPoiSearch {

    private int pageNum;

    @Override
    protected void poiSearchInit() {
        poiSearch.searchInCity(getSearchParams()); //城市搜索（搜索参数）

    }

    @Override
    public boolean onPoiClick(int i) {
        /** 获取其中一个Poi*/
        PoiInfo poiInfo = poiOverlay.getPoiResult().getAllPoi().get(i);
        /** 搜索该Poi的详细信息 */
        poiSearch.searchPoiDetail(getSearchDetailParams(poiInfo.uid));
        return true;
    }

    /**
     * 搜索某一个poi的详细信息
     */
    private PoiDetailSearchOption getSearchDetailParams(String poiUid) {
        PoiDetailSearchOption params = new PoiDetailSearchOption();
        params.poiUid(poiUid);

        return params;
    }

    /**
     * 搜索城市参数
     */
    private PoiCitySearchOption getSearchParams() {
        PoiCitySearchOption params = new PoiCitySearchOption();
        params.city("北京");          //搜索城市
        params.keyword("加油站");     //搜索内容
        params.pageCapacity(10);     //一页显示条数
        params.pageNum(pageNum);     //指定显示哪一页
        return params;
    }

    /**
     * 获取兴趣点详细信息, poi 详情查询结果回调
     */
    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        if (poiDetailResult == null || poiDetailResult.error != SearchResult.ERRORNO.NO_ERROR){
           showToast("没有搜到详细信息");
        }
        /** 真实开发中需要判断值是否为空 */
        showToast("营业时间："+poiDetailResult.getShopHours()+","+"电话："+poiDetailResult.getTelephone());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_1) {

            pageNum++; //获取下一页
            poiSearch.searchInCity(getSearchParams()); //城市搜索（搜索参数）
        }
        return super.onKeyDown(keyCode, event);
    }
}
