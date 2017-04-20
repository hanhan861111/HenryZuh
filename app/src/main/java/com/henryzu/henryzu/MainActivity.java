package com.henryzu.henryzu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.RadioGroup;

import com.henryzu.henryzu.factory.FragmentFactory;
import com.zhy.autolayout.AutoLayoutActivity;

public class MainActivity extends AutoLayoutActivity{

    private ViewPager vp_main;
    private RadioGroup rg_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //初始化控件
        vp_main = (ViewPager) findViewById(R.id.vp_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        //初始化viewPager
        initViewPager();
        //初始化RadioGroup
        initRadioGroup();
    }

    private void initRadioGroup() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                  switch (i){
                      case R.id.home_rb:
                          vp_main.setCurrentItem(0);
                          break;
                      case R.id.category_rb:
                          vp_main.setCurrentItem(1);
                          break;
                      case R.id.classroom_rb:
                          vp_main.setCurrentItem(2);
                          break;
                      case R.id.shoppingcart_rb:
                          vp_main.setCurrentItem(3);
                          break;
                      case R.id.mine_rb:
                          vp_main.setCurrentItem(4);
                          break;
                  }
            }
        });
    }

    private void initViewPager() {
        vp_main.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return FragmentFactory.getFragment(position);
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
    }
}
