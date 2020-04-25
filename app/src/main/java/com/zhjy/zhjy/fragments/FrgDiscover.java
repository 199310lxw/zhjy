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
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
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
import com.zhjy.zhjy.adapters.DiscoverAdapter;
import com.zhjy.zhjy.beans.Treads;
import com.zhjy.zhjy.utils.ParseUtil;

import java.util.ArrayList;
import java.util.List;

public class FrgDiscover extends Fragment {
    private static final String TAG="FrgDiscover";

    private View view;
    private SmartRefreshLayout refreshLayout;
    private ClassicsFooter footer;
    private RecyclerView rv;
    private DiscoverAdapter mAdapter;

    private List<String> list_data = new ArrayList<>();
    private int page= 1;
    private List<Treads> list_treads;
    private List<Treads> lists_temp=new ArrayList<>();

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                Log.e(TAG,"what==1");
                lists_temp= new ParseUtil().parseTreadsjson(msg.obj.toString());
                list_treads=lists_temp;
                initRv(view);
            }else if(msg.what==2){
                Log.e(TAG,"whate==2");
//                List<User> lists=new ArrayList<>();
//                lists=(List<User>) msg.obj;
                lists_temp= new ParseUtil().parseTreadsjson(msg.obj.toString());
                for(int i=0;i<lists_temp.size();i++){
                    Treads treads=new Treads();
                    treads.setPhone(lists_temp.get(i).getPhone());
                    treads.setNickname(lists_temp.get(i).getNickname());
                    treads.setAge(lists_temp.get(i).getAge());
                    treads.setHeardpic_url(lists_temp.get(i).getHeardpic_url());
                    treads.setLocation(lists_temp.get(i).getLocation());
                    treads.setHometown(lists_temp.get(i).getHometown());
                    treads.setDate_time(lists_temp.get(i).getDate_time());
                    treads.setContent(lists_temp.get(i).getContent());
                    treads.setTreads_picture_url(lists_temp.get(i).getTreads_picture_url());
                    list_treads.add(treads);
                }
                if (lists_temp.size()>0) {
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = View.inflate(getActivity(), R.layout.fragment_discover, null);
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
        footer=view.findViewById(R.id.footer);
        refreshLayout.autoRefresh();//自动刷新
        //加载更多
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                LoadMoreData();
                refreshLayout.finishLoadMore(2000);
            }

        });
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshData();
                footer.setFinishDuration(0);//参数很明显是时间参数，将参数设为0，就没用白框了
                refreshLayout.finishRefresh(2000);
            }
        });

    }

    private void initRv(View view) {
        rv = view.findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        mAdapter = new DiscoverAdapter(getActivity(),  list_treads);
        rv.setAdapter(mAdapter);
    }

    /**
     * 下拉刷新加载数据
     */
    private void refreshData() {
        page=1;
        getTreadsData(page,new HttpConstant().COMMENT_PAGE_SIZE,1);
    }

    /**
     * 上拉加载数据
     */
    private void LoadMoreData() {
        ++page;
        getTreadsData(page,new HttpConstant().COMMENT_PAGE_SIZE,2);
    }

    /**
     * 获取用户动态数据
     * @param page
     * @param pagesize
     * @param what
     */
    private void getTreadsData(int page,int pagesize,final int what){
        String url = new HttpConstant().url_path+"/treads.php";
        final Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add("page", page);
        request.add("pagesize", pagesize);
        RequestQueue queue = NoHttp.newRequestQueue();
        queue.add(0, request, new SimpleResponseListener<String>() {
            @Override
            public void onSucceed(int i, Response<String> response) {
                Log.e(TAG,response.get());
                Message msg=new Message();
                msg.obj=response.get();
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

}
