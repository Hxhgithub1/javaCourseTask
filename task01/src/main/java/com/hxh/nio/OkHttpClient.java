package com.hxh.nio;

import okhttp3.*;
import sun.rmi.runtime.Log;

import java.io.IOException;

public class OkHttpClient {
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8808";
        System.out.println(getResponse(url));

    }

    public static String getResponse(String url) throws IOException {
        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }
}
