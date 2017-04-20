package com.henryzu.henryzu.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.henryzu.henryzu.R;
import com.henryzu.henryzu.bean.AllGoodsBean;
import com.henryzu.henryzu.holder.Holder_AllGoods;
import com.henryzu.henryzu.utils.CommonUtils;
import com.henryzu.henryzu.utils.ImageloadFresco;

/**
 * Created by ${韩永光} on on 2017/3/27 0027 09:25..
 */

public class RvAdapter_AllGoods  extends RecyclerView.Adapter<Holder_AllGoods>{
    private final Context context;
    private final AllGoodsBean allGoodsBean;

    public RvAdapter_AllGoods(Context context, AllGoodsBean allGoodsBean) {
        this.context=context;
        this.allGoodsBean=allGoodsBean;
    }

    @Override
    public Holder_AllGoods onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = CommonUtils.inflate(R.layout.home_line_hotweek_item);
        Holder_AllGoods holder_allGoods = new Holder_AllGoods(view);
        return holder_allGoods;
    }

    @Override
    public void onBindViewHolder(Holder_AllGoods holder, int position) {
                new ImageloadFresco.LoadImageFrescoBuilder(context,holder.home_line_hot_week_img,allGoodsBean.getData().get(position).getGoods_img()).build();
        holder.home_line_hot_week_tv_title.setText(allGoodsBean.getData().get(position).getGoods_name());
        holder.home_line_hot_week_tv_price.setText("￥"+allGoodsBean.getData().get(position).getShop_price());
        holder.home_line_hot_week_tv_oldPrice.setText("￥"+allGoodsBean.getData().get(position).getMarket_price());
        holder.home_line_hot_week_tv_oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.home_line_hot_week_tv_name.setText(allGoodsBean.getData().get(position).getEfficacy());
    }

    @Override
    public int getItemCount() {
        return allGoodsBean.getData().size();
    }
}
