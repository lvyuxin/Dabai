package com.suomate.dabaiserver.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.DeviceAddBean;

import java.util.List;

/**
 * Created by fanxi on 2018/7/4.
 */

public class AddDeviceAdapter extends BaseQuickAdapter<DeviceAddBean,BaseViewHolder> {
    public AddDeviceAdapter(int layoutResId, @Nullable List<DeviceAddBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceAddBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_type,item.getType());
    }
}
