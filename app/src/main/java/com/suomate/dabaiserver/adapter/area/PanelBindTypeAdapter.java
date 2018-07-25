package com.suomate.dabaiserver.adapter.area;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.CommonBean;

import java.util.List;

/**
 * Created by fanxi on 2018/7/24.
 */

public class PanelBindTypeAdapter extends BaseQuickAdapter<CommonBean.BindTypeBean,BaseViewHolder> {

    public PanelBindTypeAdapter(int layoutResId, @Nullable List<CommonBean.BindTypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonBean.BindTypeBean item) {
        helper.setText(R.id.bind_type_tv,item.getName());
    }
}
