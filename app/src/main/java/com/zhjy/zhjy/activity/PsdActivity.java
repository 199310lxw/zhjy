package com.zhjy.zhjy.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.zhjy.zhjy.MainActivity;
import com.zhjy.zhjy.R;
import com.zhjy.zhjy.beans.User;
import com.zhjy.zhjy.utils.HttpRequestUtil;
import com.zhjy.zhjy.utils.ParseUtil;

import java.util.ArrayList;
import java.util.List;

public class PsdActivity extends BaseActivity {

    private Boolean isLogin=false;

    private String str_phone;

    private EditText edit_psd;
    private Button btn_login;
    private  List<User> list_users=new ArrayList<>();
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
//                    Toast.makeText(PsdActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                list_users=new ParseUtil().parseUserjson(msg.obj.toString());
                    if(list_users.size()>0){
                        Intent in_main=new Intent(PsdActivity.this, MainActivity.class);
                        isLogin=true;        //登录状态标志
                        SharedPreferences sharedPreferences = getSharedPreferences("zhjy", Context.MODE_PRIVATE); //私有数据
                        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                        editor.putString("user_phone",list_users.get(0).getPhone());
                        editor.putString("user_nickname",list_users.get(0).getNickname());
                        editor.putString("user_heardpic_url",list_users.get(0).getHeardpic_url());
//                    editor.putString("user_phone",list_users.get(0).getPhone());
                        editor.putBoolean("isLogin",isLogin);
                        editor.commit();
                        startActivity(in_main);
                        finish();
                }else{
                    Toast.makeText(PsdActivity.this,"密码错误",Toast.LENGTH_SHORT).show();

                }
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psd);
        str_phone=getIntent().getStringExtra("phone");
        initView();
    }
    private void initView(){
        edit_psd=findViewById(R.id.edit_psd);
        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_psd=edit_psd.getText().toString().trim();
                new HttpRequestUtil().getRequestData(new HttpConstant().url_path+"/login.php",new HttpConstant().ACTION_LOGIN,str_phone,str_psd,null,null,mHandler);
            }
        });
    }

}
