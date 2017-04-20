package com.henryzu.henryzu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.henryzu.henryzu.R;
import com.henryzu.henryzu.bean.NewsBean;
import com.henryzu.henryzu.holder.BaseHolder;
import com.henryzu.henryzu.holder.Holder_CurrentBigimg;
import com.henryzu.henryzu.holder.Holder_CurrentMoreimg;
import com.henryzu.henryzu.holder.Holder_CurrentNoimg;
import com.henryzu.henryzu.holder.Holder_CurrentNormal;
import com.henryzu.henryzu.holder.Holder_CurrentVideo;
import com.henryzu.henryzu.utils.CommonUtils;

import java.util.List;

/**
 * Created by ${韩永光} on on 2017/4/7 0007 10:10..
 */

public class CurrentFragmentRVadapter extends RecyclerView.Adapter<BaseHolder> {
    private final Context context;
    private final List<NewsBean.DataBean> data;

    public CurrentFragmentRVadapter(Context context,  List<NewsBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    int NOIMG = 0;//没图片
    int BIGIMG = 1;//大图片
    int MOREIMG = 2;//多图片
    int HAVEVIDEO = 3;//有视频
    int NORMAL = 4;//普通类型


    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseHolder baseHolder = null;
        if (viewType == NOIMG) {
            View view = CommonUtils.inflate(R.layout.current_noimg);
            baseHolder = new Holder_CurrentNoimg(view);
        } else if (viewType == BIGIMG) {
            View view = CommonUtils.inflate(R.layout.current_bigimg);
            baseHolder = new Holder_CurrentBigimg(view);
        } else if (viewType == MOREIMG) {
            View view = CommonUtils.inflate(R.layout.current_moreimg);
            baseHolder = new Holder_CurrentMoreimg(view);
        } else if (viewType == HAVEVIDEO) {
            View view = CommonUtils.inflate(R.layout.current_video);
            baseHolder = new Holder_CurrentVideo(view);
        }else if(viewType==NORMAL){
            View view = CommonUtils.inflate(R.layout.current_normal);
            baseHolder = new Holder_CurrentNormal(view);
        }

        return baseHolder;

    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        //传递条目至holder
        holder.getHolder(context, data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    @Override
    public int getItemViewType(int position) {
        if (data.get(position).isHas_video()) {
            return HAVEVIDEO;
        } else if (!data.get(position).isHas_image()){
            return  NOIMG;
        }if(data.get(position).getLarge_image_list()!=null&&data.get(position).getLarge_image_list().size()!=0){
            return BIGIMG;
        }if(data.get(position).getImage_list()!=null&&data.get(position).getImage_list().size()>1){
            return MOREIMG;
        }
            return NORMAL;
    }


}
