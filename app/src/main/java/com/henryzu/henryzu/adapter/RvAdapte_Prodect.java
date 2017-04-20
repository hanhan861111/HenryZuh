package com.henryzu.henryzu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.henryzu.henryzu.R;
import com.henryzu.henryzu.bean.ProductBean;
import com.henryzu.henryzu.holder.Holder_Prodect;
import com.henryzu.henryzu.utils.CommonUtils;
import com.henryzu.henryzu.utils.ImageloadFresco;
import com.henryzu.henryzu.utils.TimeUtils;
import com.henryzu.henryzu.utils.URLUtils;

/**
 * Created by ${韩永光} on on 2017/3/27 0027 11:44..
 */

public class RvAdapte_Prodect extends RecyclerView.Adapter<Holder_Prodect> {
    private final Context context;
    private final ProductBean productBean;

    public RvAdapte_Prodect(Context context, ProductBean productBean) {
        this.context = context;
        this.productBean = productBean;
    }

    @Override
    public Holder_Prodect onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = CommonUtils.inflate(R.layout.prodect_item);
        Holder_Prodect holder_prodect = new Holder_Prodect(view);
        return holder_prodect;
    }

    @Override
    public void onBindViewHolder(final Holder_Prodect holder, final int position) {
           new ImageloadFresco.LoadImageFrescoBuilder(context,holder.img_prodect, URLUtils.BASE_URL2+productBean.getIndex_duobao_list().get(position).getIcon()).build();
           holder.title_prodect.setText("名称："+productBean.getIndex_duobao_list().get(position).getName());
           holder.des_prodect.setText("简介："+productBean.getIndex_duobao_list().get(position).getBrief());
        String time = productBean.getIndex_duobao_list().get(position).getCreate_time();
        String s = TimeUtils.formatTime(Integer.parseInt(time));
        holder.time_prodect.setText("时间: "+s);

    }

    @Override
    public int getItemCount() {
        return productBean.getIndex_duobao_list().size();
    }

}
