package com.suomate.dabaiserver.bean;

/**
 * Created by fanxi on 2018/7/17.
 */

public class FunctionClassifyBean {

    /**
     * classify_id : -1
     * classify_name : 场景
     * guid : 123456975
     * classify_icon :
     * classify_background_img :
     * nail : 1
     */

    private int classify_id;
    private String classify_name;
    private int guid;
    private String classify_icon;
    private String classify_background_img;
    private int nail;

    public int getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(int classify_id) {
        this.classify_id = classify_id;
    }

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }

    public int getGuid() {
        return guid;
    }

    public void setGuid(int guid) {
        this.guid = guid;
    }

    public String getClassify_icon() {
        return classify_icon;
    }

    public void setClassify_icon(String classify_icon) {
        this.classify_icon = classify_icon;
    }

    public String getClassify_background_img() {
        return classify_background_img;
    }

    public void setClassify_background_img(String classify_background_img) {
        this.classify_background_img = classify_background_img;
    }

    public int getNail() {
        return nail;
    }

    public void setNail(int nail) {
        this.nail = nail;
    }
}
