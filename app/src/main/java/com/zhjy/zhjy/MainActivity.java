package com.zhjy.zhjy;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zhjy.zhjy.activity.BaseActivity;
import com.zhjy.zhjy.fragments.FrgDiscover;
import com.zhjy.zhjy.fragments.FrgHome;
import com.zhjy.zhjy.fragments.FrgInfo;
import com.zhjy.zhjy.fragments.FrgUser;
import com.zhjy.zhjy.ustils.FragmentUtil;
import com.zhjy.zhjy.ustils.SystemUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {
    //是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useThemestatusBarColor = true;
    //是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
    protected boolean useStatusBarColor = true;

    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    private Boolean isLogin = false;//是否登录,默认没登陆

    private RadioGroup mRadioGroup;

    private FragmentManager fm;
    private List<Fragment> mFragments;
    private FrgHome mHomeFrg;
    private FrgDiscover mDiscocerFrg;
    private FrgInfo mInfoFrg;
    private FrgUser mUserFrg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化主页三个fragment，并添加进fragments里面
     */
    private void initFragments() {
        fm = getSupportFragmentManager();
        mFragments = new ArrayList<>();

        mHomeFrg = new FrgHome();
        mDiscocerFrg = new FrgDiscover();
        mInfoFrg=new FrgInfo();
        mUserFrg = new FrgUser();

        mFragments.add(mHomeFrg);
        mFragments.add(mDiscocerFrg);
        mFragments.add(mInfoFrg);
        mFragments.add(mUserFrg);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        mRadioGroup = findViewById(R.id.tab_bar);
        initFragments();
        new FragmentUtil(fm).showFragment(R.id.frg_container, mFragments.get(0), isLogin);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.tab_home:
                        new FragmentUtil(fm).showFragment(R.id.frg_container, mFragments.get(0), isLogin);
//                        SystemUtil.setAndroidNativeLightStatusBar(MainActivity.this,false);
                        break;
                    case R.id.tab_discover:
                        new FragmentUtil(fm).showFragment(R.id.frg_container, mFragments.get(1), isLogin);
//                        SystemUtil.setAndroidNativeLightStatusBar(MainActivity.this,false);

                        break;
                    case R.id.tab_info:
                        new FragmentUtil(fm).showFragment(R.id.frg_container, mFragments.get(2), isLogin);
//                        SystemUtil.setAndroidNativeLightStatusBar(MainActivity.this,false);
                        break;
                    case R.id.tab_user:
                        new FragmentUtil(fm).showFragment(R.id.frg_container, mFragments.get(3), isLogin);
//                        SystemUtil.setAndroidNativeLightStatusBar(MainActivity.this,false);
                        break;
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦错第一次按键记录
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }

            }, 2000);
        } else {//退出程序
            this.finish();
            System.exit(0);
        }
    }
}
