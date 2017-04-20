package com.henryzu.henryzu.interfaces;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by 韩永光
 * on 2017/1/11 11:33.
 */

public interface IRetrofitAPI {

    @GET
    Call<String> getInfo(@Url String url);

    @FormUrlEncoded
    @POST
    Call<String> postInfo(@Url String url, @FieldMap Map<String, String> map);
}
