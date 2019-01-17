package com.example.administrator.plb.until;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    private String url;
    private Handler handler;
    private int what;

    public HttpUtil(String url, Handler handler, int what) {
        this.url = url;
        this.handler = handler;
        this.what = what;
    }

    public void openConn(){
        OkHttpClient client=new OkHttpClient();
        final Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("httpError","请求失败"+e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                InputStream inputStream = response.body().byteStream();
                Reader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuffer str = new StringBuffer();
                String cont;
                while ((cont = bufferedReader.readLine())!=null){
                    str.append(cont);
                }
                msg.obj = str.toString();
                msg.what = what;
                handler.sendMessage(msg);
            }
        });
    }
}
