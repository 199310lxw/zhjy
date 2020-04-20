package com.zhjy.zhjy;

import android.app.Application;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.URLConnectionNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // NoHttp默认初始化。
        NoHttp.initialize(this, new NoHttp.Config()
                        .setConnectTimeout(30 * 1000) // 全局连接超时时间，单位毫秒。
                        .setReadTimeout(30 * 1000) // 全局服务器响应超时时间，单位毫秒。
//                .setCacheStore(
//                        new DiskCacheStore(this) // 配置缓存到SD卡。
//                )
                        .setCacheStore(
                                new DBCacheStore(this) // 配置缓存到数据库。
                                        .setEnable(true) // true启用缓存，fasle禁用缓存。
                        )
                        //指定使用OKHttp或者HTTPURLCONNECTION
                        .setNetworkExecutor(new URLConnectionNetworkExecutor())

        );
    }
}
