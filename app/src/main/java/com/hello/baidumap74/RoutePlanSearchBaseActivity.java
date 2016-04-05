package com.hello.baidumap74;

import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.RoutePlanSearch;

/**
 *  路线规划搜索父类
 */
public abstract class RoutePlanSearchBaseActivity extends BaseActivity implements OnGetRoutePlanResultListener {

    protected RoutePlanSearch routePlanSearch;

    @Override
    public void init() {
        routePlanSearch = RoutePlanSearch.newInstance();
        routePlanSearch.setOnGetRoutePlanResultListener(this);
        routePlanSearchInit();
    }
    /** 路线搜索初始化代码写在这个方法里 */
    protected abstract void routePlanSearchInit();
}
