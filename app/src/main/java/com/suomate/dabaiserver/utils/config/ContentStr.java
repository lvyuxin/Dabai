package com.suomate.dabaiserver.utils.config;

/**
 * Created by lenovo on 2018/7/2.
 */

public class ContentStr {
    public final static class TCP_BroadCast {
        public final static String TCP_OPEN_STATE = "tcp_open_state";
        public final static String TCP_OPEN_AIR_CONDITION = "tcp_open_air_condition";
        public final static String TCP_AIR_CONDITION_STATE = "tcp_air_condition_state";
        public final static String UPDATE_CHANGE_IMAGE = "update_change_image";//修改区域图片
        public final static String UPDATE_DEVICE_IMAGE = "update_device_image";
    }
    public final static class Control_Device {//SET;1;{254.0.6.1};$0D$0A
        public static final String OPEN_LIGHT = "SET;00000001;";//打开开关灯
        public static final String CLOSE_LIGHT = "SET;00000000;";//关闭开关灯
        public static final String OPEN_DIMMER = "SET;10000060;";//打开调光灯
        public static final String CLOSE_DIMMER = "SET;10000000;";//关闭调光灯
        public static final String OPEN_FRESH_AIR = "SET;1;";//打开新风
        public static final String CLOSE_FRESH_AIR = "SET;2;";//关闭新风

    }
}
