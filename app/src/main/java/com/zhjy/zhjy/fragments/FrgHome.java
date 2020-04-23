package com.zhjy.zhjy.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;
import com.zhjy.zhjy.Constant.HttpConstant;
import com.zhjy.zhjy.R;
import com.zhjy.zhjy.adapters.HomePageRecyAdapter;
import com.zhjy.zhjy.beans.User;
import com.zhjy.zhjy.utils.ParseUserUtil;

import java.util.ArrayList;
import java.util.List;

public class FrgHome extends Fragment {
    private static final String TAG="FrgHome";

    private View view;
    private SmartRefreshLayout refreshLayout;
    private RecyclerView rv;
    private HomePageRecyAdapter mAdapter;

    private List<User> list_user = new ArrayList<>();
    private int page = 1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                Log.e(TAG,"what==1");
                List<User> lists=new ArrayList<>();
                    lists=(List<User>) msg.obj;
                    list_user=lists;
                    initRv(view);
            }else if(msg.what==2){
                Log.e(TAG,"whate==2");
                List<User> lists=new ArrayList<>();
                lists=(List<User>) msg.obj;
                for(int i=0;i<lists.size();i++){
                    User user=new User();
                    user.setNickname(lists.get(i).getNickname());
                    user.setAge(lists.get(i).getAge());
                    user.setHeardpic_url(lists.get(i).getHeardpic_url());
                    user.setLocation(lists.get(i).getLocation());
                    user.setHometown(lists.get(i).getHometown());
                    user.setPhone(lists.get(i).getPhone());
                    user.setIsmarried(lists.get(i).isIsmarried());
                    user.setHeight(lists.get(i).getHeight());
                    user.setWeight(lists.get(i).getWeight());
                    user.setWork(lists.get(i).getWork());
                    user.setSalary(lists.get(i).getSalary());
                    list_user.add(user);
                }
                if (lists.size()>0) {
                    refreshLayout.finishLoadMore();
                } else {
                    refreshLayout.finishLoadMoreWithNoMoreData();//显示全部加载完成，并不再触发加载更事件
                }
                refreshLayout.finishLoadMore();
                initRv(view);

            }
        }
    };
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_home, null);
        initView(view);
        return view;
    }
    private void initView(View view){
        refreshLayout=view.findViewById(R.id.refreshLayout);

        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);//取消内容不满一页时开启上拉加载功能
        refreshLayout.setEnableAutoLoadMore(false);//使上拉加载具有弹性效果
        refreshLayout.setEnableOverScrollDrag(false);//禁止越界拖动（1.0.4以上版本）
        refreshLayout.setEnableOverScrollBounce(false);//关闭越界回弹功能
        refreshLayout.setEnableAutoLoadMore(true);//没有上拉，快速滚动列表，Footer自己冒上来了？
        //加载更多
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载结束之后的逻辑
                 LoadMoreData();
//                mAdapter.notifyDataSetChanged();

           }

        });
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData();
                refreshLayout.finishRefresh(2000);
            }
        });
        refreshLayout.autoRefresh();//自动刷新

    }

    private void initRv(View view) {
        rv = view.findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        mAdapter = new HomePageRecyAdapter(getActivity(), list_user);
        rv.setAdapter(mAdapter);
    }
    private  void refreshData() {
        page=1;
        getUserData(new HttpConstant().ACTION_SHOWALL,page,new HttpConstant().COMMENT_PAGE_SIZE,1);
    }

    /**
     * 获取用户数据
     * @param action
     */
    private void getUserData(String action,int page,int pagesize,final int what){
        String url = new HttpConstant().url_path;
        final Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add("action", action);
        request.add("page", page);
        request.add("pagesize", pagesize);
        RequestQueue queue = NoHttp.newRequestQueue();
        queue.add(0, request, new SimpleResponseListener<String>() {
            @Override
            public void onSucceed(int i, Response<String> response) {
                Log.e(TAG,response.get());
               Message msg=new Message();
               msg.obj=new ParseUserUtil().parsejson(response.get());
               msg.what=what;
               mHandler.sendMessage(msg);
            }
            @Override
            public void onFailed(int what, Response<String> response) {
                Log.e(TAG,"请求错误："+response.getException().getMessage());
                Toast.makeText(getActivity(),"网络请求错误："+response.getException().getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void LoadMoreData() {
        page++;
        getUserData(new HttpConstant().ACTION_SHOWALL,page,new HttpConstant().COMMENT_PAGE_SIZE,2);
    }
}
