package com.henryzu.henryzu.holder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.henryzu.henryzu.R;

/**
 * Created by ${韩永光} on on 2017/3/27 0027 09:28..
 */

public class Holder_AllGoods extends RecyclerView.ViewHolder {
    public SimpleDraweeView home_line_hot_week_img;
    public TextView home_line_hot_week_tv_title;
    public TextView home_line_hot_week_tv_oldPrice;
    public TextView home_line_hot_week_tv_price;
    public TextView home_line_hot_week_tv_name;

    public Holder_AllGoods(View itemView) {
        super(itemView);
        home_line_hot_week_img = (SimpleDraweeView) itemView.findViewById(R.id.home_line_hot_week_img);
        home_line_hot_week_tv_title = (TextView) itemView.findViewById(R.id.home_line_hot_week_tv_title);
        home_line_hot_week_tv_oldPrice = (TextView) itemView.findViewById(R.id.home_line_hot_week_tv_oldPrice);
        home_line_hot_week_tv_price = (TextView) itemView.findViewById(R.id.home_line_hot_week_tv_price);
        home_line_hot_week_tv_name = (TextView) itemView.findViewById(R.id.home_line_hot_week_tv_name);
        home_line_hot_week_tv_name.setTextColor(Color.GREEN);
        //设置控件间的间距
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 0, 0, 0);
        itemView.setLayoutParams(params);
        home_line_hot_week_img.setLayoutParams(new LinearLayout.LayoutParams(300,300));
    }
}
