package com.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * HutoolHttpUtils
 * 再次封装Hutool的HttpUtils
 * @author FanQingChuan
 * @since 2021/8/27 17:57
 */
public class HttpUtils extends HttpUtil {


    public static String doGet(String url, Map<String,String> headers,String params){
        return HttpRequest.get(url)
                .addHeaders(headers)
                .body(params)
                .execute().body();
    }

    public static String doGet(String url, Map<String,String> headers){
        return HttpRequest.get(url)
                .addHeaders(headers)
                .execute().body();
    }

    public static String doGet(String url){
        return HttpRequest.get(url)
                .execute().body();
    }

    public static String doPost(String url){
        return HttpRequest.post(url)
                .execute().body();
    }

    public static String doPost(String url,String params){
        return HttpRequest.post(url)
                .body(params)
                .execute().body();
    }

    public static String doPost(String url, Map<String,String> headers){
        return HttpRequest.post(url)
                .addHeaders(headers)
                .execute().body();
    }

    public static String doPost(String url, Map<String,String> headers,String params){
        return HttpRequest.post(url)
                .addHeaders(headers)
                .body(params)
                .execute().body();
    }
}
