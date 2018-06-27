package com.suomate.dabaiserver.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import java.util.ArrayList;

public class LoadAndRefreshActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ArrayList<String> datas = new ArrayList<>();
    private BaseQuickAdapter adapter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_load_and_refresh;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });

        setDatas();
    }

    private void setDatas(){
        recyclerView = findViewById(R.id.recyclerview1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        for(int i =0;i<20;i++){
            datas.add("item" + i);
        }
        adapter = new BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_test,datas){

            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_item,item);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showToast("点击了第" + position + "项");
            }
        });
    }
}
