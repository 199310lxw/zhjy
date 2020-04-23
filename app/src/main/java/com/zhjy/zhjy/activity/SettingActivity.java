package com.zhjy.zhjy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhjy.zhjy.R;
import com.zhjy.zhjy.utils.SystemUtil;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        SystemUtil.initSystemBarTint(this, getResources().getColor(R.color.transparent_bg));
        SystemUtil.setAndroidNativeLightStatusBar(this, true);//状态栏字体颜色,true为黑色，false为白色
        initView();
    }
    private void initView(){
        img_back=findViewById(R.id.img_back);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }
}
