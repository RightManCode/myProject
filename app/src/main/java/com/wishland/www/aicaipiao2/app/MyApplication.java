package com.wishland.www.aicaipiao2.app;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.wishland.www.aicaipiao2.utils.SPUtils;

import java.util.logging.Level;

/**
 * Created by admin on 2017/9/12.
 */

public class MyApplication extends Application {
    private static MyApplication instance;

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }

    public void initApp() {
        instance = this;
        SPUtils.context = this;
        initOkGO();
    }


    private void initOkGO() {
        OkGo.init(this);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.put("Accept-Language", "zh-cn,zh,en-0,en;");    //header不支持中文
            OkGo.getInstance()
                    .debug("OkGo", Level.INFO, true)
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间
                    .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                    .setRetryCount(3)
//                    .setCookieStore(new PersistentCookieStore())        //cookie持久化存储，如果cookie不过期，则一直有效
                    .setCertificates()                               //方法一：信任所有证书,不安全有风险
                    .addCommonHeaders(headers); //设置全局公共头
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
