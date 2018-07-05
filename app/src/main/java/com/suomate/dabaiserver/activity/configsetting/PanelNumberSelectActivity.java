package com.suomate.dabaiserver.activity.configsetting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PanelNumberSelectActivity extends BaseActivity {
    @BindView(R.id.reycler)
    RecyclerView recyclerView;
    private BaseQuickAdapter adapter;
    private List<String> list = new ArrayList<>();
    private int panel_number;
    Intent intent;

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_test, list) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv_item, item);
            }
        };
       intent= getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle.getInt("type") == 1) {//io面板
            list.add("一按键无文字面板");
            list.add("二按键无文字面板");
            list.add("三按键无文字面板");
            list.add("四按键无文字面板");
            list.add("五按键无文字面板");
            list.add("六按键无文字面板");
        } else {
            list.add("一按键有文字面板");
            list.add("二按键有文字面板");
            list.add("三按键有文字面板");
            list.add("四按键有文字面板");
            list.add("五按键有文字面板");
            list.add("六按键有文字面板");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        bindEvent();
    }

    private void bindEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                panel_number = position + 1;
                intent.putExtra("panel_number",panel_number);
                setResult(RESULT_OK,intent);
                showToast("选择成功！");
                finish();
            }
        });

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_panel_number_select;
    }


}
