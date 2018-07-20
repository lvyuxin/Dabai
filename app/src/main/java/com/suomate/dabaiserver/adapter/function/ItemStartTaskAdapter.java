package com.suomate.dabaiserver.adapter.function;

import android.support.annotation.Nullable;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.ScenceStartTaskBean;

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
        helper.setText(R.id.item_device_name, item.getName());
        CheckBox checkBox = helper.getView(R.id.item_device_check);
        checkBox.setChecked(item.isSelect());
        helper.addOnClickListener(R.id.item_device_check);
        helper.setText(R.id.item_excute_device_detail,item.getDetail());
    }
}
