package com.suomate.dabaiserver.activity.function;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.adapter.function.FunctionDeviceAdapter;
import com.suomate.dabaiserver.adapter.pager.AreaPagerAdapter;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.bean.FunctionDeviceListBean;
import com.suomate.dabaiserver.bean.Result;
import com.suomate.dabaiserver.utils.CommonMethod;
import com.suomate.dabaiserver.utils.LogUtils;
import com.suomate.dabaiserver.utils.UrlUtils;
import com.suomate.dabaiserver.utils.config.Content;
import com.suomate.dabaiserver.utils.config.ContentStr;
import com.suomate.dabaiserver.utils.net.AbstractRequest;
import com.suomate.dabaiserver.widget.TitleBar;
import com.yanzhenjie.nohttp.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FuntionDeviceListActivity extends BaseActivity {
    @BindView(R.id.tb)
    TitleBar tb;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private String classify_id;
    private List<FunctionDeviceListBean> list = new ArrayList<>();
    private FunctionDeviceAdapter adapter;
    public static final int TYPE_LIGHT = 4, TYPE_DIMMER = 5, TYPE_FRESHAIR = 6, TYPE_DINUAN = 7, TYPE_CURTAIN = 8;
    private String mDiNuanOpenPort;


    //点
    private AreaPagerAdapter pagerAdapter;
    private LinearLayout linearDot;
    private View moveDot;
    private int distance;//两点之间的距离
    private List<ImageView> imageViews = new ArrayList<>();
    private int currentPage = 0;
    private ViewPager pager;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            pager.setCurrentItem(currentPage++ % imageViews.size());
            handler.sendEmptyMessageDelayed(1, 3000);
        }
    };

    @Override
    protected int bindLayout() {
        return R.layout.activity_funtion_device_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.title_color);
        recycler.setLayoutManager(new GridLayoutManager(this,2));
        Bundle bundle = getIntent().getExtras();
        classify_id = bundle.getString("classify_id");
        tb.setTextTitle(bundle.getString("name"));
        adapter = new FunctionDeviceAdapter(R.layout.item_function_list, list);
        View header = LayoutInflater.from(this).inflate(R.layout.item_area_device_header, null);
        adapter.addHeaderView(header);
        recycler.setAdapter(adapter);
        pager = header.findViewById(R.id.pager);
        linearDot = header.findViewById(R.id.recom_graydot);
        moveDot = header.findViewById(R.id.recom_whiltdot);
        pagerAdapter = new AreaPagerAdapter(imageViews);
        setDot();
        pagerAdapter.notifyDataSetChanged();
        requestData();
        bindevent();
    }

    private void bindevent() {

        //点击开关按钮
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                FunctionDeviceListBean deviceEntity = list.get(position);
                switch (view.getId()) {
                    case R.id.check_switch:
                        showToast("点了");
                        //4路电灯和8路电灯
                        if (Content.SERIAL.SWITCH8.equals(deviceEntity.getType().trim()) || Content.SERIAL.SWITCH8_OLD.equals(deviceEntity.getType().trim())
                                || Content.SERIAL.SWITCH4.equals(deviceEntity.getType().trim()) || Content.SERIAL.SWITCH4_OLD.equals(deviceEntity.getType().trim())) {
                            sendOrder(deviceEntity, TYPE_LIGHT);
                            //调光模块
                        } else if (Content.SERIAL.DIMMING4.equals(deviceEntity.getType().trim()) || Content.SERIAL.DIMMING4_OLD.equals(deviceEntity.getType().trim())
                                || Content.SERIAL.DIMMING2.equals(deviceEntity.getType().trim()) || Content.SERIAL.DIMMING2_OLD.equals(deviceEntity.getType().trim())) {
                            sendOrder(deviceEntity, TYPE_DIMMER);
                            //新风模块
                        } else if (Content.SERIAL.EXTENDED_XINFEN.equals(deviceEntity.getType().trim())) {
                            sendOrder(deviceEntity, TYPE_FRESHAIR);
                            //地暖模块
                        } else if (Content.SERIAL.EXTENDED_DINUAN.equals(deviceEntity.getType().trim())) {
                            sendOrder(deviceEntity, TYPE_DINUAN);
                            //窗帘
                        } else if (Content.SERIAL.SWITCH485_CURTAIN.equals(deviceEntity.getType().trim()) || Content.SERIAL.SWITCH4_CURTAIN.equals(deviceEntity.getType().trim())
                                || Content.SERIAL.SWITCH4_CURTAIN_OLD.equals(deviceEntity.getType().trim())) {
                            sendOrder(deviceEntity, TYPE_CURTAIN);
                        }
                        break;
                }
            }
        });
    }

    private void sendOrder(FunctionDeviceListBean deviceEntity, int type) {
        String content = null;
        AbstractRequest request = buildRequest(UrlUtils.SEND_ORDER, Content.STRING_TYPE, RequestMethod.GET, null);
        request.add("guid", getGuid() + "");
        request.add("extend", "0");
        request.add("name", deviceEntity.getDevice_name());
        if (deviceEntity.isOpen()) {//状态是开的状态，关的动作
            switch (type) {
                case TYPE_LIGHT:
                    content = ContentStr.Control_Device.CLOSE_LIGHT;
                    break;
                case TYPE_DIMMER:
                    content = ContentStr.Control_Device.CLOSE_DIMMER;
                    break;
                case TYPE_FRESHAIR:
                    content = ContentStr.Control_Device.CLOSE_FRESH_AIR;
                    break;
                case TYPE_DINUAN:
                    switch (deviceEntity.getPort()) {
                        case 1:
                            mDiNuanOpenPort = "01;";
                            break;
                        case 2:
                            mDiNuanOpenPort = "15;";
                            break;
                        case 3:
                            mDiNuanOpenPort = "29;";
                            break;
                        case 4:
                            mDiNuanOpenPort = "3D;";
                            break;
                    }
                    content = "SET;00000" + mDiNuanOpenPort;
                    break;
                case TYPE_CURTAIN:
                    String str = "SET;000000";
                    switch (deviceEntity.getPort()) {//100%
                        case 1:
                            content = str + CommonMethod.getHexStringValue(11) + ";";
                            break;
                        case 2:
                            content = str + CommonMethod.getHexStringValue(36) + ";";
                            break;
                        case 3:
                            content = str + CommonMethod.getHexStringValue(61) + ";";
                            break;
                        case 4:
                            content = str + CommonMethod.getHexStringValue(86) + ";";
                            break;

                    }
                    break;
            }
            deviceEntity.setOpen(false);
        } else {//打开的动作
            switch (type) {
                case TYPE_LIGHT:
                    content = ContentStr.Control_Device.OPEN_LIGHT;
                    break;
                case TYPE_DIMMER:
                    content = ContentStr.Control_Device.OPEN_DIMMER;
                    break;
                case TYPE_FRESHAIR:
                    content = ContentStr.Control_Device.OPEN_FRESH_AIR;
                    break;
                case TYPE_DINUAN:
                    switch (deviceEntity.getPort()) {
                        case 1:
                            mDiNuanOpenPort = "02;";
                            break;
                        case 2:
                            mDiNuanOpenPort = "16;";
                            break;
                        case 3:
                            mDiNuanOpenPort = "2A;";
                            break;
                        case 4:
                            mDiNuanOpenPort = "3E;";
                            break;
                    }
                    content = "SET;00000" + mDiNuanOpenPort;
                    break;
                case TYPE_CURTAIN:
                    String str = "SET;000000";
                    switch (deviceEntity.getPort()) {//100%
                        case 1:
                            content = str + CommonMethod.getHexStringValue(1) + ";";
                            break;
                        case 2:
                            content = str + CommonMethod.getHexStringValue(26) + ";";
                            break;
                        case 3:
                            content = str + CommonMethod.getHexStringValue(51) + ";";
                            break;
                        case 4:
                            content = str + CommonMethod.getHexStringValue(76) + ";";
                            break;
                    }
                    break;
            }
            deviceEntity.setOpen(true);
        }
        request.add("content", content + deviceEntity.getAddress() + "$0D$0A");
        LogUtils.e(TAG, "guid:" + getGuid() + "  content:" + content + deviceEntity.getAddress() + "$0D$0A" + "  name:" + deviceEntity.getDevice_name());
        executeNetwork(4, holdonMsg, request);
    }

    private void requestData() {
        AbstractRequest request = buildRequest(UrlUtils.FUNCTION_DEVICE_LIST, Content.LIST_TYPE, RequestMethod.POST, FunctionDeviceListBean.class);
        request.add("guid", getGuid());
        request.add("classify_id", classify_id);
        executeNetwork(1, holdonMsg, request);
    }

    @Override
    protected <T> void mHandle200(int what, Result<T> result) {
        super.mHandle200(what, result);
        list.addAll((List<FunctionDeviceListBean>) result.getData());
        adapter.notifyDataSetChanged();
    }


    //设置圆点的方法
    private void setDot() {
        View view;
        ImageView imageView;
        for (int i = 0; i < 3; i++) {
            imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532344233011&di=629058674b660ee3db691ae48b378d74&imgtype=0&src=http%3A%2F%2Fwww.glsdzs.com%2Fuploads%2Fallimg%2F180224%2F1-1P224155535223.jpg").into(imageView);
            imageViews.add(imageView);
            //灰点
            view = new View(this);
            view.setBackgroundResource(R.drawable.shape_dot_grey);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(16, 16);
            if (i != 0) {
                params.leftMargin = 30;
            }
            view.setLayoutParams(params);
            linearDot.addView(view);


        }
        setMoveDot();
    }

    private void setMoveDot() {
        moveDot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                distance = linearDot.getChildAt(1).getLeft() - linearDot.getChildAt(0).getLeft();

            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                handler.removeMessages(1);
                position = position % imageViews.size();
                float left = distance * (position + positionOffset);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) moveDot.getLayoutParams();
                params.leftMargin = Math.round(left);
                moveDot.setLayoutParams(params);
                handler.sendEmptyMessageDelayed(1, 3000);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


}
