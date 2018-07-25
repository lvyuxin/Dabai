package com.suomate.dabaiserver.adapter.area;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.AreaSelectListBean;

import java.util.List;

/**
 * Created by lenovo on 2018/6/26.
 */

public class AreaSelectListAdapter extends BaseQuickAdapter<AreaSelectListBean.DataBean, BaseViewHolder> {
//    private Context context;

    public AreaSelectListAdapter(int layoutResId, @Nullable List<AreaSelectListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaSelectListBean.DataBean item) {
        helper.setText(R.id.tv_item, item.getArea_name());
//        ImageView img = helper.getView(R.id.img_area);
//        if (!TextUtils.isEmpty(item.getArea_background_img())) {
//            Glide.with(context).load(item.getArea_background_img()).into(img);
//        }

    }
}
