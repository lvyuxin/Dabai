package com.suomate.dabaiserver.bean;

/**
 * Created by fanxi on 2018/7/4.
 */

public class FunctionDeviceListBean {

    /**
     * device_id : 61
     * area_id : 7
     * classify_id : 7
     * device_name : 地暖1
     * port : 3
     * main_engine_id : 56
     * device_icon : http://101.201.50.1:808
     * type : SMT-DNZH
     * search_version : 0
     * show_version : 无用
     * is_thirdly : 0
     * address : {254.0.56.1};
     * device_background_img : null
     * guid : 123456975
     * control_type : null
     * panel_number : null
     * other_guid : null
     * is_use : 0
     * area_name : 测试区域
     * classify_name : 地暖
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
    private Object control_type;
    private Object panel_number;
    private Object other_guid;
    private int is_use;
    private String area_name;
    private String classify_name;
    private boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
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

    public Object getControl_type() {
        return control_type;
    }

    public void setControl_type(Object control_type) {
        this.control_type = control_type;
    }

    public Object getPanel_number() {
        return panel_number;
    }

    public void setPanel_number(Object panel_number) {
        this.panel_number = panel_number;
    }

    public Object getOther_guid() {
        return other_guid;
    }

    public void setOther_guid(Object other_guid) {
        this.other_guid = other_guid;
    }

    public int getIs_use() {
        return is_use;
    }

    public void setIs_use(int is_use) {
        this.is_use = is_use;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }
}
