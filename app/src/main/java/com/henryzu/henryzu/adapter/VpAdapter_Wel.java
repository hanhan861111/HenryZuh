package com.henryzu.henryzu.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by 韩永光
 * on 2017/3/20 13:57.
 */
public class VpAdapter_Wel extends PagerAdapter {
    private final Context context;
    private final ArrayList<Integer> imgList;
    private ImageView img;

    public VpAdapter_Wel(Context context, ArrayList<Integer> imgList) {
        this.context = context;
        this.imgList = imgList;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        img = new ImageView(context);
        img.setImageResource(imgList.get(position));
        //设置图片全屏展示
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
