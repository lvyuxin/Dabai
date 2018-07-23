package com.suomate.dabaiserver.bean;

import java.util.List;

/**
 * Created by fanxi on 2018/7/16.
 */

public class ScenceStartTaskBean {

    /**
     * classify_id : 7
     * classify_name : 地暖
     * count : 1
     * deviceOrSceneInfo : [{"device_or_scene_id":6,"port":1,"main_engine_id":"56","device_or_scene_name":"测试地暖1","area_name":"测试区域","type":"SMT-DNZH","control_type":"floorHeating","panel_number":0,"is_fictitious":0,"json_type":3,"val":"00000001","count_device":0,"is_use":false}]
     */

    private int classify_id;
    private String classify_name;
    private int count;
    private List<DeviceOrSceneInfoBean> deviceOrSceneInfo;
    //自定义的属性
    private String percent;

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DeviceOrSceneInfoBean> getDeviceOrSceneInfo() {
        return deviceOrSceneInfo;
    }

    public void setDeviceOrSceneInfo(List<DeviceOrSceneInfoBean> deviceOrSceneInfo) {
        this.deviceOrSceneInfo = deviceOrSceneInfo;
    }

    public static class DeviceOrSceneInfoBean {
        /**
         * device_or_scene_id : 6
         * port : 1
         * main_engine_id : 56
         * device_or_scene_name : 测试地暖1
         * area_name : 测试区域
         * type : SMT-DNZH
         * control_type : floorHeating
         * panel_number : 0
         * is_fictitious : 0
         * json_type : 3
         * val : 00000001
         * count_device : 0
         * is_use : false
         */

        private int device_or_scene_id;
        private int port;
        private String main_engine_id;
        private String device_or_scene_name;
        private String area_name;
        private String type;
        private String control_type;
        private int panel_number;
        private int is_fictitious;
        private int json_type;
        private String val;
        private int count_device;
        private boolean is_use;
        //自定义是否选中
        private boolean isSelect;
        private String name;
        private String detail="";
        private int delay;
        private String address;

//        public DeviceOrSceneInfoBean(int device_or_scene_id, int port, String control_type, int json_type, String name, String detail) {
//            this.device_or_scene_id = device_or_scene_id;
//            this.port = port;
//            this.control_type = control_type;
//            this.json_type = json_type;
//            this.name = name;
//            this.detail = detail;
//        }
//        public DeviceOrSceneInfoBean(int device_or_scene_id, String control_type, int json_type, String name) {
//            this.device_or_scene_id = device_or_scene_id;
//            this.control_type = control_type;
//            this.json_type = json_type;
//            this.name = name;
//        }

        public DeviceOrSceneInfoBean(int device_or_scene_id, String control_type, int json_type, String name, String detail,int port,String address) {
            this.device_or_scene_id = device_or_scene_id;
            this.control_type = control_type;
            this.json_type = json_type;
            this.name = name;
            this.detail = detail;
            this.port=port;
            this.address=address;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getDelay() {
            return delay;
        }

        public void setDelay(int delay) {
            this.delay = delay;
        }

        public DeviceOrSceneInfoBean() {
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getDevice_or_scene_id() {
            return device_or_scene_id;
        }

        public void setDevice_or_scene_id(int device_or_scene_id) {
            this.device_or_scene_id = device_or_scene_id;
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

        public String getDevice_or_scene_name() {
            return device_or_scene_name;
        }

        public void setDevice_or_scene_name(String device_or_scene_name) {
            this.device_or_scene_name = device_or_scene_name;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public int getIs_fictitious() {
            return is_fictitious;
        }

        public void setIs_fictitious(int is_fictitious) {
            this.is_fictitious = is_fictitious;
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

        public int getCount_device() {
            return count_device;
        }

        public void setCount_device(int count_device) {
            this.count_device = count_device;
        }

        public boolean isIs_use() {
            return is_use;
        }

        public void setIs_use(boolean is_use) {
            this.is_use = is_use;
        }
    }
}
