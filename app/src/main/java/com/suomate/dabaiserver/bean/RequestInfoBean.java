package com.suomate.dabaiserver.bean;

/**
 * Created by fanxi on 2018/7/5.
 */

public class RequestInfoBean {


    public static class PhoneMessageBean {
        public PhoneMessageBean(String phone, String info) {
            this.phone = phone;
            this.info = info;
        }

        /**
         * phone : 12345678901
         * info : 短信提醒
         */

        private String phone;
        private String info;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }//message

        @Override
        public String toString() {
            return "{" +
                    "phone='" + phone + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
    }

    public static class EmailMessageBean {

        /**
         * email : youxiang@xx.com
         * info : 邮箱提醒
         */

        private String email;
        private String info;

        public EmailMessageBean(String email, String info) {
            this.email = email;
            this.info = info;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "{" +
                    "email='" + email + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
    }

    public static class ExecuteDevice {
        @Override
        public String toString() {
            return "{" +
                    "device_or_scene_id='" + device_or_scene_id + '\'' +
                    ", val='" + val + '\'' +
                    ", delay=" + delay +
                    ", type='" + type + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }

        /**
         * device_or_scene_id : 62
         * val : 01000001
         * delay : 0
         * type : 1
         * address : null
         */

        private String device_or_scene_id;
        private String val;
        private int delay;
        private String type;
        private String address;
        //自定义的设备名字
        private String name;
        private boolean isSelect;


        public ExecuteDevice(String name, String device_or_scene_id, String val, int delay, String type, String address) {
            this.device_or_scene_id = device_or_scene_id;
            this.val = val;
            this.delay = delay;
            this.type = type;
            this.address = address;
            this.name = name;
        }
        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDevice_or_scene_id() {
            return device_or_scene_id;
        }

        public void setDevice_or_scene_id(String device_or_scene_id) {
            this.device_or_scene_id = device_or_scene_id;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public int getDelay() {
            return delay;
        }

        public void setDelay(int delay) {
            this.delay = delay;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    //开始场景时候的选择设备
    public static class DeviceBean {

        /**
         * device_id : 1
         * area_id : 7
         * classify_id : 12
         * device_name : 测试调光灯
         * port : 1
         * main_engine_id : 7
         * device_icon : 1
         * type : ET-D0201A
         * search_version : 1
         * show_version : 1
         * is_thirdly : 0
         * address : {254.0.7.1};
         * device_background_img : 1
         * guid : 123456975
         * control_type : dimmerLight
         * panel_number : 0
         * other_guid : null
         * is_use : 1
         * area_name : 测试区域
         * classify_name : 调光
         * json_type : 3
         * val : 00000001
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
        private String device_background_img;
        private int guid;
        private String control_type;
        private int panel_number;
        private Object other_guid;
        private int is_use;
        private String area_name;
        private String classify_name;
        private int json_type;
        private String val;

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

        public int getJson_type() {
            return json_type;
        }

        public void setJson_type(int json_type) {
            this.json_type = json_type;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }
    }

    public static class ScenceBean {
        /**
         * scene_id : 4
         * number : 4
         * scene_name : 定时场景测试
         * area_name : 测试区域
         * click_touch : 1
         * count_device : 8
         * json_type : 2
         * val : 00000001
         */
        private int scene_id;
        private int number;
        private String scene_name;
        private String area_name;
        private int click_touch;
        private int count_device;
        private int json_type;
        private String val;

        public int getScene_id() {
            return scene_id;
        }

        public void setScene_id(int scene_id) {
            this.scene_id = scene_id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getScene_name() {
            return scene_name;
        }

        public void setScene_name(String scene_name) {
            this.scene_name = scene_name;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public int getClick_touch() {
            return click_touch;
        }

        public void setClick_touch(int click_touch) {
            this.click_touch = click_touch;
        }

        public int getCount_device() {
            return count_device;
        }

        public void setCount_device(int count_device) {
            this.count_device = count_device;
        }

        public int getJson_type() {
            return json_type;
        }

        public void setJson_type(int json_type) {
            this.json_type = json_type;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }
    }

}

