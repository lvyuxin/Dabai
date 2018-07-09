package com.suomate.dabaiserver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.base.activity.BaseActivity;
import com.suomate.dabaiserver.fragments.AreaFragment;
import com.suomate.dabaiserver.fragments.ConfigFragment;
import com.suomate.dabaiserver.fragments.FunctionFragment;
import com.suomate.dabaiserver.fragments.MessageFragment;
import com.suomate.dabaiserver.utils.DeviceService;
import com.suomate.dabaiserver.utils.config.AppConfig;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.rg_main_menu)
    RadioGroup rgMenu;
    private ConfigFragment configFragment;
    private FunctionFragment functionFragment;
    private AreaFragment areaFragment;
    private MessageFragment messageFragment;
    private Fragment[] mFragments;
    private int curIndex;
    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setWindowStatusBarColor(R.color.title_color);
        AppConfig.getInstance().putString("guid","123456975");
        configFragment = new ConfigFragment();
        functionFragment = new FunctionFragment();
        areaFragment = new AreaFragment();
        messageFragment = new MessageFragment();
        mFragments = new Fragment[]{configFragment, functionFragment, areaFragment, messageFragment};
        initService();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fl_maincontainer, mFragments[curIndex]).show(mFragments[curIndex]).commit();
        event();
        ((RadioButton) rgMenu.getChildAt(0)).setChecked(true);
    }

//    private void initBroadCast() {
//        if (mTcpReceiver == null) {
//            mTcpReceiver = new TcpReceiver();
//            IntentFilter intentFilter = new IntentFilter(C.TCP_BroadCast.TCP_OPEN_STATE);
//            intentFilter.addAction(C.Common.UPDATE_USER);
//            intentFilter.addAction(C.Common.EXIT_USER);
//            registerReceiver(mTcpReceiver, intentFilter);
//        }
//    }

    private void initService() {
        Intent intent = new Intent(this, DeviceService.class);
        startService(intent);
    }

    private void event() {
        rgMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                for (int i = 0; i < rgMenu.getChildCount(); i++) {
                    if (rgMenu.getChildAt(i).getId() == id) {
                        setMenu(i);
                    }
                }
            }
        });
    }

    private void setMenu(int index) {
        if (curIndex == index) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(mFragments[curIndex]);
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.fl_maincontainer, mFragments[index]).show(mFragments[index]).commit();
        } else {
            ft.show(mFragments[index]).commit();
        }
        curIndex = index;
    }
}
