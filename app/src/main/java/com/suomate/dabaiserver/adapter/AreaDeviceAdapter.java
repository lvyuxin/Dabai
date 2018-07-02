package com.suomate.dabaiserver.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.AreaDeviceEntity;

import java.util.List;

/**
 * Created by lenovo on 2018/7/2.
 */

public class AreaDeviceAdapter extends BaseQuickAdapter<AreaDeviceEntity, BaseViewHolder> {
    public AreaDeviceAdapter(int layoutResId, @Nullable List<AreaDeviceEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaDeviceEntity item) {
        helper.setText(R.id.area_device_name, item.getDevice_name());
    }
}
