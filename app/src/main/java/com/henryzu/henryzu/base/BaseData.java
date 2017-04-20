package com.henryzu.henryzu.base;

import android.text.TextUtils;

import com.henryzu.henryzu.interfaces.ICallback;
import com.henryzu.henryzu.interfaces.IRetrofitAPI;
import com.henryzu.henryzu.utils.CommonUtils;
import com.henryzu.henryzu.utils.Md5Encoder;
import com.henryzu.henryzu.views.ReadCookiesInterceptor;
import com.henryzu.henryzu.views.SaveCookiesInterceptor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.R.attr.key;
import static android.R.attr.value;

/**
 * Created by 韩永光
 * on 2016/11/28.
 */
public class BaseData {

    public static final int NO_TIME = 0;//要最新的数据
    public static final int TEN_MINUTE_TIME = 10 * 60 * 1000;//10分钟
    public static final int SHORT_TIME = 60 * 60 * 1000;//一小时
    public static final int LONG_TIME = 24 * 60 * 60 * 1000;//一天
    private final File fileDir;
    private StringBuilder stringPath;

    public BaseData() {
        File cacheDir = CommonUtils.getContext().getCacheDir();
        fileDir = new File(cacheDir, "daily_study_cache");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
    }

    //get请求数据
    public void getData(String baseUrl, String url, int time, ICallback iCallback) {
        if (time == 0) {
            getDataFromNet(baseUrl, url, time, iCallback);
        } else {
            String data = getDataFromLocal(url, time);
            if (TextUtils.isEmpty(data)) {
                //如果本地没有数据，则请求网络
                getDataFromNet(baseUrl, url, time, iCallback);
            } else {
                //将本地获取到的数据返回
                iCallback.onResponse(data);
            }
        }
    }

    /**
     * get网络请求
     *
     * @param baseUrl
     * @param url
     * @param time
     * @param iCallback
     */
    private void getDataFromNet(final String baseUrl, final String url, int time, final ICallback iCallback) {

        Retrofit builder = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).build();

        IRetrofitAPI iRetrofitAPI = builder.create(IRetrofitAPI.class);

        Call<String> stringCall = iRetrofitAPI.getInfo(url);

        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, final Response<String> response) {
                iCallback.onResponse(response.body());

                CommonUtils.executeRunnalbe(new Runnable() {
                    @Override
                    public void run() {
                        //将数据存入本地
                        saveDataToLocal(url, response.body());
                    }
                });

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                iCallback.onFailure(t.getMessage());
            }
        });
    }

    //post请求数据
    public void postData(boolean isReadCookie, boolean isSaveCookie, String baseUrl, String url, int time, Map<String, String> map, ICallback icallback) {
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        stringPath = new StringBuilder();
        for (Map.Entry<String, String> stringEntry : entrySet) {
            stringPath.append(stringEntry.getKey() + stringEntry.getValue());
        }

        if (time == 0) {
            postDataFromNet(isReadCookie, isSaveCookie, baseUrl, url, time, map, icallback);
        } else {
            String data = getDataFromLocal(baseUrl + url + stringPath, time);
            if (TextUtils.isEmpty(data)) {
                //如果本地没有数据，则请求网络
                postDataFromNet(isReadCookie, isSaveCookie, baseUrl, url, time, map, icallback);
            } else {
                //将本地获取到的数据返回
                icallback.onResponse(data);
            }
        }
    }

    /**
     * post请求获取本地数据
     *
     * @param time
     * @return
     */
    private String getDataFromLocal(String path, int time) {

        BufferedReader bufferedReader = null;
        try {
            File file = new File(fileDir, Md5Encoder.encode(path));
            bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            //去除存储的时间，作比较
            String lineTime = bufferedReader.readLine();
            long saveTime = Long.parseLong(lineTime);
            if (System.currentTimeMillis() - saveTime < time) {
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * pst请求获取网络数据
     *
     * @param baseUrl
     * @param url
     * @param time
     * @param map
     */
    private void postDataFromNet(boolean isReadCookie, boolean isSaveCookie, final String baseUrl, final String url, int time, Map<String, String> map, final ICallback icallback) {

        OkHttpClient httpClient = null;
        if (isReadCookie && !isSaveCookie) {
            httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new ReadCookiesInterceptor())
                    .build();
        }
        if (isSaveCookie && !isReadCookie) {
            httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new SaveCookiesInterceptor())
                    .build();
        }
        if (isSaveCookie && isReadCookie) {
            httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new SaveCookiesInterceptor()).addInterceptor(new ReadCookiesInterceptor())
                    .build();
        }
        if (!isSaveCookie && !isReadCookie) {
            httpClient = new OkHttpClient.Builder()
                    .build();
        }

        //指定客户端
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).client(httpClient).addConverterFactory(ScalarsConverterFactory.create()).build();

//        Retrofit builder = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).build();

        IRetrofitAPI retrofitAPI = retrofit.create(IRetrofitAPI.class);

        Call<String> stringCall = retrofitAPI.postInfo(url, map);

        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, final Response<String> response) {
                icallback.onResponse(response.body());
                CommonUtils.executeRunnalbe(new Runnable() {
                    @Override
                    public void run() {
                        //将数据存入本地
                        saveDataToLocal(baseUrl + url + key + value, response.body());
                    }
                });
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                icallback.onFailure(t.getMessage());
            }
        });
    }


    /**
     * 将数据写入本地
     *
     * @param path
     * @param data
     */
    private void saveDataToLocal(String path, String data) {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(fileDir, Md5Encoder.encode(path));
            bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            bufferedWriter.write(System.currentTimeMillis() + "\r\n");//存一下当前的时间
            bufferedWriter.write(data);
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
