package com.henryzu.henryzu.activity;


import android.os.Bundle;
import android.view.KeyEvent;

import com.henryzu.henryzu.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class BaseActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs( this)){
//            return;
//        }

        setContentView(R.layout.activity_base);
    }


    //监听返回
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            overridePendingTransition(R.anim.xin_left, R.anim.xout_right);
        }
        return super.onKeyDown(keyCode, event);
    }
}
