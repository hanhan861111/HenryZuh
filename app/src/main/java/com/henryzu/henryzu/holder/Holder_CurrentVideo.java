package com.henryzu.henryzu.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.henryzu.henryzu.R;
import com.henryzu.henryzu.bean.NewsBean;
import com.henryzu.henryzu.utils.ImageloadFresco;

/**
 * Created by ${韩永光} on on 2017/4/7 0007 14:48..
 */

public class Holder_CurrentVideo extends BaseHolder {

    private final TextView tv_news_title;
    private final ImageView iv_news_movie_play;
    private final SimpleDraweeView iv_news_pic;
    private final ImageView vv_news_movie;

    public Holder_CurrentVideo(View itemView) {
        super(itemView);
        tv_news_title = (TextView) itemView.findViewById(R.id.tv_news_title);
        iv_news_movie_play = (ImageView) itemView.findViewById(R.id.iv_news_movie_play);
        iv_news_pic = (SimpleDraweeView) itemView.findViewById(R.id.iv_news_pic);
        vv_news_movie = (ImageView) itemView.findViewById(R.id.vv_news_movie);
    }

    @Override
    public void getHolder(Context context, Object o) {
        NewsBean.DataBean o1 = (NewsBean.DataBean) o;
        tv_news_title.setText(o1.getAbstractX());
        new ImageloadFresco.LoadImageFrescoBuilder(context,iv_news_pic,o1.getMedia_info().getAvatar_url()).build();
    }
}
