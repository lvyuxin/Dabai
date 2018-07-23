package com.suomate.dabaiserver.adapter.function;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.CommonBean;
import com.suomate.dabaiserver.utils.config.ContentStr;

import java.util.List;

/**
 * Created by fanxi on 2018/7/20.
 */

public class DelayTimeAdapter extends BaseQuickAdapter<CommonBean.ExecuteDeviceBean,BaseViewHolder> {
    public DelayTimeAdapter(int layoutResId, @Nullable List<CommonBean.ExecuteDeviceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonBean.ExecuteDeviceBean item) {
        helper.addOnClickListener(R.id.iv_task_delete);
        helper.addOnClickListener(R.id.delay_time);
        helper.setText(R.id.delay_name,item.getName());
        helper.setText(R.id.delay_time,item.getDelay()+" s"+ ContentStr.Symbol.triangle_open);

    }
}
