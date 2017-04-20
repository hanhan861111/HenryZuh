package com.henryzu.henryzu.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.henryzu.henryzu.R;
import com.henryzu.henryzu.bean.NewsBean;
import com.henryzu.henryzu.utils.ImageloadFresco;

/**
 * Created by ${韩永光} on on 2017/4/7 0007 14:47..
 */

public class Holder_CurrentMoreimg extends BaseHolder {
    public TextView tv_news_title;
    public SimpleDraweeView iv_news_pic1;
    public SimpleDraweeView iv_news_pic2;
    public SimpleDraweeView iv_news_pic3;
    public Holder_CurrentMoreimg(View itemView) {
        super(itemView);
        tv_news_title = (TextView) itemView.findViewById(R.id.tv_news_title);
        iv_news_pic1 = (SimpleDraweeView) itemView.findViewById(R.id.iv_news_pic1);
        iv_news_pic2 = (SimpleDraweeView) itemView.findViewById(R.id.iv_news_pic2);
        iv_news_pic3 = (SimpleDraweeView) itemView.findViewById(R.id.iv_news_pic3);
    }

    @Override
    public void getHolder(Context context, Object o) {
        NewsBean.DataBean o1 = (NewsBean.DataBean) o;
        tv_news_title.setText(o1.getTitle());
        new ImageloadFresco.LoadImageFrescoBuilder(context,iv_news_pic1,o1.getImage_list().get(0).getUrl()).build();
        new ImageloadFresco.LoadImageFrescoBuilder(context,iv_news_pic2,o1.getImage_list().get(1).getUrl()).build();
        new ImageloadFresco.LoadImageFrescoBuilder(context,iv_news_pic3,o1.getImage_list().get(2).getUrl()).build();
    }
}
