package com.henryzu.henryzu.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.henryzu.henryzu.R;
import com.henryzu.henryzu.bean.NewsBean;

/**
 * Created by ${韩永光} on on 2017/4/7 0007 14:43..
 */

public class Holder_CurrentNoimg extends BaseHolder {
    public TextView tv_news_title;
    public Holder_CurrentNoimg(View itemView) {
        super(itemView);
        tv_news_title = (TextView) itemView.findViewById(R.id.tv_news_title);
    }

    @Override
    public void getHolder(Context context, Object o) {
        NewsBean.DataBean o1 = (NewsBean.DataBean) o;
        tv_news_title.setText(o1.getTitle());
    }
}
