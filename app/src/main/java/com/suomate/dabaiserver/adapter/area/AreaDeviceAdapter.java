package com.suomate.dabaiserver.adapter.area;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.AreaDeviceEntity;
import com.suomate.dabaiserver.utils.DeviceUtils;
import com.suomate.dabaiserver.utils.LogUtils;

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
        ImageView ivIcon = helper.getView(R.id.area_device_iv);
        DeviceUtils.setIcon(ivIcon, item.getDevice_icon());
//        LogUtils.e("fancyicon:"+item.getDevice_icon());
        helper.setChecked(R.id.check_switch,item.isSelect());
        helper.addOnClickListener(R.id.check_switch);

    }
}
