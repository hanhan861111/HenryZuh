package com.henryzu.henryzu.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.henryzu.henryzu.R;

/**
 * Created by ${韩永光} on on 2017/3/30 0030 16:57..
 */

public class Holder_Doctor extends RecyclerView.ViewHolder {

    public TextView tv_name_classroom;
    public TextView tv_des_classroom;
    public SimpleDraweeView img_doctor_classroom;

    public Holder_Doctor(View itemView) {
        super(itemView);
        tv_name_classroom = (TextView) itemView.findViewById(R.id.tv_name_classroom);
        tv_des_classroom = (TextView) itemView.findViewById(R.id.tv_des_classroom);
        img_doctor_classroom = (SimpleDraweeView) itemView.findViewById(R.id.img_doctor_classroom);
    }
}
