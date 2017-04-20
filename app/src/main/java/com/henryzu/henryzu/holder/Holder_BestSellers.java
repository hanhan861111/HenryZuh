package com.henryzu.henryzu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.henryzu.henryzu.R;

/**
 * Created by ${韩永光} on on 2017/3/24 0024 15:31..
 */

public class Holder_BestSellers extends RecyclerView.ViewHolder {

    public SimpleDraweeView home_line_hot_week_img;
    public TextView home_line_hot_week_tv_title;
    public TextView home_line_hot_week_tv_oldPrice;
    public TextView home_line_hot_week_tv_price;

    public Holder_BestSellers(View itemView) {
        super(itemView);
        home_line_hot_week_img = (SimpleDraweeView) itemView.findViewById(R.id.home_line_hot_week_img);
        home_line_hot_week_tv_title = (TextView) itemView.findViewById(R.id.home_line_hot_week_tv_title);
        home_line_hot_week_tv_oldPrice = (TextView) itemView.findViewById(R.id.home_line_hot_week_tv_oldPrice);
        home_line_hot_week_tv_price = (TextView) itemView.findViewById(R.id.home_line_hot_week_tv_price);
        home_line_hot_week_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //设置控件间的间距
       LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
      params.setMargins(18, 0, 0, 0);
       itemView.setLayoutParams(params);
    }
}
