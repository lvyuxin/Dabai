package com.suomate.dabaiserver.bean;

import java.util.List;

/**
 * Created by fanxi on 2018/7/12.
 */

public class IoBean {

    /**
     * classify_id : 10
     * classify_name : 人感
     * count : 3
     * deviceOrSceneInfo : [{"device_or_scene_id":109,"port":1,"main_engine_id":"62","device_or_scene_name":"人感1","area_name":"测试区域","type":"MI0606A","control_type":"humanFeeling","panel_number":0,"json_type":3,"val":"00000001"},{"device_or_scene_id":117,"port":2,"main_engine_id":"62","device_or_scene_name":"人感2","area_name":"测试一下","type":"MI0606A","control_type":"humanFeeling","panel_number":0,"json_type":3,"val":"00000001"},{"device_or_scene_id":125,"port":3,"main_engine_id":"62","device_or_scene_name":"安卓人感","area_name":"美女","type":"MI0606A","control_type":"humanFeeling","panel_number":0,"json_type":3,"val":"00000001"}]
     */

    private int classify_id;
    private String classify_name;
    private int count;
    private List<DeviceOrSceneInfoBean> deviceOrSceneInfo;

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
         * device_or_scene_id : 109
         * port : 1
         * main_engine_id : 62
         * device_or_scene_name : 人感1
         * area_name : 测试区域
         * type : MI0606A
         * control_type : humanFeeling
         * panel_number : 0
         * json_type : 3
         * val : 00000001
         */

        private int device_or_scene_id;
        private int port;
        private String main_engine_id;
        private String device_or_scene_name;
        private String area_name;
        private String type;
        private String control_type;
        private int panel_number;
        private int json_type;
        private String val;
        //自定义
        private int iotype=17;//17为按下 18 19
        private boolean isSelect;

        public int getIotype() {
            return iotype;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public void setIotype(int iotype) {
            this.iotype = iotype;
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
