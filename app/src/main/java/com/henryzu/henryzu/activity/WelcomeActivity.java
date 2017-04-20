package com.henryzu.henryzu.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.henryzu.henryzu.MainActivity;
import com.henryzu.henryzu.R;
import com.henryzu.henryzu.adapter.VpAdapter_Wel;
import com.henryzu.henryzu.utils.CommonUtils;
import com.henryzu.henryzu.utils.LogUtils;

import java.util.ArrayList;


public class WelcomeActivity extends BaseActivity {

    private Button bt_wel;
    private LinearLayout ll_wel;
    private ViewPager vp_wel;
    private ArrayList<Integer> imgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //初始化数据
        initView();

        initLogin();

    }

    private void initData() {
        //设置ViewPager适配器
        vp_wel.setAdapter(new VpAdapter_Wel(this,imgList));
        //ViewPager监听
        vp_wel.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //最后一页时 按钮显示与隐藏
                bt_wel.setVisibility(position==imgList.size()-1? View.VISIBLE:View.GONE);
                for (int i = 0; i <ll_wel.getChildCount() ; i++) {
                    ImageView ll_img = (ImageView) ll_wel.getChildAt(i);
                    if(position==i){
                        ll_img.setImageResource(R.mipmap.abc_btn_radio_to_on_mtrl_015);
                    }else{
                        ll_img.setImageResource(R.mipmap.abc_btn_radio_to_on_mtrl_000);
                    }
                }
                //跳转按钮
                initButton();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initButton() {
        bt_wel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.startActivity(WelcomeActivity.this, MainActivity.class);
                CommonUtils.putBoolean("isFirstLogin",true);
            }
        });
    }

    private void initLogin() {
        //判断是否是第一次登陆
        boolean isFirstLogin = CommonUtils.getBoolean("isFirstLogin");
        LogUtils.d("~~~",isFirstLogin+"");
        if(isFirstLogin==false){
            initData();
            //初始化页面
        }else{
            CommonUtils.startActivity(WelcomeActivity.this, MainActivity.class);
        }

    }

    private void initView() {
        bt_wel = (Button) findViewById(R.id.bt_wel);
        ll_wel = (LinearLayout) findViewById(R.id.ll_wel);
        vp_wel = (ViewPager) findViewById(R.id.vp_wel);
        imgList = new ArrayList<>();
        imgList.add(R.mipmap.introductory1_xhdpi);
        imgList.add(R.mipmap.introductory2_xhdpi);
        imgList.add(R.mipmap.introductory3_xhdpi);
        imgList.add(R.mipmap.introductory4_xhdpi);
        //设置布局属性
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5, 5, 5, 5);
        //小点的设置
        for (int i = 0; i < imgList.size(); i++) {
            ImageView img_point=new ImageView(this);
            if(i==0){
                img_point.setImageResource(R.mipmap.abc_btn_radio_to_on_mtrl_015);
            }else{
                img_point.setImageResource(R.mipmap.abc_btn_radio_to_on_mtrl_000);
            }

           img_point.setLayoutParams(lp);
            //将添加的小圆点，加到布局中
           ll_wel.addView(img_point);
        }


    }

}
