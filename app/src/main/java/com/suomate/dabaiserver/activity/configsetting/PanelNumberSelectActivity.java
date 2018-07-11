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
import com.suomate.dabaiserver.utils.config.ContentStr;

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
            list.add(ContentStr.Control_type.io_panel1);
            list.add(ContentStr.Control_type.io_panel2);
            list.add(ContentStr.Control_type.io_panel3);
            list.add(ContentStr.Control_type.io_panel4);
            list.add(ContentStr.Control_type.io_panel5);
            list.add(ContentStr.Control_type.io_panel6);
        } else {
            list.add(ContentStr.Control_type.intelligent_panel1);
            list.add(ContentStr.Control_type.intelligent_panel2);
            list.add(ContentStr.Control_type.intelligent_panel3);
            list.add(ContentStr.Control_type.intelligent_panel4);
            list.add(ContentStr.Control_type.intelligent_panel5);
            list.add(ContentStr.Control_type.intelligent_panel6);
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
                intent.putExtra("control_type",list.get(position));
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
