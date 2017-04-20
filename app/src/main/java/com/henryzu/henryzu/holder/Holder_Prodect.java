package com.henryzu.henryzu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.henryzu.henryzu.R;

/**
 * Created by ${韩永光} on on 2017/3/27 0027 11:45..
 */

public class Holder_Prodect extends RecyclerView.ViewHolder {

    public SimpleDraweeView img_prodect;
    public TextView title_prodect;
    public TextView des_prodect;
    public TextView time_prodect;

    public Holder_Prodect(View itemView) {
        super(itemView);
        img_prodect = (SimpleDraweeView) itemView.findViewById(R.id.img_prodect);
        title_prodect = (TextView) itemView.findViewById(R.id.title_prodect);
        des_prodect = (TextView) itemView.findViewById(R.id.des_prodect);
        time_prodect = (TextView) itemView.findViewById(R.id.time_prodect);
        //设置控件间的间距
       LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
       params.setMargins(10, 2, 2, 2);
       itemView.setLayoutParams(params);
    }
}
