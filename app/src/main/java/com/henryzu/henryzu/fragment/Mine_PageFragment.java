package com.henryzu.henryzu.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.henryzu.henryzu.R;
import com.henryzu.henryzu.activity.LoginActivity;
import com.henryzu.henryzu.activity.SettingsActivity;
import com.henryzu.henryzu.base.BaseFragment;
import com.henryzu.henryzu.utils.CommonUtils;
import com.henryzu.henryzu.views.ShowingPage;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class Mine_PageFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private TextView login_name;
    private ImageView no_login_icon;
    private ImageView is_login_icon;
    private boolean isLogin;
    private Button mine_login_btn;

    @Override
    protected void createTitleView(ShowingPage showingPage) {

    }

    @Override
    protected void onload() {

        showingPage.setCurrentState(ShowingPage.StateType.STATE_LOAD_SUCCESS);

    }

    @Override
    public View CreateSuccessView() {
        view = CommonUtils.inflate(R.layout.mine_fragment);
        //初始化控件
        initView(view);
        return view;
    }

    private void initView(View view) {
        no_login_icon = (ImageView) view.findViewById(R.id.mine_nologin_iv);
        no_login_icon.setOnClickListener(this);
        is_login_icon = (ImageView) view.findViewById(R.id.mine_islogin_iv);
        is_login_icon.setOnClickListener(this);
        login_name = (TextView) view.findViewById(R.id.mine_login_name);
        login_name.setOnClickListener(this);
        mine_login_btn = (Button) view.findViewById(R.id.mine_login_btn);
        mine_login_btn.setOnClickListener(this);
        view.findViewById(R.id.mine_settings_iv).setOnClickListener(this);
        view.findViewById(R.id.tv_mine_shopcat).setOnClickListener(this);
        view.findViewById(R.id.tv_mine_wait_pay).setOnClickListener(this);
        view.findViewById(R.id.tv_mine_wait_send_good).setOnClickListener(this);
        view.findViewById(R.id.tv_mine_wait_receive_good).setOnClickListener(this);
        view.findViewById(R.id.tv_mine_wait_evaluate).setOnClickListener(this);
        view.findViewById(R.id.tv_mine_wait_refund).setOnClickListener(this);
        view.findViewById(R.id.tv_mine_order_icon).setOnClickListener(this);
        view.findViewById(R.id.tv_mine_invite_gift_icon).setOnClickListener(this);
        view.findViewById(R.id.tv_mine_face_test_icon).setOnClickListener(this);
        view.findViewById(R.id.tv_mine_coupon_icon).setOnClickListener(this);
        view.findViewById(R.id.tv_mine_lottery_icon).setOnClickListener(this);
        view.findViewById(R.id.tv_mine_collection_icon).setOnClickListener(this);
        view.findViewById(R.id.tv_mine_contact_service_icon).setOnClickListener(this);
    }

    @Override
    public boolean twoneedTitleView(ShowingPage showingPage) {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击登录按钮或者会员中心
            case R.id.mine_login_btn:
                if (isLogin) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    //startactivityforresult方法
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 101);
                }
                break;
            //点击未登录头像
            case R.id.mine_nologin_iv:
                //   CommonUtils.startActivity(getActivity(), LoginActivity.class);
                break;
            //点击登录头像
            case R.id.mine_islogin_iv:
                Toast.makeText(getActivity(), "已经登录。", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_mine_contact_service_icon:
                Toast.makeText(getActivity(), "515", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mine_settings_iv:
                CommonUtils.startActivity(getActivity(), SettingsActivity.class);
                break;
            //点击用户名
            case R.id.mine_login_name:
                //   CommonUtils.startActivity(getActivity(), SettingsActivity.class);
                break;
            //待付款
            case R.id.tv_mine_wait_pay:
                enterOrderActivity(0);
                break;
            //待发货
            case R.id.tv_mine_wait_send_good:
                enterOrderActivity(1);
                break;
            //待收货
            case R.id.tv_mine_wait_receive_good:
                enterOrderActivity(2);
                break;
            //待评价
            case R.id.tv_mine_wait_evaluate:
                enterOrderActivity(3);
                break;
            //退款
            case R.id.tv_mine_wait_refund:
                enterOrderActivity(4);
                break;
        }
    }

    private void enterOrderActivity(int position) {
//        Intent intent = new Intent(getActivity(), MyOrderActivity.class);
//        intent.putExtra("order_position",position);
//        startActivity(intent);
//        getActivity().overridePendingTransition(R.animator.xin_right, R.animator.xout_left);
    }

    //startactivityforresult方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            String registname = data.getStringExtra("registname");
            isLogin = data.getBooleanExtra("isLogin", false);
            login_name.setText(CommonUtils.getSp("registname"));
            mine_login_btn.setText("已登录");
            Toast.makeText(getActivity(), "已登录", Toast.LENGTH_SHORT).show();
        }
    }
}