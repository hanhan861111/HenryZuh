package com.henryzu.henryzu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.henryzu.henryzu.R;
import com.henryzu.henryzu.app.MyApplication;
import com.henryzu.henryzu.utils.CommonUtils;
import com.henryzu.henryzu.utils.PwdCheckUtil;
import com.zhy.autolayout.AutoLayoutActivity;

public class LoginActivity extends AutoLayoutActivity implements View.OnClickListener {

    private TextView loginynf, loginmobile, loginforgettvps, logintvother, getCode;
    private EditText loginetnum, loginetpsw, loginetcode;
    private LinearLayout line_code;
    private PopupWindow popWindow;
    private LinearLayout login_layout;
    private LinearLayout login_bg;
    private ImageView title_back_iv;
    private TextView title_right_tv;
    private EditText et_regist_name;
    private EditText et_regist_pass;
    private EditText et_regist_morepass;
    private Button bt_regist_ensure;
    private Button bt_regist_cancel;
    private EditText login_et_name;
    private EditText login_et_psw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        getSupportActionBar().hide();
        login_bg = (LinearLayout) findViewById(R.id.login_bg_line);
        login_layout = (LinearLayout) findViewById(R.id.login_layout);
        loginynf = (TextView) findViewById(R.id.login_ynf);
        loginmobile = (TextView) findViewById(R.id.login_mobile);
        loginetcode = (EditText) findViewById(R.id.login_et_code);
        loginforgettvps = (TextView) findViewById(R.id.login_forget_tv_ps);
        logintvother = (TextView) findViewById(R.id.login_tv_other);
        getCode = (TextView) findViewById(R.id.login_getCode);
        line_code = (LinearLayout) findViewById(R.id.login_line_code);
        title_back_iv = (ImageView) findViewById(R.id.title_back_iv);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        loginynf.setOnClickListener(this);
        loginmobile.setOnClickListener(this);
        logintvother.setOnClickListener(this);
        getCode.setOnClickListener(this);
        title_back_iv.setOnClickListener(this);
        title_right_tv.setOnClickListener(this);
        login_et_name = (EditText) findViewById(R.id.login_et_name);
        login_et_psw = (EditText) findViewById(R.id.login_et_psw);

    }

    //登陆
    public void login_btn(View view) {
        String registname = CommonUtils.getSp("registname");
        String registpass = CommonUtils.getSp("registpass");
        Log.d("QQQ", registname + registpass + "~~~~~~~~~~~~~~~~~~");
        if (login_et_name.getText().toString().trim().isEmpty() || registpass.equals(login_et_psw.getText().toString().trim().isEmpty())) {
            Toast.makeText(LoginActivity.this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (registname.equals(login_et_name.getText().toString().trim()) && registpass.equals(login_et_psw.getText().toString().trim())) {
            Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            MyApplication.isLogin=true;
            Intent intent=new Intent();
            intent.putExtra("registname",registname);
            intent.putExtra("isLogin", MyApplication.isLogin);
            this.setResult(101,intent);
            this.finish();

        } else {
            Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //login_bg.setBackgroundResource(R.mipmap.bg_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //选择账号登录
            case R.id.login_ynf:
                loginynf.setTextColor(getResources().getColor(R.color.colorTextMain));
                loginmobile.setTextColor(Color.GRAY);
                loginynf.setBackgroundColor(Color.WHITE);
                loginmobile.setBackgroundColor(00000000);
                loginforgettvps.setVisibility(View.VISIBLE);
                loginetpsw.setVisibility(View.VISIBLE);
                line_code.setVisibility(View.GONE);
                break;
            //选择手机登录
            case R.id.login_mobile:
                loginmobile.setBackgroundColor(Color.WHITE);
                loginynf.setBackgroundColor(00000000);
                loginynf.setTextColor(Color.GRAY);
                loginmobile.setTextColor(getResources().getColor(R.color.colorTextMain));
                loginforgettvps.setVisibility(View.INVISIBLE);
                line_code.setVisibility(View.VISIBLE);
                loginetpsw.setVisibility(View.GONE);
                break;
            case R.id.login_tv_other:
                //showPopwindow();
                break;
            case R.id.login_getCode:
                break;
            //返回
            case R.id.title_back_iv:
                CommonUtils.finishActivity(LoginActivity.this);
                break;
            //注册
            case R.id.title_right_tv:
                View view = CommonUtils.inflate(R.layout.pop_regist);
                popWindow = new PopupWindow(view,
                        WindowManager.LayoutParams.MATCH_PARENT, 350);
                popWindow.setFocusable(true);
                popWindow.setAnimationStyle(R.style.popupAnimation);
                popWindow.showAtLocation(loginforgettvps,
                        Gravity.NO_GRAVITY, 10, 10);

                et_regist_name = (EditText) view.findViewById(R.id.et_regist_name);
                et_regist_pass = (EditText) view.findViewById(R.id.et_regist_pass);
                et_regist_morepass = (EditText) view.findViewById(R.id.et_regist_morepass);
                bt_regist_ensure = (Button) view.findViewById(R.id.bt_regist_ensure);
                bt_regist_cancel = (Button) view.findViewById(R.id.bt_regist_cancel);
                bt_regist_ensure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = et_regist_name.getText().toString().trim();
                        String pass = et_regist_pass.getText().toString().trim();
                        String morepass = et_regist_morepass.getText().toString().trim();
                        boolean letterDigitname = PwdCheckUtil.isLetterDigit(name);
                        boolean letterDigitpass = PwdCheckUtil.isLetterDigit(pass);
                        if (name.isEmpty() || pass.isEmpty() || morepass.isEmpty()) {
                            Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                        } else if (!letterDigitname || !letterDigitpass) {
                            Toast.makeText(LoginActivity.this, "账号或密码必须包含字母和数字", Toast.LENGTH_SHORT).show();
                        } else if (!pass.equals(morepass)) {
                            Toast.makeText(LoginActivity.this, "您两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            CommonUtils.saveSp("registname", name);
                            CommonUtils.saveSp("registpass", pass);
                            closePopupWindow();
                        }
                    }

                });


                break;
            //QQ登录
//            case R.id.login_pop_qq:
//                UMShareAPI mShareAPI = UMShareAPI.get(LoginActivity.this);
////                mShareAPI.doOauthVerify(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
//                mShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
//                break;
            default:
                break;
        }

    }

    /**
     * 显示popupWindow
     */
   /* private void showPopwindow() {

        // 利用layoutInflater获得View
       // View view = CommonUtils.inflate(R.layout.login_pop_bottom);
        ImageView qq_login = (ImageView) view.findViewById(R.id.login_pop_qq);
        qq_login.setOnClickListener(this);
        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        popWindow = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT, 350);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        popWindow.setFocusable(true);
        setBackgroundAlpha();

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                closePopupWindow();
                return false;
            }
        });
        // 设置popWindow的显示和消失动画
        popWindow.setAnimationStyle(R.style.popupAnimation);
        // 在底部显示
        popWindow.showAtLocation(loginforgettvps,
                Gravity.BOTTOM, 0, 0);
    }*/

    //设置当前窗口背景背景
    public void setBackgroundAlpha() {
        WindowManager.LayoutParams params = this.getWindow().getAttributes();
        params.alpha = 0.7f;
        this.getWindow().setAttributes(params);
    }

    //关闭PopWindow
    private void closePopupWindow() {
        if (popWindow != null && popWindow.isShowing()) {
            popWindow.dismiss();
            popWindow = null;
            WindowManager.LayoutParams params = this.getWindow().getAttributes();
            params.alpha = 1f;
            this.getWindow().setAttributes(params);
        }
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            MyApplication.isLogin = true;//设置登录状态
            CommonUtils.saveSp("user_name", data.get("screen_name"));//用户民
            CommonUtils.saveSp("user_icon", data.get("profile_image_url"));//头像
            finish();
            Toast.makeText(getApplicationContext(), "登录成功！", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };*/
}


