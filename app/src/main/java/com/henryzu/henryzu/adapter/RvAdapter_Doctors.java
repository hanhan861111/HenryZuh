package com.henryzu.henryzu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.henryzu.henryzu.R;
import com.henryzu.henryzu.holder.Holder_Doctor;
import com.henryzu.henryzu.interfaces.OnItemClickListener;
import com.henryzu.henryzu.utils.CommonUtils;
import com.henryzu.henryzu.utils.ImageloadFresco;

import java.util.ArrayList;

/**
 * Created by ${韩永光} on on 2017/3/30 0030 16:56..
 */

public class RvAdapter_Doctors extends RecyclerView.Adapter<Holder_Doctor> {
    private final Context context;
    private final ArrayList<String> doctorList;
    private OnItemClickListener onItemClickListener;

    public RvAdapter_Doctors(Context context, ArrayList<String> doctorList) {
        this.context=context;
        this.doctorList=doctorList;
    }

    @Override
    public Holder_Doctor onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = CommonUtils.inflate(R.layout.classroom_fragment_xml);
        Holder_Doctor holder_doctor = new Holder_Doctor(view);
        return holder_doctor;
    }

    @Override
    public void onBindViewHolder(final Holder_Doctor holder, final int position) {
        holder.tv_name_classroom.setText("名称:");
        holder.tv_des_classroom.setText("医生简介:");
        new ImageloadFresco.LoadImageFrescoBuilder(context,holder.img_doctor_classroom,doctorList.get(position)).build();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null){
                    onItemClickListener.OnItemClick(holder.itemView,position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
