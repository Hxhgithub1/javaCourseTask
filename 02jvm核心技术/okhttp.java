package com.hxh.nio;

import okhttp3.*;
import sun.rmi.runtime.Log;

import java.io.IOException;

public class okhttp {
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        System.out.println("run: " + response.body().string());

    }
}
