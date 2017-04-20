package com.henryzu.henryzu.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.henryzu.henryzu.R;
import com.henryzu.henryzu.app.MyApplication;
import com.henryzu.henryzu.manager.Common;
import com.henryzu.henryzu.manager.DataClearManager;
import com.henryzu.henryzu.utils.CommonUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AutoLayoutActivity implements View.OnClickListener {

    private LinearLayout line_clearCache;
    private TextView tv_cacheSize;
    private String cacheSize;
    private TextView tv_number;
    private Button exitLogin;
    private GoogleApiClient client;
    long m_newVerCode; //最新版的版本号
    String m_newVerName; //最新版的版本名
    String m_appNameStr; //下载到本地要给这个APP命的名字

    Handler m_mainHandler;
    ProgressDialog m_progressDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        getSupportActionBar().hide();
        TextView title = (TextView) findViewById(R.id.title_center_tv);
        title.setText("设置");
        findViewById(R.id.title_right_tv).setVisibility(View.GONE);
        findViewById(R.id.title_back_iv).setOnClickListener(this);
        line_clearCache = (LinearLayout) findViewById(R.id.settings_tv_clearCache);
        tv_cacheSize = (TextView) findViewById(R.id.settings_tv_cacheSize);
        findViewById(R.id.settings_tv_notice).setOnClickListener(this);
        findViewById(R.id.settings_tv_idea).setOnClickListener(this);
        findViewById(R.id.settings_tv_about_us).setOnClickListener(this);
        findViewById(R.id.settings_line_call).setOnClickListener(this);
        tv_number = (TextView) findViewById(R.id.settings_tv_number);
        findViewById(R.id.settings_line_update).setOnClickListener(this);
        line_clearCache.setOnClickListener(this);
        exitLogin = (Button) findViewById(R.id.settings_exitLogin);
        exitLogin.setOnClickListener(this);
        getCacheSize();
    }

    private void getCacheSize() {
        try {
            cacheSize = DataClearManager.getTotalCacheSize(this);
            tv_cacheSize.setText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back_iv:
                CommonUtils.finishActivity(SettingsActivity.this);
                break;
            case R.id.settings_tv_clearCache:
                Toast.makeText(SettingsActivity.this, "clear", Toast.LENGTH_SHORT).show();
                DataClearManager.clearAllCache(this);

                getCacheSize();
                tv_cacheSize.setText(cacheSize);
                break;
            //购物须知
            case R.id.settings_tv_notice:
                Toast.makeText(SettingsActivity.this, "开心购物", Toast.LENGTH_SHORT).show();
                break;
            //意见反馈
            case R.id.settings_tv_idea:
                Toast.makeText(SettingsActivity.this, "有意见直接和韩永光说。", Toast.LENGTH_SHORT).show();
                break;
            //关于我们
            case R.id.settings_tv_about_us:

                break;
            //拨打电话
            case R.id.settings_line_call:
                String number = tv_number.getText().toString().trim();
                if (TextUtils.isEmpty(number)) {
                    Toast.makeText(SettingsActivity.this, "号码有误！", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                break;
            //版本更新
            case R.id.settings_line_update:
                //初始化相关变量  
                initVariable();
                new checkNewestVersionAsyncTask().execute();
               // Toast.makeText(SettingsActivity.this, "当前已是最新版本！", Toast.LENGTH_SHORT).show();
                break;
            //退出登录
            case R.id.settings_exitLogin:
                MyApplication.isLogin = false;//设置登录状态
                CommonUtils.saveSp("user_name", null);//用户民
                CommonUtils.saveSp("user_icon", null);//头像
                break;
        }
    }

    private void initVariable() {
        m_mainHandler = new Handler();
        m_progressDlg = new ProgressDialog(this);
        m_progressDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置ProgressDialog 的进度条是否不明确 false 就是不设置为不明确
        m_progressDlg.setIndeterminate(false);
        m_appNameStr = "haha.apk";
    }


    class checkNewestVersionAsyncTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO Auto-generated method stub
            Log.d("msg",postCheckNewestVersionCommand2Server()+"");
            if (postCheckNewestVersionCommand2Server()) {
                int vercode = Common.getVerCode(getApplicationContext()); // 用到前面第一节写的方法    
                if (m_newVerCode > vercode) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub  
            if (result) {//如果有最新版本  
                doNewVersionUpdate(); // 更新新版本    
            } else {
                notNewVersionDlgShow(); // 提示当前为最新版本    
            }
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub  
            super.onPreExecute();
        }
    }

    private void notNewVersionDlgShow() {
        int verCode = Common.getVerCode(this);
        String verName = Common.getVerName(this);
        String str="当前版本:"+verName+" Code:"+verCode+",/n已是最新版,无需更新!";
        Dialog dialog = new AlertDialog.Builder(this).setTitle("软件更新")
                .setMessage(str)// 设置内容
                .setPositiveButton("确定",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                finish();
                            }
                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }

    private void doNewVersionUpdate() {
        int verCode = Common.getVerCode(getApplicationContext());
        String verName = Common.getVerName(getApplicationContext());

        String str= "当前版本："+verName+" Code:"+verCode+" ,发现新版本："+m_newVerName+
                " Code:"+m_newVerCode+" ,是否更新？";
        Dialog dialog = new AlertDialog.Builder(this).setTitle("软件更新").setMessage(str)
                // 设置内容
                .setPositiveButton("更新",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                m_progressDlg.setTitle("正在下载");
                                m_progressDlg.setMessage("请稍候...");
                                downFile(Common.UPDATESOFTADDRESS);  //开始下载
                            }
                        })
                .setNegativeButton("暂不更新",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                // 点击"取消"按钮之后退出程序
                                finish();
                            }
                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }

    private void downFile(final String url) {
        m_progressDlg.show();
        new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    long length = entity.getContentLength();

                    m_progressDlg.setMax((int)length);//设置进度条的最大值  

                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File file = new File(
                                Environment.getExternalStorageDirectory(),
                                m_appNameStr);
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[1024];
                        int ch = -1;
                        int count = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            count += ch;
                            if (length > 0) {
                                m_progressDlg.setProgress(count);
                            }
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    down();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private void down() {
        m_mainHandler.post(new Runnable() {
            public void run() {
                m_progressDlg.cancel();
                update();
            }
        });
    }

    private void update() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), m_appNameStr)),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private Boolean postCheckNewestVersionCommand2Server()
    {
        StringBuilder builder = new StringBuilder();
        JSONArray jsonArray = null;
        try {
            // 构造POST方法的{name:value} 参数对
            List<NameValuePair> vps = new ArrayList<NameValuePair>();
            // 将参数传入post方法中
            vps.add(new BasicNameValuePair("action", "checkNewestVersion"));
            builder = Common.post_to_server(vps);
            jsonArray = new JSONArray(builder.toString());
            if (jsonArray.length()>0) {
                if (jsonArray.getJSONObject(0).getInt("id") == 1) {
                    m_newVerName = jsonArray.getJSONObject(0).getString("verName");
                    m_newVerCode = jsonArray.getJSONObject(0).getLong("verCode");

                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            Log.e("msg",e.getMessage());
            m_newVerName="";
            m_newVerCode=-1;
            return false;
        }
    }
}
