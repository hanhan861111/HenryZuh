package com.henryzu.henryzu.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ${韩永光} on on 2017/4/7 0007 10:26..
 */

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {
    public BaseHolder(View itemView) {
        super(itemView);
    }
    public abstract void getHolder(Context context,T t);
}
