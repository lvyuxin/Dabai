package com.suomate.dabaiserver.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.ConfigSingleDeviceBean;

import java.util.List;

/**
 * Created by lenovo on 2018/6/26.
 */

public class ConfigSingleDeviceAdapter extends BaseQuickAdapter<ConfigSingleDeviceBean, BaseViewHolder> {
    public ConfigSingleDeviceAdapter(int layoutResId, @Nullable List<ConfigSingleDeviceBean> data) {
            super(layoutResId, data);



    }

    @Override
    protected void convert(BaseViewHolder helper, ConfigSingleDeviceBean item) {
        helper.setText(R.id.port_tv, item.getPort()+"");
    }
}
