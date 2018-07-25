package com.suomate.dabaiserver.adapter.area;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.bean.AreaSelectListBean;

import java.util.List;

/**
 * Created by fanxi on 2018/7/23.
 */

public class AreaAdapter extends BaseQuickAdapter<AreaSelectListBean.DataBean,BaseViewHolder> {
    private Context context;
    public AreaAdapter(Context context,int layoutResId, @Nullable List<AreaSelectListBean.DataBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AreaSelectListBean.DataBean item) {
        helper.setText(R.id.item_area_name, item.getArea_name());
        ImageView img = helper.getView(R.id.img_area);
        if (!TextUtils.isEmpty(item.getArea_background_img())) {
            Glide.with(context).load(item.getArea_background_img()).into(img);
        }
    }
}
