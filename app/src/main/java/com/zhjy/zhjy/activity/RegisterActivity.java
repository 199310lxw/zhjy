package com.zhjy.zhjy.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;
import com.zhjy.zhjy.Constant.HttpConstant;
import com.zhjy.zhjy.R;
import com.zhjy.zhjy.beans.Code;
import com.zhjy.zhjy.utils.CountDownTimerUtils;

import java.lang.invoke.MethodType;

public class RegisterActivity extends BaseActivity {
    private static final String TAG="RegisterActivity";
    private static final int SEND_SMS=1;
    private static final int GET_CODE=2;

    private TextView rst_send_code;
    private EditText edit_code;
    private EditText edit_psd;
    private Button btn_register;

    private CountDownTimerUtils countDownTimer;//获取验证码倒计时
    private String str_phone;
    private String smscode;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==GET_CODE){
                 smscode=msg.obj.toString();
                sendSms(str_phone,"{\"code\":\""+smscode+"\"}",SEND_SMS);
            }else if(msg.what==SEND_SMS){
                Toast.makeText(RegisterActivity.this,"验证码发送成功",Toast.LENGTH_SHORT).show();
            }

        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        str_phone=getIntent().getStringExtra("phone");
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
                getSmsCode("smscode",GET_CODE);
            }
        });

        edit_code=findViewById(R.id.edit_code);
        edit_psd=findViewById(R.id.edit_psd);
        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_code=edit_code.getText().toString();
                String str_psd=edit_psd.getText().toString();
                if(str_code.equals("")){
                    Toast.makeText(RegisterActivity.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                }else if(str_code.equals(smscode)){
                     if(!str_psd.equals("")){
                         Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                     }else{
                         Toast.makeText(RegisterActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                     }
                }else{
                    Toast.makeText(RegisterActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 阿里云发送短信验证码
     * @param phone
     */
    private void sendSms(final String phone,final String code,final int what){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", new HttpConstant().AccessKey_ID,new HttpConstant().AccessKeySecret);
                IAcsClient client = new DefaultAcsClient(profile);
                CommonRequest request = new CommonRequest();
                request.setMethod(com.aliyuncs.http.MethodType.POST);
                request.setDomain("dysmsapi.aliyuncs.com");
//        request.setSysDomain("dysmsapi.aliyuncs.com");
                request.setAction("SendSms");
                request.setVersion("2017-05-25");
//        request.setSysVersion("2017-05-25");
//        request.setSysAction("SendSms");
                request.putQueryParameter("RegionId", "cn-hangzhou");
                request.putQueryParameter("PhoneNumbers", phone);
                request.putQueryParameter("SignName", "珍婚交友");
                request.putQueryParameter("TemplateCode", "SMS_187954945");
                request.putQueryParameter("TemplateParam", code);
                try {
                    CommonResponse response = client.getCommonResponse(request);
                    Message msg=new Message();
                    msg.obj=response.getData();
                    msg.what=what;
                    mHandler.sendMessage(msg);

                } catch (ServerException e) {
                    e.printStackTrace();
                } catch (ClientException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 服务器获取验证码数据
     * @param action
     * @param what
     */
    private void  getSmsCode(String action,final int what){
           String url=new HttpConstant().url_path+"/smscode.php";
            final Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
            request.add("action", action);
            RequestQueue queue = NoHttp.newRequestQueue();
            queue.add(0, request, new SimpleResponseListener<String>() {
                @Override
                public void onSucceed(int i, Response<String> response) {
                    Log.e(TAG, response.get());
                    Message msg = new Message();
                    Gson gson=new Gson();
                    Code code=gson.fromJson(response.get(),Code.class);
                    String code_num=code.getDatas();
                    msg.obj = code_num;
                    msg.what=what;
                    mHandler.sendMessage(msg);
                }

                @Override
                public void onFailed(int what, Response<String> response) {
                    Log.e(TAG, "请求错误：" + response.getException().getMessage());
                }
            });

    }
}
