package com.hello.baidumap74;

import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

/**
 * 抽取出来的PoiSearch
 */
public abstract class BaseActivityForPoiSearch extends BaseActivity implements OnGetPoiSearchResultListener {

    protected PoiSearch poiSearch;
    protected PoiOverlay poiOverlay;

    // 加final是禁止子类重写覆盖
    @Override
    public final void init() {
        //因为其他搜索也需要这个对象，所以抽取成父类，子类可以直接使用
        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(this);   //搜索结果监听器

        // poi 覆盖物对象
        poiOverlay = new PoiOverlay(map){
            @Override
            public boolean onPoiClick(int i) {
                PoiInfo poiInfo = getPoiResult().getAllPoi().get(i);
                showToast(poiInfo.name+","+poiInfo.address);
                return true;
            }
        };
        map.setOnMarkerClickListener(poiOverlay);       //覆盖物单击监听器

        poiSearchInit();
    }

    //强制子类使用这个方法（已经初始化过poiSearch）,防止出现poiSearch出现控制帧异常
    protected abstract void poiSearchInit();

    //因为其他搜索结果处理方法相同，所以抽取成父类
    /** 获取兴趣点, poi 查询结果回调*/
    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        if (poiResult == null || poiResult.error != SearchResult.ERRORNO.NO_ERROR){
            showToast("没有搜索到结果");
            return;
        }

        poiOverlay.setData(poiResult);  //把数据设置给覆盖物
        poiOverlay.addToMap();          //相当于循环执行map.addOverlay()，把所有结果用覆盖物标注出来
        poiOverlay.zoomToSpan();        //所有结果显示在一个屏幕中

    }

}
