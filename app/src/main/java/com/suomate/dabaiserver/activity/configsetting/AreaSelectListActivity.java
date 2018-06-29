package com.suomate.dabaiserver.activity.configsetting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.AreaSelectListAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.AreaSelectListBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.ContentConfig;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AreaSelectListActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.tb)
    TitleBar tb;
    private AreaSelectListAdapter adapter;
    private List<AreaSelectListBean.DataBean> list = new ArrayList<>();
    public static final int REQUEST_CODE = 103;

    @Override
    protected int bindLayout() {
        return R.layout.activity_area_select_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.black);
        tb.setTextTitle(getString(R.string.area));
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AreaSelectListAdapter(R.layout.item_test, list);
        recycler.setAdapter(adapter);
        requestData();
        bindEvent();
    }

    private void requestData() {
        AbstractRequest request = buildRequest(UrlUtils.AREA_LIST, ContentConfig.LIST_TYPE, RequestMethod.GET, AreaSelectListBean.DataBean.class);
        request.add("guid",getGuid());
        executeNetwork(1, holdonMsg, request);
    }

    private void bindEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = getIntent();
                intent.putExtra("areaName", list.get(position).getArea_name());
                intent.putExtra("areaId", list.get(position).getArea_id()+"");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                AbstractRequest request = buildRequest(UrlUtils.DELETE_AREA_ITEM, ContentConfig.LIST_TYPE, RequestMethod.GET, null);
                request.add("classify_id", list.get(position).getArea_id());
                executeNetwork(2, holdonMsg, request);
                return false;
            }
        });

        tb.setOnMenuClickListener(new TitleBar.RightMenuListener() {//自定义
            @Override
            public void onMenuClick() {
                Bundle bundle=new Bundle();
                bundle.putInt("type",1);
                startActivityForResult(EditNameActivity.class, bundle, REQUEST_CODE);
            }
        });
    }

    /**
     * 处理成功的逻辑
     *
     * @param what
     * @param result
     * @param <T>
     */
    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                result.getData();
                list.addAll((List<AreaSelectListBean.DataBean>) result.getData());
                adapter.notifyDataSetChanged();
                break;
            case 2:
                showToast("删除成功!");
                list.clear();
                requestData();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode==RESULT_OK) {
                    list.clear();
                    requestData();
                }
                break;
        }

    }
}
