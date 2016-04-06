package com.hello.baidumap74;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 换乘路线搜索（公交地铁）
 */
public class TransitSearchActivity extends RoutePlanSearchBaseActivity{

    LatLng zxFoc = new LatLng(23.148398,113.330352);    //中信广场
    LatLng gzFoc = new LatLng(23.112229,113.330995);    //广州塔
    LatLng sdFoc = new LatLng(23.145929,113.323963);    //时代广场

    @Override
    protected void routePlanSearchInit() {
        routePlanSearch.transitSearch(getSearchParams());//1 搜索，2 结果回调

    }

    private TransitRoutePlanOption getSearchParams() {
        TransitRoutePlanOption option = new TransitRoutePlanOption();
        option.city("广州");  //城市内搜索
        option.policy(TransitRoutePlanOption.TransitPolicy.EBUS_TIME_FIRST);// 时间优先
        option.from(PlanNode.withLocation(gzFoc));  //设置起点
        option.to(PlanNode.withLocation(sdFoc));    //设置终点
        return option;
    }

    /**
     * 获取步行线路结果回调
     * @param result
     */
    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult result) {

    }

    /**
     * 2  获取公交、地铁线路结果回调
     * @param transitRouteResult
     */
    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
        TransitRouteOverlay overlay = new TransitRouteOverlay(map);
        map.setOnMarkerClickListener(overlay);
        List<TransitRouteLine> routeLines = transitRouteResult.getRouteLines(); //获取所有线路
        overlay.setData(routeLines.get(0));  //覆盖物设置到第一条线路上
        overlay.addToMap();                  //搜索结果添加到地图
        overlay.zoomToSpan();                //搜索结果显示在一个屏幕内
    }

    /**
     * 获取驾驶线路结果回调
     * @param drivingRouteResult
     */
    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {



    }
}
