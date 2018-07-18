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
        public static final String panel = "panel";
        //智能面板
        public static final String intelligentPanel = "intelligentPanel";
        public static final String electricityConversion = "electricityConversion";
        public static final String airCondition = "airCondition";
        public static final String fictitiousDevice = "fictitiousDevice";
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

    public final static class Symbol {
        public static final String dot = "•";
        public static final String triangle_open = "▼";
        public static final String triangle_close = "▲";
    }
//


    public final static class IconType {
        //1、照明 :ic_shoot_light(射灯)、ic_dimming_light(调光)、
        // ic_lamp_with(灯带)、ic_switch_light、ic_normal_dimming_light(调光)、ic_droplight(吊灯)
        public static final String ic_shoot_light = "ic_shoot_light";
        public static final String ic_dimming_light = "ic_dimming_light";
        public static final String ic_lamp_with = "ic_lamp_with";
        public static final String ic_switch_light = "ic_switch_light";
        public static final String ic_normal_dimming_light = "ic_normal_dimming_light";
        public static final String ic_droplight = "ic_droplight";
        //2、窗帘：ic_normal_curtain、ic_single_curtian,ic_double_curtains
        public static final String ic_normal_curtain = "ic_normal_curtain";
        public static final String ic_single_curtian = "ic_single_curtian";
        public static final String ic_double_curtains = "ic_double_curtains";
        //3、安防 ic_sensor ,ic_gas_alarm(燃气报警)、ic_sensor_normal、ic_smoke_alarm(烟雾报警)
        public static final String ic_sensor = "ic_sensor";
        public static final String ic_gas_alarm = "ic_gas_alarm";
        public static final String ic_sensor_normal = "ic_sensor_normal";
        public static final String ic_smoke_alarm = "ic_smoke_alarm";
        //4、环境 ic_co2,ic_humidity（湿度）,ic_pm25,ic_tempture,ic_voc
        public static final String ic_co2 = "ic_co2";
        public static final String ic_humidity = "ic_humidity";
        public static final String ic_pm25 = "ic_pm25";
        public static final String ic_tempture = "ic_tempture";
        public static final String ic_voc = "ic_voc";
        //5、面板 ic_panel_key1,ic_panel_key2,ic_panel_key3,ic_panel_key4,ic_panel_key5,ic_panel_key6
        public static final String ic_panel_key1 = "ic_panel_key1";
        public static final String ic_panel_key2 = "ic_panel_key2";
        public static final String ic_panel_key3 = "ic_panel_key3";
        public static final String ic_panel_key4 = "ic_panel_key4";
        public static final String ic_panel_key5 = "ic_panel_key5";
        public static final String ic_panel_key6 = "ic_panel_key6";
        //6、地暖 ic_floor_heating
        public static final String ic_floor_heating = "ic_floor_heating";
        //7、新风 ic_fresh_air
        public static final String ic_fresh_air = "ic_fresh_air";
        //8、电量 ic_electricity
        public static final String ic_electricity = "ic_electricity";
        //9、监控 ic_monitor
        public static final String ic_monitor = "ic_monitor";
        //10、空调 ic_air_condition
        public static final String ic_air_condition = "ic_air_condition";

    }
}
