package com.suomate.dabaiserver.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.suomate.dabaiserver.R;
import com.suomate.dabaiserver.activity.LoadAndRefreshActivity;
import com.suomate.dabaiserver.activity.TestDataActivity;
import com.suomate.dabaiserver.base.fragment.BaseFragment;


public class MessageFragment extends BaseFragment {

    private Button btnTest;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initViews() {
        super.initViews();
        btnTest = view.findViewById(R.id.btn_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TestDataActivity.class));
            }
        });

        view.findViewById(R.id.btn_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoadAndRefreshActivity.class));
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
