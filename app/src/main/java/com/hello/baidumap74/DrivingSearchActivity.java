package com.hello.baidumap74;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 驾驶搜索
 */
public class DrivingSearchActivity extends RoutePlanSearchBaseActivity{

    LatLng zxFoc = new LatLng(23.148398,113.330352);    //中信广场
    LatLng gzFoc = new LatLng(23.112229,113.330995);    //广州塔
    LatLng sdFoc = new LatLng(23.145929,113.323963);    //时代广场

    @Override
    protected void routePlanSearchInit() {
        routePlanSearch.drivingSearch(getSearchParams());

    }

    /**
     * 驾驶搜索参数
     * @return 规划后的结果
     */
    private DrivingRoutePlanOption getSearchParams() {
        DrivingRoutePlanOption params = new DrivingRoutePlanOption();//驾驶线路对象
        params.from(PlanNode.withLocation(zxFoc));                   //出发地点
        List<PlanNode> nodes = new ArrayList<PlanNode>();            //经过地点集合
        nodes.add(PlanNode.withLocation(sdFoc));                     //添加一个经过节点，可以是多个
        params.passBy(nodes);                                        //经过地点
        params.to(PlanNode.withLocation(gzFoc));                     //最终到达地点
        return params;
    }

    /**
     * 获取步行线路结果回调
     * @param result
     */
    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult result) {

    }

    /**
     * 获取公交、地铁线路结果回调
     * @param transitRouteResult
     */
    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    /**
     * 获取驾驶线路结果回调
     * @param drivingRouteResult
     */
    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
        DrivingRouteOverlay overlay = new DrivingRouteOverlay(map);
        map.setOnMarkerClickListener(overlay);
        List<DrivingRouteLine> routeLines = drivingRouteResult.getRouteLines(); //获取所有线路
        overlay.setData(routeLines.get(0));  //覆盖物设置到第一条线路上
        overlay.addToMap();                  //搜索结果添加到地图
        overlay.zoomToSpan();                //搜索结果显示在一个屏幕内


    }
}
