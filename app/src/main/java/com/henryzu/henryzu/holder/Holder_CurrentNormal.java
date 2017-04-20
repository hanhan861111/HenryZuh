package com.henryzu.henryzu.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.henryzu.henryzu.R;
import com.henryzu.henryzu.bean.NewsBean;
import com.henryzu.henryzu.utils.ImageloadFresco;

/**
 * Created by ${韩永光} on on 2017/4/7 0007 10:12..
 */

public class Holder_CurrentNormal extends BaseHolder {

   public TextView tv_news_title;
    public SimpleDraweeView iv_news_pic;


    public Holder_CurrentNormal(View itemView) {
        super(itemView);
        tv_news_title = (TextView) itemView.findViewById(R.id.tv_news_title);
        iv_news_pic = (SimpleDraweeView) itemView.findViewById(R.id.iv_news_pic);

    }

    @Override
    public void getHolder(Context context, Object o) {
        NewsBean.DataBean o1 = (NewsBean.DataBean) o;
        tv_news_title.setText(o1.getAbstractX());
        new ImageloadFresco.LoadImageFrescoBuilder(context,iv_news_pic,o1.getMiddle_image().getUrl()).build();
    }
}
