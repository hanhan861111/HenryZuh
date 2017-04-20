package com.henryzu.henryzu.views;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.henryzu.henryzu.R;
import com.henryzu.henryzu.interfaces.IOnResetShowingPage;
import com.henryzu.henryzu.utils.CommonUtils;
import com.henryzu.henryzu.utils.NetUtils;
import com.zhy.autolayout.AutoRelativeLayout;


/**
 * Created by 韩永光
 * on 2017/3/23.
 */
public abstract class ShowingPage extends FrameLayout implements View.OnClickListener {

    /**
     * 定义状态
     */

    public static final int STATE_LOADING = 1;
    public static final int STATE_LOAD_ERROR = 2;
    public static final int STATE_LOAD_SUCCESS = 3;
    private final Context context;

    //定义当前状态
    public int currentState = STATE_LOADING;//得到当前的状态
    private final View view;
    private final View showLoadError, showLoading;
    private final FrameLayout showFrameLayout;
    private final AutoRelativeLayout titleLayout;

    private IOnResetShowingPage iOnResetShowingPage;

    public ShowingPage(Context context) {
        super(context);
        this.context = context;

        //获取主布局视图
        view = View.inflate(context, R.layout.showing_pager, null);
        titleLayout = (AutoRelativeLayout) view.findViewById(R.id.title_bar);
        showLoadError = view.findViewById(R.id.showLoadError);
        showLoadError.findViewById(R.id.showing_error_tv_reset).setOnClickListener(this);
        showLoading = view.findViewById(R.id.showLoading);
        showFrameLayout = (FrameLayout) view.findViewById(R.id.showSuccessView);

        this.addView(view);

        showFrameLayout.addView(setSuccessView());


        //设置是否需要Title
        titleLayout.setVisibility(needTitleView(this)?VISIBLE:GONE);

        showPage();
    }

    //添加成功的视图
    public abstract View setSuccessView();

    //添加Title
    public abstract boolean needTitleView(ShowingPage showingPage);

    public void setCurrentState(StateType stateType) {
        currentState = stateType.currentState;
        showPage();
    }

    private void showPage() {
        //在主线程执行
        CommonUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showPageOnUI();
            }
        });
    }

    private void showPageOnUI() {
        showLoading.setVisibility(currentState == STATE_LOADING ? View.VISIBLE : View.GONE);
        showLoadError.setVisibility(currentState == STATE_LOAD_ERROR ? View.VISIBLE : View.GONE);
        showFrameLayout.setVisibility(currentState == STATE_LOAD_SUCCESS ? View.VISIBLE : View.GONE);
    }

    public void setIOnResetShowingPage(IOnResetShowingPage iOnResetShowingPage) {
        this.iOnResetShowingPage = iOnResetShowingPage;
    }

    /**
     * 枚举类
     */
    public enum StateType {
        //请求类型
        STATE_LOADING(1), STATE_LOAD_ERROR(2), STATE_LOAD_SUCCESS(3);
        private final int currentState;

        StateType(int currentState) {
            this.currentState = currentState;
        }

        public int getCurrentState() {
            return currentState;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //重新请求
            case R.id.showing_error_tv_reset:
                //如果点击且有网络
                if (!NetUtils.isNoNet() && iOnResetShowingPage != null) {
                    this.setCurrentState(StateType.STATE_LOADING);
                    iOnResetShowingPage.onReset(v);
                }
                break;
        }
    }
}
