package com.suomate.dabaiserver.adapter.function;

import android.support.annotation.Nullable;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.ScenceStartTaskBean;
import com.suomate.dabaiserver.utils.config.ContentStr;

import java.util.List;

/**
 * Created by fanxi on 2018/7/13.
 */

public class ItemStartTaskAdapter extends BaseQuickAdapter<ScenceStartTaskBean.DeviceOrSceneInfoBean, BaseViewHolder> {
    public ItemStartTaskAdapter(int layoutResId, @Nullable List<ScenceStartTaskBean.DeviceOrSceneInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScenceStartTaskBean.DeviceOrSceneInfoBean item) {
        helper.setText(R.id.item_device_name, item.getArea_name() + ContentStr.Symbol.dot + item.getDevice_or_scene_name());
        CheckBox checkBox = helper.getView(R.id.item_device_check);
        checkBox.setChecked(item.isSelect());
        helper.addOnClickListener(R.id.item_device_check);
    }
}
