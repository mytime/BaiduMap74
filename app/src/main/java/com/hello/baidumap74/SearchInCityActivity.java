package com.hello.baidumap74;

import android.view.KeyEvent;

import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;

/**
 *  城市搜索
 */
public class SearchInCityActivity extends BaseActivityForPoiSearch {

    private int pageNum;
    @Override
    protected void poiSearchInit() {
        poiSearch.searchInCity(getSearchParams()); //城市搜索（搜索参数）
    }

    private PoiCitySearchOption getSearchParams() {
        PoiCitySearchOption params = new PoiCitySearchOption();
        params.city("广州市");          //搜索城市
        params.keyword("加油站");     //搜索内容
        params.pageCapacity(10);       //一页显示条数
        params.pageNum(pageNum);      //指定显示哪一页
        return params;
    }

    /** 获取兴趣点详细信息,poi 详情查询结果回调*/
    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_1){
            //获取下一页
            pageNum++;
            poiSearch.searchInCity(getSearchParams()); //城市搜索（搜索参数）
        }
        return super.onKeyDown(keyCode, event);
    }
}
