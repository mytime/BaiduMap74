package com.hello.baidumap74.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.hello.baidumap74.BaseActivity;

/**
 * 抽取出来你的Toast方法
 */
public class Utils {

    /**
     * 在屏幕中央显示一个Toast
     */
    public static void showToast(Context context, CharSequence text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0); //设置重力：居中，XY轴为0；
        toast.show();

    }
}
