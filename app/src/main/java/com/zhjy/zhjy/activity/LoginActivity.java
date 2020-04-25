package com.zhjy.zhjy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zhjy.zhjy.Constant.HttpConstant;
import com.zhjy.zhjy.R;
import com.zhjy.zhjy.utils.HttpRequestUtil;


public class LoginActivity extends BaseActivity {
    private static final String TAG="LoginActivity";
    private EditText edit_phone;
    private Button btn_login;
    private String str_phone;

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                 if(msg.obj.toString().contains("true")){
                     Intent in_psd=new Intent(LoginActivity.this,PsdActivity.class);
                     in_psd.putExtra("phone",str_phone);
                     finish();
                     startActivity(in_psd);
                 }else{
                     Intent in_psd=new Intent(LoginActivity.this,RegisterActivity.class);
                     in_psd.putExtra("phone",str_phone);
                     startActivity(in_psd);
                     Toast.makeText(LoginActivity.this,"用户不存在",Toast.LENGTH_SHORT).show();
                 }
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        edit_phone = findViewById(R.id.edit_phone);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_phone = edit_phone.getText().toString().trim();
                if (str_phone.equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else if (str_phone.length() != 11) {
                    Toast.makeText(LoginActivity.this, "手机号格式不对", Toast.LENGTH_SHORT).show();
                } else {
//                    checkUser(str_phone);
                    new HttpRequestUtil().getRequestData(new HttpConstant().url_path+"/login.php",new HttpConstant().ACTION_CHECK_USER,str_phone,null,null,null,mHandler);
                }
            }
        });
    }
}
