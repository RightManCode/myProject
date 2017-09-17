package com.wishland.www.aicaipiao2.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.lzy.okgo.OkGo;
import com.umeng.analytics.MobclickAgent;
import com.wishland.www.aicaipiao2.utils.SPUtils;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by admin on 2017/9/12.
 */

public abstract class BaseStyleActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
            super.onCreate(savedInstanceState);
            SPUtils.context = this;
            initVariable();
            initView();
            initDate();
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(this);
        super.onDestroy();
        //根据 Tag 取消请求
    }

    protected abstract void initVariable();

    protected abstract void initDate();

    protected abstract void initView();


}
