package com.zhjy.zhjy.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.zhjy.zhjy.R;
import com.zhjy.zhjy.utils.SystemUtil;

public class MyRoseActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img_back;

    private RelativeLayout re_100, re_200, re_500, re_1000, re_2000, re_3000, re_5000;
    private TextView tv_num_100, tv_num_200, tv_num_500, tv_num_1000, tv_num_2000, tv_num_3000, tv_num_5000;
    private TextView tv_money_100, tv_money_200, tv_money_500, tv_money_1000, tv_money_2000, tv_money_3000, tv_money_5000;

    private Button btn_confirm;
    private int money=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrose);
        SystemUtil.initSystemBarTint(this, getResources().getColor(R.color.transparent_bg));
        SystemUtil.setAndroidNativeLightStatusBar(this, true);//状态栏字体颜色,true为黑色，false为白色
        initView();
    }

    private void initView() {
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(this);
        initRose();
        btn_confirm=findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(this);
    }

    private void initRose() {
        re_100 = findViewById(R.id.re_100);
        re_200 = findViewById(R.id.re_200);
        re_500 = findViewById(R.id.re_500);
        re_1000 = findViewById(R.id.re_1000);
        re_2000 = findViewById(R.id.re_2000);
        re_3000 = findViewById(R.id.re_3000);
        re_5000 = findViewById(R.id.re_5000);

        tv_num_100 = findViewById(R.id.tv_num_100);
        tv_num_200 = findViewById(R.id.tv_num_200);
        tv_num_500 = findViewById(R.id.tv_num_500);
        tv_num_1000 = findViewById(R.id.tv_num_1000);
        tv_num_2000 = findViewById(R.id.tv_num_2000);
        tv_num_3000 = findViewById(R.id.tv_num_3000);
        tv_num_5000 = findViewById(R.id.tv_num_5000);

        tv_money_100 = findViewById(R.id.tv_money_100);
        tv_money_200 = findViewById(R.id.tv_money_200);
        tv_money_500 = findViewById(R.id.tv_money_500);
        tv_money_1000 = findViewById(R.id.tv_money_1000);
        tv_money_2000 = findViewById(R.id.tv_money_2000);
        tv_money_3000 = findViewById(R.id.tv_money_3000);
        tv_money_5000 = findViewById(R.id.tv_money_5000);

        re_100.setOnClickListener(this);
        re_200.setOnClickListener(this);
        re_500.setOnClickListener(this);
        re_1000.setOnClickListener(this);
        re_2000.setOnClickListener(this);
        re_3000.setOnClickListener(this);
        re_5000.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_confirm:
                if(money==0){
                    Toast.makeText(MyRoseActivity.this,"请选择玫瑰花数量",Toast.LENGTH_SHORT).show();
                }else{
                    commonDialog();
                }
                break;
            case R.id.re_100:
                tv_num_100.setTextColor(getResources().getColor(R.color.colorRed));
                tv_money_100.setTextColor(getResources().getColor(R.color.colorRed));

                tv_num_200.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_200.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_500.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_500.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_1000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_1000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_2000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_2000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_3000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_3000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_5000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_5000.setTextColor(getResources().getColor(R.color.colorBlack));
                money=10;

                break;
            case R.id.re_200:
                tv_num_200.setTextColor(getResources().getColor(R.color.colorRed));
                tv_money_200.setTextColor(getResources().getColor(R.color.colorRed));

                tv_num_100.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_100.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_500.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_500.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_1000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_1000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_2000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_2000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_3000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_3000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_5000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_5000.setTextColor(getResources().getColor(R.color.colorBlack));
                money=20;
                break;
            case R.id.re_500:

                tv_num_500.setTextColor(getResources().getColor(R.color.colorRed));
                tv_money_500.setTextColor(getResources().getColor(R.color.colorRed));

                tv_num_200.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_200.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_100.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_100.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_1000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_1000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_2000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_2000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_3000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_3000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_5000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_5000.setTextColor(getResources().getColor(R.color.colorBlack));
                money=50;
                break;
            case R.id.re_1000:
                tv_num_1000.setTextColor(getResources().getColor(R.color.colorRed));
                tv_money_1000.setTextColor(getResources().getColor(R.color.colorRed));

                tv_num_200.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_200.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_500.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_500.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_100.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_100.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_2000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_2000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_3000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_3000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_5000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_5000.setTextColor(getResources().getColor(R.color.colorBlack));
                money=100;
                break;
            case R.id.re_2000:
                tv_num_2000.setTextColor(getResources().getColor(R.color.colorRed));
                tv_money_2000.setTextColor(getResources().getColor(R.color.colorRed));

                tv_num_200.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_200.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_500.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_500.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_1000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_1000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_100.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_100.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_3000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_3000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_5000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_5000.setTextColor(getResources().getColor(R.color.colorBlack));
                money=200;
                break;
            case R.id.re_3000:
                tv_num_3000.setTextColor(getResources().getColor(R.color.colorRed));
                tv_money_3000.setTextColor(getResources().getColor(R.color.colorRed));

                tv_num_200.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_200.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_500.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_500.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_1000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_1000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_2000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_2000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_100.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_100.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_5000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_5000.setTextColor(getResources().getColor(R.color.colorBlack));
                money=300;
                break;
            case R.id.re_5000:
                tv_num_5000.setTextColor(getResources().getColor(R.color.colorRed));
                tv_money_5000.setTextColor(getResources().getColor(R.color.colorRed));

                tv_num_200.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_200.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_500.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_500.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_1000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_1000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_2000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_2000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_3000.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_3000.setTextColor(getResources().getColor(R.color.colorBlack));

                tv_num_100.setTextColor(getResources().getColor(R.color.colorBlack));
                tv_money_100.setTextColor(getResources().getColor(R.color.colorBlack));
                money=500;
                break;
        }
    }

    private void commonDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyRoseActivity.this);
//        builder.setTitle("普通对话框");// 设置标题
        // builder.setIcon(R.drawable.ic_launcher);//设置图标
        builder.setMessage("请选择支付方式");// 为对话框设置内容
        // 为对话框设置取消按钮
        builder.setNegativeButton("微信支付", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                Toast.makeText(MyRoseActivity.this,"你选择了微信支付",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("支付宝支付", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                Toast.makeText(MyRoseActivity.this,"你选择了支付宝支付",Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();// 使用show()方法显示对话框
    }
}
