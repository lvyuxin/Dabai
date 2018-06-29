package com.suomate.dabaiserver.activity.configsetting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.classifyListAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.ClassifyListBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.ContentConfig;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ClassifyDeviceListActivity extends BaseActivity {

    @BindView(R.id.tb)
    TitleBar tb;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private classifyListAdapter adapter;
    private List<ClassifyListBean.DataBean> list = new ArrayList<>();
    public static final int REQUEST_CODE = 101;

    @Override
    protected int bindLayout() {
        return R.layout.activity_classify_device;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        tb.setTextTitle(getString(R.string.classify));
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new classifyListAdapter(R.layout.item_test, list);
        recycler.setAdapter(adapter);
        requestData();
        bindEvent();
    }

    private void requestData() {
        AbstractRequest request = buildRequest(UrlUtils.CLASSIFY_LIST, ContentConfig.LIST_TYPE, RequestMethod.GET, ClassifyListBean.DataBean.class);
        request.add("guid",getGuid());
        executeNetwork(1, holdonMsg, request);
    }

    private void bindEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = getIntent();
                intent.putExtra("classifyName", list.get(position).getClassify_name());
                intent.putExtra("classifyId", list.get(position).getClassify_id()+"");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                AbstractRequest request = buildRequest(UrlUtils.DELETE_CLASSIFY_ITEM, ContentConfig.LIST_TYPE, RequestMethod.GET, null);
                request.add("classify_id", list.get(position).getClassify_id());
                executeNetwork(2, holdonMsg, request);
                return false;
            }
        });

        tb.setOnMenuClickListener(new TitleBar.RightMenuListener() {
            @Override
            public void onMenuClick() {
                Bundle bundle = new Bundle();
                bundle.putInt("type", ContentConfig.TYPE.CLASSIFY);
                startActivityForResult(EditNameActivity.class, bundle, REQUEST_CODE);
            }
        });

    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                result.getData();
                list.addAll((List<ClassifyListBean.DataBean>) result.getData());
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
                if (resultCode == RESULT_OK) {
                    list.clear();
                    requestData();
                }
                break;
        }
    }
}
