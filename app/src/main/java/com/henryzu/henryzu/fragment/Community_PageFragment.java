package com.henryzu.henryzu.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.gson.Gson;
import com.henryzu.henryzu.R;
import com.henryzu.henryzu.base.BaseData;
import com.henryzu.henryzu.base.BaseFragment;
import com.henryzu.henryzu.bean.TitleBean;
import com.henryzu.henryzu.interfaces.ICallback;
import com.henryzu.henryzu.utils.CommonUtils;
import com.henryzu.henryzu.utils.TitleUtils;
import com.henryzu.henryzu.utils.URLUtils;
import com.henryzu.henryzu.views.ShowingPage;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class Community_PageFragment extends BaseFragment {

    private TabLayout tl_community;
    private ViewPager vp_community;
    private BaseData baseData;
    private Gson gson;
    private FragmentPagerAdapter mfragmentPagerAdapter;

    @Override
    protected void createTitleView(ShowingPage showingPage) {

    }

    @Override
    protected void onload() {
        baseData = new BaseData();
        baseData.getData(URLUtils.URL_TITLE_PATH_BASE, URLUtils.URL_TITLE_PATH, BaseData.LONG_TIME, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {
                showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                gson = new Gson();
                TitleBean titleBean = gson.fromJson(responseInfo, TitleBean.class);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("推荐:,");
                for (int i = 0; i < titleBean.getData().getData().size(); i++) {
                    TitleBean.DataBeanX.DataBean dataBean = titleBean.getData().getData().get(i);
                    String name = dataBean.getName();
                    String category = dataBean.getCategory();
                    stringBuilder.append(name + ":" + category + ",");
                }
                CommonUtils.saveSp("newsTitle", stringBuilder.toString());
                //viewpager适配
                getViewPager(titleBean);
                //绑定
                tl_community.setupWithViewPager(vp_community);
                tl_community.setTabGravity(TabLayout.GRAVITY_FILL);
                tl_community.setTabsFromPagerAdapter(mfragmentPagerAdapter);
                vp_community.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        tl_community.getTabAt(position).select();
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });


            }

            @Override
            public void onFailure(String errorInfo) {
                showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        });
        baseData.getData(URLUtils.URL_TITLE_PATH_BASE, URLUtils.URL_CATEGORY + "news_hot", BaseData.LONG_TIME, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {

            }

            @Override
            public void onFailure(String errorInfo) {

            }
        });
        baseData.getData(URLUtils.URL_TITLE_PATH_BASE, URLUtils.URL_CATEGORY, BaseData.LONG_TIME, new ICallback() {
            @Override
            public void onResponse(String responseInfo) {

            }

            @Override
            public void onFailure(String errorInfo) {

            }
        });

    }

    private void getViewPager(final TitleBean titleBean) {
        final String newsTitle = CommonUtils.getSp("newsTitle");
        String[] split = newsTitle.split(",");
        //装title的两个集合
        for (int i = 0; i < 10; i++) {
            TitleUtils.myChannel.add(split[i]);
        }
        for (int i = 10; i < split.length; i++) {
            TitleUtils.moreChannel.add(split[i]);
        }
        mfragmentPagerAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = CurrencyFragment.getIntence(TitleUtils.myChannel.get(position).substring(TitleUtils.myChannel.get(position).indexOf(":") + 1));
                return fragment;
            }

            @Override
            public int getCount() {
                return TitleUtils.myChannel.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return TitleUtils.myChannel.get(position).substring(0, TitleUtils.myChannel.get(position).indexOf(":"));
            }

        };
        vp_community.setAdapter(mfragmentPagerAdapter);
    }


    @Override
    public View CreateSuccessView() {
        View view = CommonUtils.inflate(R.layout.community_fragment);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tl_community = (TabLayout) view.findViewById(R.id.tl_community);
        vp_community = (ViewPager) view.findViewById(R.id.vp_community);

    }

    @Override
    public boolean twoneedTitleView(ShowingPage showingPage) {
        return true;
    }
}
