package com.suomate.dabaiserver.bean;

import java.util.List;

/**
 * Created by fanxi on 2018/7/9.
 */

public class SenceDetailBean {


    private List<InfoBean> info;
    private List<DeviceInfoBean> deviceInfo;
    private List<?> sceneInfo;

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public List<DeviceInfoBean> getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(List<DeviceInfoBean> deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public List<?> getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(List<?> sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    public static class InfoBean {
        /**
         * area_name : 测试区域
         * scene_id : 6
         * scene_name : 联动MEI灯哦哦2222
         * scene_img : null
         * area_id : 7
         * guid : 123456975
         * icon : 1
         * phone_info : null
         * app_info : null
         * email_info : null
         * click_touch : 1
         * is_delete : 1
         * json_type : 3
         * number : 14
         * json : {"type":"key","key":[{"key":"003e0001","obj":"fe100011","mode":18,"fback":"00000000","fbmode":0}]}
         * is_hidden : 0
         */

        private String area_name;
        private int scene_id;
        private String scene_name;
        private Object scene_img;
        private int area_id;
        private String guid;
        private String icon;
        private Object phone_info;
        private Object app_info;
        private Object email_info;
        private int click_touch;
        private int is_delete;
        private int json_type;
        private int number;
        private String json;
        private int is_hidden;

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public int getScene_id() {
            return scene_id;
        }

        public void setScene_id(int scene_id) {
            this.scene_id = scene_id;
        }

        public String getScene_name() {
            return scene_name;
        }

        public void setScene_name(String scene_name) {
            this.scene_name = scene_name;
        }

        public Object getScene_img() {
            return scene_img;
        }

        public void setScene_img(Object scene_img) {
            this.scene_img = scene_img;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Object getPhone_info() {
            return phone_info;
        }

        public void setPhone_info(Object phone_info) {
            this.phone_info = phone_info;
        }

        public Object getApp_info() {
            return app_info;
        }

        public void setApp_info(Object app_info) {
            this.app_info = app_info;
        }

        public Object getEmail_info() {
            return email_info;
        }

        public void setEmail_info(Object email_info) {
            this.email_info = email_info;
        }

        public int getClick_touch() {
            return click_touch;
        }

        public void setClick_touch(int click_touch) {
            this.click_touch = click_touch;
        }

        public int getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(int is_delete) {
            this.is_delete = is_delete;
        }

        public int getJson_type() {
            return json_type;
        }

        public void setJson_type(int json_type) {
            this.json_type = json_type;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }

        public int getIs_hidden() {
            return is_hidden;
        }

        public void setIs_hidden(int is_hidden) {
            this.is_hidden = is_hidden;
        }
    }

    public static class DeviceInfoBean {
        /**
         * delay : 0
         * scene_val : 00000001
         * device_id : 3
         * area_id : 7
         * classify_id : 26
         * device_name : 测试开关灯3
         * port : 3
         * main_engine_id : 11
         * device_icon : 1
         * type : ET-R0816A
         * search_version : 1
         * show_version : 1
         * is_thirdly : 0
         * address : {254.0.11.3};
         * device_background_img : 1
         * guid : 123456975
         * control_type : switchLight
         * panel_number : null
         * other_guid : null
         * is_fictitious : 0
         */

        private String delay;
        private String scene_val;
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
        private String device_background_img;
        private int guid;
        private String control_type;
        private Object panel_number;
        private Object other_guid;
        private int is_fictitious;

        public String getDelay() {
            return delay;
        }

        public void setDelay(String delay) {
            this.delay = delay;
        }

        public String getScene_val() {
            return scene_val;
        }

        public void setScene_val(String scene_val) {
            this.scene_val = scene_val;
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

        public String getDevice_background_img() {
            return device_background_img;
        }

        public void setDevice_background_img(String device_background_img) {
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

        public int getIs_fictitious() {
            return is_fictitious;
        }

        public void setIs_fictitious(int is_fictitious) {
            this.is_fictitious = is_fictitious;
        }
    }
}
