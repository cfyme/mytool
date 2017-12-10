/**
 * Copyright (c) 2016, 791650277@qq.com(Mr.kiwi) All Rights Reserved.
 */
package com.fshows.commons.util;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 项目：fs_test_java_api
 * 包名：com.fshows.test.commons
 * 功能：
 * 时间：2016-11-19 17:57
 * 作者：Mr.Kiwi
 */
public class OkHttpUtils {

    private final static int READ_TIMEOUT = 30*100;
    private final static int WRITE_TIMEOUT = 30*100;
    private final static int CONNECT_TIMEOUT = 30*100;

    private static OkHttpClient client = null;

    static {

        if (client == null) {

            client = new OkHttpClient().newBuilder()//
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)//
                .build();
        }

    }

    public static String get(String url) throws IOException {

        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {

            return response.body().string();
        } else {

            return response.message();
        }
    }

    public static String post(String url, Map<String, Object> params) throws IOException {

        FormBody.Builder builder = new FormBody.Builder();
        for (String s : params.keySet()) {

            Object v = params.get(s);
            builder.add(s, v != null ? v.toString() : "");
        }


        Request request = new Request.Builder().url(url).post(builder.build()).build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {

            return response.body().string();
        } else {

            return response.message();
        }
    }
}
