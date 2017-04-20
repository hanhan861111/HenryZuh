package com.henryzu.henryzu.interfaces;

/**
 * Created by 韩永光
 * on 2017/1/11 13:53.
 */

public interface ICallback {

     void onResponse(String responseInfo);

     void onFailure(String errorInfo);

}
