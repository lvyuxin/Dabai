package com.suomate.dabaiserver.utils.config;

/**
 * Created by lenovo on 2018/7/2.
 */

public class ContentStr {
    public final static String GUID = "guid";


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

    public final static class Control_type {//SET;1;{254.0.6.1};$0D$0A
        public static final String switchLight = "switchLight";
        public static final String electricDoor = "electricDoor";
        public static final String electromagneticlock = "electromagneticlock";

        public static final String dimmerLight = "dimmerLight";
        public static final String colorDimmerLight = "colorDimmerLight";
        public static final String newWind = "newWind";

        public static final String floorHeating = "floorHeating";
        public static final String curtains = "curtains";//单轨
        public static final String windowCurtains = "windowCurtains";//双轨


        public static final String humanFeeling = "humanFeeling";
        public static final String gas = "gas";
        public static final String smokeFeeling = "smokeFeeling";
        //io 面板
        public static final String panel= "panel";
        //智能面板
        public static final String intelligentPanel = "intelligentPanel";

        public static final String electricityConversion = "electricityConversion";

    }


    public final static class Control_type_name {//SET;1;{254.0.6.1};$0D$0A
        public static final String switchLight = "开光灯";
        public static final String electricDoor = "车库门";
        public static final String electromagneticlock = "电磁锁";

        public static final String dimmerLight = "调光灯";
        public static final String colorDimmerLight = "彩光灯";
        public static final String newWind = "新风";

        public static final String floorHeating = "地暖";
        public static final String curtains = "单轨道窗帘";//单轨
        public static final String windowCurtains = "双轨道窗帘";//双轨

        //io部分
        public static final String humanFeeling = "人感";
        public static final String gas = "燃气报警";
        public static final String smokeFeeling = "烟雾报警";
        //io 面板
        public static final String io_panel1 = "一按键无文字面板";
        public static final String io_panel2 = "二按键无文字面板";
        public static final String io_panel3 = "三按键无文字面板";
        public static final String io_panel4 = "四按键无文字面板";
        public static final String io_panel5 = "五按键无文字面板";
        public static final String io_panel6 = "六按键无文字面板";
        //智能面板
        public static final String intelligent_panel1 = "一按键有文字面板";
        public static final String intelligent_panel2 = "二按键有文字面板";
        public static final String intelligent_panel3 = "三按键有文字面板";
        public static final String intelligent_panel4 = "四按键有文字面板";
        public static final String intelligent_panel5 = "五按键有文字面板";
        public static final String intelligent_panel6 = "六按键有文字面板";

        public static final String electricityConversion = "电量";//电量

    }
}
