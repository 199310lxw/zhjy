package com.zhjy.zhjy.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhjy.zhjy.R;
import com.zhjy.zhjy.utils.SystemUtil;

public class PersonLikeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img_back;

    private SwipeRefreshLayout swipe;
    private RecyclerView recyclerView;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_like);
        SystemUtil.initSystemBarTint(this, getResources().getColor(R.color.transparent_bg));
        SystemUtil.setAndroidNativeLightStatusBar(this, true);//状态栏字体颜色,true为黑色，false为白色
        initView();
    }

    private void initView() {
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(this);


        recyclerView=findViewById(R.id.recyclerView);

        swipe=findViewById(R.id.swipe);
        //设置圆圈颜色
        swipe.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

    }

    private void  refreshData(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //三秒后停止刷新
                swipe.setRefreshing(false);
            }
        },3000);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }
}
