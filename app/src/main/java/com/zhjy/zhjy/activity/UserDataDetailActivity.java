package com.zhjy.zhjy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.zhjy.zhjy.R;
import com.zhjy.zhjy.views.AntoLineUtil;

public class UserDataDetailActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG="UserDataDetailActivity";
    private ImageView img_back;
    private ImageView img_collect;

    private ImageView img_heardpic;
    private TextView tv_connect;

    private TextView tv_age,tv_location,tv_hometown,tv_ismarried,tv_height,tv_weight,tv_work,tv_salary;

    private Boolean isCollected=false;//是否已经关注

    private int  photo[]={R.mipmap.one,R.mipmap.two,R.mipmap.three,R.mipmap.four};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_detail);
        initView();
    }

    private void initView() {
        img_back = findViewById(R.id.img_back);
        tv_connect = findViewById(R.id.tv_connect);
        img_collect=findViewById(R.id.img_collect);
        img_back.setOnClickListener(this);
        img_collect.setOnClickListener(this);
        tv_connect.setOnClickListener(this);

        img_heardpic=findViewById(R.id.img_heardpic);

        tv_age=findViewById(R.id.tv_age);
        tv_location=findViewById(R.id.tv_location);
        tv_hometown=findViewById(R.id.tv_hometown);
        tv_ismarried=findViewById(R.id.tv_ismarried);
        tv_height=findViewById(R.id.tv_height);
        tv_weight=findViewById(R.id.tv_weight);
        tv_work=findViewById(R.id.tv_work);
        tv_salary=findViewById(R.id.tv_salary);
        getData();

        addView();

    }
    private void  getData(){
        Intent intent=getIntent();
        tv_age.setText(intent.getIntExtra("age",0)+"");
        tv_location.setText(intent.getStringExtra("location"));
        tv_hometown.setText(intent.getStringExtra("hometown"));
        if(intent.getIntExtra("ismarried",0)==1){
            tv_ismarried.setText("已婚");
        }else{
            tv_ismarried.setText("未婚");
        }
        tv_height.setText(intent.getIntExtra("height",0)+"");
        tv_weight.setText(intent.getIntExtra("weight",0)+"");
        tv_work.setText(intent.getStringExtra("work"));
        tv_salary.setText(intent.getStringExtra("salary"));
        Glide.with(this).load(intent.getStringExtra("heardpic_url")).error(R.mipmap.ic_launcher).into(img_heardpic);
    }
    //添加交友条件
     private void addView(){
         //图片组
         AntoLineUtil photoGroup =findViewById(R.id.photo_group); // 此处是找到父控件LinearLayout
         for (int i = 0; i<photo.length; i++) {
             // 用以下方法将layout布局文件换成view
             LayoutInflater inflater = getLayoutInflater();
             View view = inflater.inflate(R.layout.item_photo_group,null);
             TextView textView = view.findViewById(R.id.tv_condition);
             ImageView img_photo=view.findViewById(R.id.img_photo_group);
             img_photo.setImageResource(photo[i]);
             final int num=i;
             img_photo.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Toast.makeText(UserDataDetailActivity.this,"我点击了第"+num+"项", Toast.LENGTH_SHORT).show();
                 }
             });
             photoGroup.addView(view);
         }

         //交友条件
         AntoLineUtil conditionGroup =findViewById(R.id.friend_condition_group); // 此处是找到父控件LinearLayout
         for (int i = 0; i<10; i++) {
             // 用以下方法将layout布局文件换成view
             LayoutInflater inflater = getLayoutInflater();
             View view = inflater.inflate(R.layout.item_friend_condition,null);
             TextView textView = view.findViewById(R.id.tv_condition);
             textView.setText("不vsdvssdv限");

             conditionGroup.addView(view);
         }

     }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.img_collect:
                 if(!isCollected){
                     img_collect.setImageResource(R.mipmap.collect_pressed);
                     isCollected=true;//变成已关注
                 }else{
                     img_collect.setImageResource(R.mipmap.collect_unpressed);
                     isCollected=false;
                 }
                break;
            case R.id. tv_connect:

                break;
        }
    }
}
