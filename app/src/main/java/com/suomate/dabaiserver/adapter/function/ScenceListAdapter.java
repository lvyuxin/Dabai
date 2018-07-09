package com.suomate.dabaiserver.adapter.function;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.RequestInfoBean;

import java.util.List;

/**
 * Created by fanxi on 2018/7/6.
 */

public class ScenceListAdapter extends BaseQuickAdapter<RequestInfoBean.ScenceBean,BaseViewHolder> {
    public ScenceListAdapter(int layoutResId, @Nullable List<RequestInfoBean.ScenceBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, RequestInfoBean.ScenceBean item) {
        helper.setText(R.id.tv_name,item.getScene_name());
    }
}
