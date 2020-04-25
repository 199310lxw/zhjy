package com.zhjy.zhjy.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.zhjy.zhjy.R;
import com.zhjy.zhjy.utils.SystemUtil;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img_back;
    private Button btn_login_out;
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
        btn_login_out=findViewById(R.id.btn_login_out);
        img_back.setOnClickListener(this);
        btn_login_out.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_login_out:
                SharedPreferences sp = getSharedPreferences("zhjy", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                finish();
                break;
        }
    }
}
