package com.zhjy.zhjy.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhjy.zhjy.R;
import com.zhjy.zhjy.utils.SystemUtil;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemUtil.initSystemBarTint(this,getResources().getColor(R.color.transparent_bg));//状态栏颜色(透明)
        SystemUtil.setAndroidNativeLightStatusBar(this,false);//状态栏字体颜色,true为黑色，false为白色
    }
}
