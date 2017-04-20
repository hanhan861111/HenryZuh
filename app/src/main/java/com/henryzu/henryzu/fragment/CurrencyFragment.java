package com.henryzu.henryzu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.henryzu.henryzu.R;
import com.henryzu.henryzu.adapter.CurrentFragmentRVadapter;
import com.henryzu.henryzu.base.BaseData;
import com.henryzu.henryzu.base.BaseFragment;
import com.henryzu.henryzu.bean.NewsBean;
import com.henryzu.henryzu.interfaces.ICallback;
import com.henryzu.henryzu.utils.CommonUtils;
import com.henryzu.henryzu.utils.URLUtils;
import com.henryzu.henryzu.views.ShowingPage;

import java.util.List;

/**
 * Created by ${韩永光} on on 2017/4/1 0001 11:12..
 */

public class CurrencyFragment extends BaseFragment {

    private RecyclerView rv_currency;
    private CurrentFragmentRVadapter currentFragmentRVadapter;

    @Override
    protected void createTitleView(ShowingPage showingPage) {

    }

    @Override
    protected void onload() {
        final String category = getArguments().getString("category");
       new BaseData().getData(URLUtils.URL_TITLE_PATH_BASE, URLUtils.URL_CATEGORY + category, BaseData.LONG_TIME, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                 showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                NewsBean newsBean = new Gson().fromJson(responseInfo, NewsBean.class);
                List<NewsBean.DataBean> data = newsBean.getData();
              //  Log.i("TAG!!!!",URLUtils.URL_TITLE_PATH_BASE+URLUtils.URL_CATEGORY + category);
                //recycleview多条目展示
                rv_currency.setLayoutManager(new LinearLayoutManager(getActivity()));
                currentFragmentRVadapter = new CurrentFragmentRVadapter(getActivity(), data);
                rv_currency.setAdapter(currentFragmentRVadapter);
            }

            @Override
            public void onFailure(String errorInfo) {
                     showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        rv_currency.setAdapter(currentFragmentRVadapter);
    }

    @Override
    public View CreateSuccessView() {
        View view = CommonUtils.inflate(R.layout.currency_fragment);
        rv_currency = (RecyclerView) view.findViewById(R.id.rv_currency);
        return view;
    }

    @Override
    public boolean twoneedTitleView(ShowingPage showingPage) {
        return false;
    }

    public static Fragment getIntence(String category) {
        CurrencyFragment currencyFragment = new CurrencyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        currencyFragment.setArguments(bundle);
        return currencyFragment;
    }
}
