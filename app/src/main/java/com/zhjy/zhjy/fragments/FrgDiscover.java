package com.zhjy.zhjy.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhjy.zhjy.R;
import com.zhjy.zhjy.adapters.DiscoverAdapter;
import com.zhjy.zhjy.adapters.HomePageRecyAdapter;

import java.util.ArrayList;
import java.util.List;

public class FrgDiscover extends Fragment {
    private SmartRefreshLayout refreshLayout;
    private RecyclerView rv;
    private DiscoverAdapter mAdapter;

    private List<String> list_data = new ArrayList<>();
    private int page_count = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_discover, null);
        initView(view);
        return view;
    }
    private void initView(View view){
        refreshLayout=view.findViewById(R.id.refreshLayout);
        initRv(view);
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
//                LoadMoreData();
//                if (page_count < 4) {
//                    refreshLayout.finishLoadMore();
//                } else {
//                    refreshLayout.finishLoadMoreWithNoMoreData();//显示全部加载完成，并不再触发加载更事件
//                }
//                mAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore(2000);
            }

        });
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                refreshData();
//                mAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh(2000);
            }
        });
        refreshLayout.autoRefresh();//自动刷新
    }

    private void initRv(View view) {
        rv = view.findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        mAdapter = new DiscoverAdapter(getActivity(), refreshData());
        rv.setAdapter(mAdapter);
    }
    private List<String> refreshData() {
        if (list_data != null) {
            list_data.clear();
            page_count = 0;
        }
        for (int i = 0; i < 20; i++) {
            list_data.add("征婚用户" + i );
        }
        return list_data;
    }
}
