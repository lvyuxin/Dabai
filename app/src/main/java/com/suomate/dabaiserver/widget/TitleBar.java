package com.suomate.dabaiserver.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suomate.dabaiserver.R;

/**
 * Created by lenovo on 2018/6/19.
 */

public class TitleBar extends RelativeLayout implements View.OnClickListener {
    private TextView tvTitle, tvBack;
    private ImageView ivTitle;
    private TextView tvRightText;
    private ImageView ivRightIcon;
    private String title,backTitle;
    private LinearLayout llRightMenu;
    private CallBack callBack;
    private RightMenuListener menuListener;
    private View ivLine;
    private TitleBackListener titleBackListener;
    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.title_bar_layout, this, true);
        View bgView = findViewById(R.id.money_bar_parent);
        ImageView backIm = (ImageView) findViewById(R.id.im_back);
        tvTitle = findViewById(R.id.tv_bar_title);
        ivTitle = findViewById(R.id.tb_title_iv);
        tvRightText = findViewById(R.id.tv_right_text);
        ivRightIcon = findViewById(R.id.iv_right_icon);
        llRightMenu = findViewById(R.id.ll_right_menu);
        tvBack =findViewById(R.id.tv_back);
        ivLine=findViewById(R.id.tb_line);
        backIm.setOnClickListener(this);
        llRightMenu.setOnClickListener(this);
        tvBack.setOnClickListener(this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
//        int bgColor = ta.getColor(R.styleable.TitleBar_backImg, getResources().getColor(R.color.app_bar_grey));
        title = ta.getString(R.styleable.TitleBar_textTitle);
        String rightText = ta.getString(R.styleable.TitleBar_rightText);
        int rightIcon = ta.getResourceId(R.styleable.TitleBar_rightIcon,R.mipmap.btn_tupian_tianjia);
        tvRightText.setText(rightText);
        ivRightIcon.setImageResource(rightIcon);
        int titleTextColor = ta.getColor(R.styleable.TitleBar_titleColor, getResources().getColor(R.color.white));
        boolean hasIvTitle = ta.getBoolean(R.styleable.TitleBar_hasIvTitle, false);
        boolean hasRightIcon=ta.getBoolean(R.styleable.TitleBar_hasRightIcon,false);
        boolean hasLine=ta.getBoolean(R.styleable.TitleBar_hasLine,true);
        int backImgId = ta.getResourceId(R.styleable.TitleBar_backImg, R.mipmap.icon_back);
        int titleSrc=ta.getResourceId(R.styleable.TitleBar_TitleSrc,R.mipmap.icon_config);
        boolean hasTvBack=ta.getBoolean(R.styleable.TitleBar_hasTvBack,false);
        backTitle=ta.getString(R.styleable.TitleBar_tvBack);
        backIm.setImageResource(backImgId);
        //设置title字体颜色
        tvTitle.setTextColor(titleTextColor);
        //设置背景
//        bgView.setBackgroundColor(bgColor);
        //是否是title是图片
        if (hasIvTitle) {
            ivTitle.setVisibility(VISIBLE);
            ivTitle.setImageResource(titleSrc);
        } else {
            ivTitle.setVisibility(GONE);
        }
        //是否显示右边的图片
        if (hasRightIcon) {
            ivRightIcon.setVisibility(VISIBLE);
        }else{
            ivRightIcon.setVisibility(GONE);
        }
        if (hasLine) {
            ivLine.setVisibility(VISIBLE);
        }else{
            ivLine.setVisibility(GONE);
        }
        if (hasTvBack) {
            backIm.setVisibility(GONE);
            tvBack.setVisibility(VISIBLE);
        }else{
            backIm.setVisibility(VISIBLE);
            tvBack.setVisibility(GONE);
        }
        //设置标题
        tvTitle.setText(title);
        ta.recycle();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.im_back:
                if (callBack != null) {
                    callBack.clickBack(v);
                } else {
                    ((Activity) getContext()).finish();
                }
                break;
            case R.id.ll_right_menu:
                if(menuListener != null){
                    menuListener.onMenuClick();
                }
                break;
            case R.id.tv_back:
                if (titleBackListener!=null) {
                    titleBackListener.ontitleBack();
                }
                break;
        }
    }

    public interface CallBack {
        void clickBack(View back);
    }

    public void setBackListener(CallBack callBack) {
        this.callBack = callBack;
    }
    public void setOnTitleBackListener(TitleBackListener titleBackListener) {
        this.titleBackListener = titleBackListener;
    }


    public interface RightMenuListener{
        void onMenuClick();
    }


    public interface TitleBackListener{
        void ontitleBack();
    }
    public void setOnRightMenuClickListener(RightMenuListener menuListener){
        this.menuListener = menuListener;
    }
    public void setBackTitle(String backTitle) {
        this.backTitle = backTitle;
        tvBack.setText(backTitle);
    }
    public void setTextTitle(String title) {
        this.title = title;
        tvTitle.setText(title);
    }
}
