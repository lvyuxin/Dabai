package com.suomate.dabaiserver.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.RequestInfoBean;

import java.util.List;

/**
 * Created by fanxi on 2018/7/5.
 */

public class StartSceneTaskAdapter extends BaseQuickAdapter<RequestInfoBean.ExecuteDevice,BaseViewHolder> {
    public StartSceneTaskAdapter(int layoutResId, @Nullable List<RequestInfoBean.ExecuteDevice> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RequestInfoBean.ExecuteDevice item) {
        helper.setText(R.id.task_name,item.getName());
        if (item.isSelect()) {
            helper.setChecked(R.id.btn_check,true);
        }else{
            helper.setChecked(R.id.btn_check,false);
        }
//        helper.addOnClickListener(R.id.btn_check);
    }
}
