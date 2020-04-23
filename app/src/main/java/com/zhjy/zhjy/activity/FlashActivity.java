package com.zhjy.zhjy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhjy.zhjy.MainActivity;
import com.zhjy.zhjy.R;
import com.zhjy.zhjy.utils.SystemUtil;

public class FlashActivity extends AppCompatActivity {
    //停留的时长
    private static final long DELAY_TIME = 1000;

    private Boolean isLogin=false;//用户之前是否已经登录
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        SystemUtil.initSystemBarTint(this, getResources().getColor(R.color.transparent_bg));
        SystemUtil.setAndroidNativeLightStatusBar(this, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进入主程序页面
                if(isLogin){
                    startActivity(new Intent(FlashActivity.this, MainActivity.class));
                }else{
                    startActivity(new Intent(FlashActivity.this, LoginActivity.class));
                }

                finish();
            }
        }, DELAY_TIME);
    }
}
