package com.henryzu.henryzu.fragment;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.henryzu.henryzu.R;
import com.henryzu.henryzu.adapter.RvAdapter_AllGoods;
import com.henryzu.henryzu.adapter.RvAdapter_BestSellsers;
import com.henryzu.henryzu.base.BaseData;
import com.henryzu.henryzu.base.BaseFragment;
import com.henryzu.henryzu.bean.AllGoodsBean;
import com.henryzu.henryzu.bean.ShouYeBean;
import com.henryzu.henryzu.build.TitleBuilder;
import com.henryzu.henryzu.interfaces.ICallback;
import com.henryzu.henryzu.utils.ImageloadFresco;
import com.henryzu.henryzu.utils.URLUtils;
import com.henryzu.henryzu.views.ShowingPage;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class Home_PageFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private Banner banner;
    private SpringView mSpringView;
    private RecyclerView rv_home_beseseller;
    private GridView home_gv_ad5;
    private BaseData basedata;
    private RecyclerView rv_home_allgoods;
    private LinearLayoutManager mlinearLayoutManager;
    private Gson gson;

    @Override
    protected void createTitleView(ShowingPage showingPage) {
        new TitleBuilder(showingPage).setMiddleText("亨利族", 30).setLeftImageRes(R.mipmap.amr).setRightImageRes(R.mipmap.ams).build();

    }

    @Override
    protected void onload() {
        //初始化数据
        getData();
        initSpringView();


    }

    private void initSpringView() {
        mSpringView.setType(SpringView.Type.FOLLOW);
        //默认的头部布局 可自定义设置
        mSpringView.setHeader(new DefaultHeader(getActivity()));
        //SpringView监听
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //结束加载
                mSpringView.onFinishFreshAndLoad();
                //重新请求数据
                getData();
            }

            @Override
            public void onLoadmore() {

            }
        });
    }

    private void getData() {
        basedata = new BaseData();
        basedata.getData(URLUtils.BASE_URL, URLUtils.HOME_URl, BaseData.LONG_TIME, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                gson = new Gson();
                //请求数据成功 展示成功的视图
                showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                ShouYeBean shouYeBean = gson.fromJson(responseInfo, ShouYeBean.class);
                //初始化顶部轮播图viewpager
                initViewPager(shouYeBean);
                //初始化签到gridview
                initGridView(shouYeBean);
                //初始化本周热销recycleview
                initHotRecycleView(shouYeBean);
                //初始化商品列表recyclerView
                initAllGoods();
            }

            @Override
            public void onFailure(String errorInfo) {
                //请求数据失败的视图
                showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });

    }

    private void initAllGoods() {
        basedata.getData(URLUtils.BASE_URL, URLUtils.ALL_GOODS_URL, BaseData.LONG_TIME, new ICallback() {

            private AllGoodsBean allGoodsBean;

            @Override
            public void onResponse(String responseInfo) {
                allGoodsBean = gson.fromJson(responseInfo, AllGoodsBean.class);
                rv_home_allgoods.setLayoutManager(new GridLayoutManager(getActivity(),2));
                RvAdapter_AllGoods mRvAdapter_allGoods=new RvAdapter_AllGoods(getActivity(),allGoodsBean);
                rv_home_allgoods.setAdapter(mRvAdapter_allGoods);
            }

            @Override
            public void onFailure(String errorInfo) {

            }
        });

    }
    private void initHotRecycleView(final ShouYeBean shouYeBean) {
        mlinearLayoutManager = new LinearLayoutManager(getActivity());
        mlinearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        rv_home_beseseller.setLayoutManager(mlinearLayoutManager);
        RvAdapter_BestSellsers mRecyclerViewAdapter=new RvAdapter_BestSellsers(getActivity(),shouYeBean);
        rv_home_beseseller.setAdapter(mRecyclerViewAdapter);
    }

    private void initGridView(final ShouYeBean shouYeBean) {
        home_gv_ad5.setAdapter(new CommonAdapter<ShouYeBean.DataBean.Ad5Bean>(getActivity(), R.layout.home_a5_item, shouYeBean.getData().getAd5()) {
            @Override
            protected void convert(ViewHolder viewHolder, ShouYeBean.DataBean.Ad5Bean item, int position) {
                SimpleDraweeView img_a5 = viewHolder.getView(R.id.img_a5);
                TextView tv_a5 = viewHolder.getView(R.id.tv_a5);
                new ImageloadFresco.LoadImageFrescoBuilder(getActivity(), img_a5, shouYeBean.getData().getAd5().get(position).getImage()).build();
                tv_a5.setText(shouYeBean.getData().getAd5().get(position).getTitle());

            }
        });
        //GridView 子条目点击事件
        home_gv_ad5.setOnItemClickListener(this);
    }


    @Override
    public View CreateSuccessView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_fragment, null);
        //初始化控件
        initView(view);
        return view;
    }

    private void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        mSpringView = (SpringView) view.findViewById(R.id.springView);
        home_gv_ad5 = (GridView) view.findViewById(R.id.home_gv_ad5);
        rv_home_beseseller = (RecyclerView) view.findViewById(R.id.rv_home_beseseller);
        rv_home_allgoods = (RecyclerView) view.findViewById(R.id.rv_home_allgoods);
    }

    private void initViewPager(final ShouYeBean shouYeBean) {
        //设置图片集合
        banner.setImages(shouYeBean.getData().getAd1());
        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //用fresco加载图片简单用法
                String img = ((ShouYeBean.DataBean.Ad1Bean) path).getImage();
                Uri uri = Uri.parse(img);
                imageView.setImageURI(uri);
            }

            @Override
            public ImageView createImageView(Context context) {
                SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getActivity());
                return simpleDraweeView;
            }
        });
        banner.start();

    }

    @Override
    public boolean twoneedTitleView(ShowingPage showingPage) {
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
