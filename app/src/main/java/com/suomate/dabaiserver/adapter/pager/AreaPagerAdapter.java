package com.suomate.dabaiserver.adapter.pager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by fanxi on 2018/7/23.
 */

public class AreaPagerAdapter extends PagerAdapter {
    private List<ImageView> imageViews;

    public AreaPagerAdapter(List<ImageView> imageViews) {
        this.imageViews = imageViews;

    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position));

    }
}
