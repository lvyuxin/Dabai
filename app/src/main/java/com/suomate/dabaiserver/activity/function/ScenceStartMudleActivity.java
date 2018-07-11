package com.suomate.dabaiserver.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.widget.dialog.ChooseTimeDialog;
import com.suomate.dabaiserver.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class ScenceStartMudleActivity extends BaseActivity {
    private Intent intent;
    @BindView(R.id.tb)
    TitleBar tb;
    private String hour,minute1;
    private int modeType;

    @Override
    protected int bindLayout() {
        return R.layout.activity_scence_start_mudle;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.title_color);
        intent = getIntent();
        bindEvent();
    }

    private void bindEvent() {
        tb.setOnRightMenuClickListener(new TitleBar.RightMenuListener() {
            @Override
            public void onMenuClick() {
                getIntent().putExtra("type",modeType);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    @OnClick({R.id.tv_instant_start, R.id.tv_time_start, R.id.tv_linkage_start, R.id.tv_positon_start})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_instant_start:
                intent.putExtra("type", 1);
                modeType = 1;
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.tv_time_start:
                modeType = 2;
//                mTimepicker.setVisibility(View.VISIBLE);
                new ChooseTimeDialog(this,R.style.basedialog_style,true).show();
                break;
            case R.id.tv_linkage_start:
                modeType = 3;
                break;
            case R.id.tv_positon_start:
                modeType = 4;
                break;
        }
    }
}
