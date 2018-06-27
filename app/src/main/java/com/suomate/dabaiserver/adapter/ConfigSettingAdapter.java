package com.suomate.dabaiserver.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.ConfigSettingBean;

import java.util.List;

/**
 * Created by lenovo on 2018/6/21.
 */

public class ConfigSettingAdapter extends BaseQuickAdapter<ConfigSettingBean, BaseViewHolder> {
    public ConfigSettingAdapter(int layoutResId, @Nullable List<ConfigSettingBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ConfigSettingBean item) {
        helper.setText(R.id.config_device_name, item.getName());
        helper.setText(R.id.config_device_num,"数量："+item.getDeviceCount());
    }
}
