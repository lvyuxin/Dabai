package com.suomate.dabaiserver.activity.function;

import android.os.Bundle;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.yanzhenjie.nohttp.RequestMethod;

import butterknife.BindView;

public class ScenceDetailActivity extends BaseActivity {
    @BindView(R.id.tb)
    TitleBar tb;
    private int sceneId;
//    private MoreDetailDialog moreDetailDialog;
    @Override
    protected int bindLayout() {
        return R.layout.activity_scence_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        sceneId = bundle.getInt("sceneId");
//        moreDetailDialog=new MoreDetailDialog(this,R.layout.dialog_scence_detail,true,getSupportFragmentManager());
        requestData();
        bindEnvent();
    }

    private void bindEnvent() {
        tb.setOnRightMenuClickListener(new TitleBar.RightMenuListener() {
            @Override
            public void onMenuClick() {
//                moreDetailDialog.show();

            }
        });

    }

    private void requestData() {
        AbstractRequest request = buildRequest(UrlUtils.SCENCE_DETAIL, Content.STRING_TYPE, RequestMethod.GET, null);
        request.add("sceneId", sceneId);
        executeNetwork(1, holdonMsg, request);
    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        switch (what) {
            case 1:
                break;
        }
    }
}
