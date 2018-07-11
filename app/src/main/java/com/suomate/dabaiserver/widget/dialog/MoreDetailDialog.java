//package com.suomate.dabaiserver.widget;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.view.ViewPager;
//
//import com.suomate.dabaiserver.R;
//import com.suomate.dabaiserver.adapter.ScenceMoreVPAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by fanxi on 2018/7/10.
// */
//
//public class MoreDetailDialog extends BaseDialog {
//    private List<Fragment> fragments=new ArrayList<>();
//    private List<String> tabList=new ArrayList<>();
//    private ViewPager viewPager;
//    private TabLayout tab;
//    private ScenceMoreVPAdapter pagerAdapter;
//    private FragmentManager fragmentManager;
//    public MoreDetailDialog(@NonNull Context context, int themeResId, boolean isShowBottom,FragmentManager fragmentManager) {
//        super(context, themeResId, isShowBottom);
//        this.fragmentManager=fragmentManager;
//        initView();
//
//    }
//
//    private void initView() {
//        viewPager=findViewById(R.id.viewpager);
//        tab=findViewById(R.id.tablayout);
////        getSupportFragmentManager();
//        pagerAdapter=new ScenceMoreVPAdapter(fragmentManager,fragments,tabList);
//        tab.setupWithViewPager(viewPager);
//
//    }
//
//
//}
