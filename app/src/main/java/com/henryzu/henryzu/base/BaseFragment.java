package com.henryzu.henryzu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.henryzu.henryzu.interfaces.IOnResetShowingPage;
import com.henryzu.henryzu.views.ShowingPage;


/**
 * Created by 韩永光
 * on 2017/2/14 16:32.m
 */
public abstract class BaseFragment extends Fragment {

    public ShowingPage showingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //因为不知道具体的展示title  需要继续抽象
        showingPage = new ShowingPage(getContext()) {
            @Override
            public View setSuccessView() {
                //因为不知道具体的展示title  需要继续抽象
                return CreateSuccessView();
            }

            @Override
            public boolean needTitleView(ShowingPage showingPage) {
                return twoneedTitleView(showingPage);
            }
        };
        //设置标题栏  建造者模式
        createTitleView(showingPage);
        return showingPage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onload();
        showingPage.setIOnResetShowingPage(new IOnResetShowingPage() {
            @Override
            public void onReset(View v) {
                onload();
            }
        });
    }

    protected abstract void createTitleView(ShowingPage showingPage);

    protected abstract void onload();

    public abstract View CreateSuccessView();

    //添加Title
    public abstract boolean twoneedTitleView(ShowingPage showingPage);
}
