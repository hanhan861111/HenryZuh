package com.henryzu.henryzu.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.henryzu.henryzu.R;
import com.henryzu.henryzu.adapter.RvAdapte_Prodect;
import com.henryzu.henryzu.base.BaseData;
import com.henryzu.henryzu.base.BaseFragment;
import com.henryzu.henryzu.bean.ProductBean;
import com.henryzu.henryzu.build.TitleBuilder;
import com.henryzu.henryzu.interfaces.ICallback;
import com.henryzu.henryzu.utils.CommonUtils;
import com.henryzu.henryzu.utils.URLUtils;
import com.henryzu.henryzu.views.ShowingPage;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class Product_PageFragment extends BaseFragment{

    private RecyclerView rv_product;
    private BaseData baseData;
    private Gson gson;

    @Override
    protected void createTitleView(ShowingPage showingPage) {
        new TitleBuilder(showingPage).setMiddleText("亨利族", 30).setLeftImageRes(R.mipmap.amr).setRightImageRes(R.mipmap.ams).build();

    }

    @Override
    protected void onload() {
        //初始化数据
        getData();
    }

    private void getData() {
        baseData = new BaseData();
        baseData.getData(URLUtils.BASE_URL2, URLUtils.PRODUCT_URL, BaseData.LONG_TIME, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                //请求数据成功 展示成功的视图
                showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                gson = new Gson();
                ProductBean productBean = gson.fromJson(responseInfo, ProductBean.class);
                //初始化RecyclerView
                initRecyclerView(productBean);

            }

            @Override
            public void onFailure(String errorInfo) {
                //请求数据失败的视图
                showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });
    }

    private void initRecyclerView(ProductBean productBean) {
        rv_product.setLayoutManager(new LinearLayoutManager(getActivity()));
        RvAdapte_Prodect rvAdapter=new RvAdapte_Prodect(getActivity(),productBean);
        rv_product.setAdapter(rvAdapter);
    }

    @Override
    public View CreateSuccessView() {
        View view = CommonUtils.inflate(R.layout.product_fragment);
        //初始化控件
        initView(view);
        return view;
    }

    private void initView(View view) {
        rv_product = (RecyclerView) view.findViewById(R.id.rv_product);
    }

    @Override
    public boolean twoneedTitleView(ShowingPage showingPage) {
        return true;
    }


}
