package com.hello.baidumap74;

import android.app.Activity;
import android.view.Gravity;
import android.widget.Toast;

/**
 *  Toast 显示方法
 */
public class BaseActivity extends Activity{

    /**
     * 在屏幕中央显示一个Toast
     * @param text
     */
    public void showToast(CharSequence text){
        Toast toast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

}
