package com.zhjy.zhjy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zhjy.zhjy.R;
import com.zhjy.zhjy.utils.CountDownTimerUtils;

public class RegisterActivity extends BaseActivity {
    private TextView rst_send_code;
    private CountDownTimerUtils countDownTimer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }
    private void initView(){
        rst_send_code=findViewById(R.id.rst_send_code);
        rst_send_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 发送成功进入倒计时
                countDownTimer = new CountDownTimerUtils(rst_send_code, 60000, 1000);
                countDownTimer.start();
            }
        });
    }
}
