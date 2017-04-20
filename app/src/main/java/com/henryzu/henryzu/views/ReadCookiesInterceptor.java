package com.henryzu.henryzu.views;


import com.henryzu.henryzu.app.MyApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;


/**
 * Created by zhiyuan on 17/1/11.
 */

public class ReadCookiesInterceptor implements Interceptor {

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String cookie = SharedPreferencesUtils.getString(MyApplication.getContext(), "cookie", "");
        //将cookie添加到请求头中
        builder.addHeader("Cookie", cookie);
        return chain.proceed(builder.build());
    }
}
