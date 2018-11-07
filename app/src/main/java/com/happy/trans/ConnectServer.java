package com.happy.trans;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectServer {

    public String requestGet(String drug_Url) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(drug_Url)
                .get()
                .addHeader("Cache-Control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        String responseResult = response.body().string().replaceAll("\\(","").replaceAll("\\)","");

        return responseResult;
    }
}
