package com.suomate.dabaiserver.activity.configsetting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.AppConfig;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.yanzhenjie.nohttp.RequestMethod;

import butterknife.BindView;

public class EditNameActivity extends BaseActivity {

    @BindView(R.id.tb)
    TitleBar tb;
    @BindView(R.id.edit_text)
    EditText editText;
    private int type;

    @Override
    protected int bindLayout() {
        return R.layout.activity_edit_name;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        type = bundle.getInt("type");
        binEvent();
    }

    private void binEvent() {
        tb.setOnRightMenuClickListener(new TitleBar.RightMenuListener() {
            @Override
            public void onMenuClick() {
                switch (type) {
                    case Content.TYPE.AREA:
                        AbstractRequest request1 = buildRequest(UrlUtils.CUSTOM_AREA_NAME, Content.LIST_TYPE, RequestMethod.GET, null);
                        request1.add("guid", AppConfig.getInstance().getString("guid", ""));
                        request1.add("name", editText.getText().toString());
                        executeNetwork(1, holdonMsg, request1);
                        break;
                    case Content.TYPE.CLASSIFY:
                        AbstractRequest request2 = buildRequest(UrlUtils.CUSTOM_CLASSIFY_NAME, Content.LIST_TYPE, RequestMethod.GET, null);
                        request2.add("guid", AppConfig.getInstance().getString("guid", ""));
                        request2.add("name", editText.getText().toString());
                        executeNetwork(2, holdonMsg, request2);
                        break;
                    case Content.TYPE.NOMINATE:
                        Intent intent = getIntent();
                        intent.putExtra("name",editText.getText().toString());
                        setResult(RESULT_OK,intent);
                        finish();
                        break;


                }

            }
        });
    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        showToast(getResources().getString(R.string.save_sucess));
        setResult(RESULT_OK);
        finish();
    }
}
