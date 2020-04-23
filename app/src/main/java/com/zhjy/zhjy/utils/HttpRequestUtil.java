package com.zhjy.zhjy.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;
import com.zhjy.zhjy.activity.LoginActivity;


public class HttpRequestUtil {
    private static final String TAG="HttpRequestUtil";
    public void  getRequestData(String url, String action, String phone,String psd, String page, String pagesize,final Handler mHandler){
        final Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add("action", action);
        request.add("phone", phone);
        request.add("psd", psd);
        request.add("page", page);
        request.add("pagesize", pagesize);

        RequestQueue queue = NoHttp.newRequestQueue();
        queue.add(0, request, new SimpleResponseListener<String>() {
            @Override
            public void onSucceed(int i, Response<String> response) {
                Log.e(TAG, response.get());
                Message msg = new Message();
                msg.obj =  response.get();
                mHandler.sendMessage(msg);
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                Log.e(TAG, "请求错误：" + response.getException().getMessage());
            }
        });
    }
}
