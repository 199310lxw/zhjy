package com.zhjy.zhjy.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhjy.zhjy.R;
import com.zhjy.zhjy.activity.MyRoseActivity;
import com.zhjy.zhjy.activity.PersonLikeActivity;
import com.zhjy.zhjy.activity.SettingActivity;
import com.zhjy.zhjy.activity.ShareActivity;
import com.zhjy.zhjy.activity.TrendsActivity;
import com.zhjy.zhjy.activity.UserDataSettingActivity;
import com.zhjy.zhjy.activity.WechatCodeActivity;

public class FrgUser extends Fragment implements View.OnClickListener {
    private RelativeLayout re_info;
    private RelativeLayout re_like;
    private RelativeLayout re_rose;

    private RelativeLayout re_trends;
    private RelativeLayout re_wechat_code;
    private RelativeLayout re_setting;
    private RelativeLayout re_share;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_user, null);
        initView(view);
        return view;
    }
    private void initView(View view){
        re_rose=view.findViewById(R.id.re_rose);
        re_like=view.findViewById(R.id.re_like);
        re_info=view.findViewById(R.id.re_info);

        re_trends=view.findViewById(R.id.re_trends);
        re_wechat_code=view.findViewById(R.id.re_wechat_code);
        re_setting=view.findViewById(R.id.re_setting);
        re_share=view.findViewById(R.id.re_share);

        re_rose.setOnClickListener(this);
        re_like.setOnClickListener(this);
        re_info.setOnClickListener(this);

        re_trends.setOnClickListener(this);
        re_wechat_code.setOnClickListener(this);
        re_setting.setOnClickListener(this);
        re_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.re_rose:
                Intent in_rose=new Intent(getActivity(), MyRoseActivity.class);
                startActivity(in_rose);
                break;
            case R.id.re_like:
                Intent in_like=new Intent(getActivity(), PersonLikeActivity.class);
                startActivity(in_like);
                break;
            case R.id.re_info:
                Intent in_data=new Intent(getActivity(), UserDataSettingActivity.class);
                startActivity(in_data);
                break;

            case R.id.re_trends:
                Intent in_trends=new Intent(getActivity(), TrendsActivity.class);
                startActivity(in_trends);
                break;
            case R.id.re_wechat_code:
                Intent in_wechatcode=new Intent(getActivity(), WechatCodeActivity.class);
                startActivity(in_wechatcode);
                break;
            case R.id.re_setting:
                Intent in_setting=new Intent(getActivity(), SettingActivity.class);
                startActivity(in_setting);
                break;
             case R.id.re_share:
                Intent in_share=new Intent(getActivity(), ShareActivity.class);
                startActivity(in_share);
                break;
        }
    }
}
