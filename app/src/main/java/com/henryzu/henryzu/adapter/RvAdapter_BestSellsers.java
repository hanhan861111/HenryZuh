package com.henryzu.henryzu.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.henryzu.henryzu.R;
import com.henryzu.henryzu.bean.ShouYeBean;
import com.henryzu.henryzu.holder.Holder_BestSellers;
import com.henryzu.henryzu.utils.CommonUtils;
import com.henryzu.henryzu.utils.ImageloadFresco;
import com.henryzu.henryzu.utils.ToastUtil;

/**
 * Created by ${韩永光} on on 2017/3/24 0024 15:30..
 */

public class RvAdapter_BestSellsers extends RecyclerView.Adapter<Holder_BestSellers> {
    private final Context context;
    private final ShouYeBean shouYeBean;

    public RvAdapter_BestSellsers(Context context, ShouYeBean shouYeBean) {
        this.context=context;
        this.shouYeBean=shouYeBean;
    }

    @Override
    public Holder_BestSellers onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = CommonUtils.inflate(R.layout.home_line_hotweek_item);
        Holder_BestSellers holder=new Holder_BestSellers(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder_BestSellers holder, final int position) {
        ShouYeBean.DataBean.BestSellersBean bestSellersBean = shouYeBean.getData().getBestSellers().get(0);
        new ImageloadFresco.LoadImageFrescoBuilder(context,holder.home_line_hot_week_img,bestSellersBean.getGoodsList().get(position).getGoods_img()).build();
        holder.home_line_hot_week_tv_title.setText(bestSellersBean.getGoodsList().get(position).getGoods_name());
        holder.home_line_hot_week_tv_price.setText("￥"+bestSellersBean.getGoodsList().get(position).getShop_price());
        holder.home_line_hot_week_tv_oldPrice.setText("￥"+bestSellersBean.getGoodsList().get(position).getMarket_price());
        holder.home_line_hot_week_tv_oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
          if(position==6){
              holder.home_line_hot_week_img.setImageResource(R.mipmap.home_rank_list_more);
              holder.home_line_hot_week_img.setScaleType(ImageView.ScaleType.FIT_END);
              holder.home_line_hot_week_tv_title.setText("");
              holder.home_line_hot_week_tv_price.setText("");
              holder.home_line_hot_week_tv_oldPrice.setText("");
              holder.home_line_hot_week_tv_oldPrice.setText("");
              //条目点击事件
              holder.home_line_hot_week_img.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      ToastUtil.show(context,"加载更多"+position);
                  }
              });
          }else{
              holder.home_line_hot_week_img.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      ToastUtil.show(context,"商品详情"+shouYeBean.getData().getAd5());
                  }
              });
          }

    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
