package com.zhjy.zhjy.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhjy.zhjy.R;
import com.zhjy.zhjy.utils.SystemUtil;

public class LoginActivity extends BaseActivity {
    private ImageView bg;
    private String url="http://192.168.1.6/zhjy/heardpic/13682377116/one.jpg";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        SystemUtil.initSystemBarTint(this, getResources().getColor(R.color.transparent_bg));
//        SystemUtil.setAndroidNativeLightStatusBar(this, true);//状态栏字体颜色,true为黑色，false为白色
        initView();
    }
    private void initView(){
        bg=findViewById(R.id.bg);
    }
}
