package com.suomate.dabaiserver.bean;

/**
 * Created by fanxi on 2018/7/12.
 */

public class IoBean {

    /**
     * device_id : 109
     * area_id : 7
     * classify_id : 10
     * device_name : 人感1
     * port : 1
     * main_engine_id : 62
     * device_icon : http://101.201.50.1:808
     * type : MI0606A
     * search_version : 0
     * show_version : 无用
     * is_thirdly : 0
     * address : {254.0.62.1};
     * device_background_img : null
     * guid : 123456975
     * control_type : humanFeeling
     * panel_number : 0
     * other_guid : null
     * is_fictitious : 0
     * val : 17
     */

    private int device_id;
    private int area_id;
    private int classify_id;
    private String device_name;
    private int port;
    private String main_engine_id;
    private String device_icon;
    private String type;
    private String search_version;
    private String show_version;
    private int is_thirdly;
    private String address;
    private Object device_background_img;
    private int guid;
    private String control_type="";
    private int panel_number;
    private Object other_guid;
    private int is_fictitious;
    private String val;
    //自定义属性
    private boolean isSelect;
    private int iotype;//装态判断 1 代表无人  2代表有人

    public boolean isSelect() {
        return isSelect;
    }


    public int getIotype() {
        return iotype;
    }

    public void setIotype(int iotype) {
        this.iotype = iotype;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public int getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(int classify_id) {
        this.classify_id = classify_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getMain_engine_id() {
        return main_engine_id;
    }

    public void setMain_engine_id(String main_engine_id) {
        this.main_engine_id = main_engine_id;
    }

    public String getDevice_icon() {
        return device_icon;
    }

    public void setDevice_icon(String device_icon) {
        this.device_icon = device_icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSearch_version() {
        return search_version;
    }

    public void setSearch_version(String search_version) {
        this.search_version = search_version;
    }

    public String getShow_version() {
        return show_version;
    }

    public void setShow_version(String show_version) {
        this.show_version = show_version;
    }

    public int getIs_thirdly() {
        return is_thirdly;
    }

    public void setIs_thirdly(int is_thirdly) {
        this.is_thirdly = is_thirdly;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getDevice_background_img() {
        return device_background_img;
    }

    public void setDevice_background_img(Object device_background_img) {
        this.device_background_img = device_background_img;
    }

    public int getGuid() {
        return guid;
    }

    public void setGuid(int guid) {
        this.guid = guid;
    }

    public String getControl_type() {
        return control_type;
    }

    public void setControl_type(String control_type) {
        this.control_type = control_type;
    }

    public int getPanel_number() {
        return panel_number;
    }

    public void setPanel_number(int panel_number) {
        this.panel_number = panel_number;
    }

    public Object getOther_guid() {
        return other_guid;
    }

    public void setOther_guid(Object other_guid) {
        this.other_guid = other_guid;
    }

    public int getIs_fictitious() {
        return is_fictitious;
    }

    public void setIs_fictitious(int is_fictitious) {
        this.is_fictitious = is_fictitious;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
