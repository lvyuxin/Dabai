package com.suomate.dabaiserver.utils.config;

/**
 * Created by lenovo on 2018/6/20.
 */

public class Content {
    public static final int SERVER_WRONG = -7102;
    public static final int REQUEST_BODY_NULL = -2017;
    public static final int NOT_JSON_DATA = -4515;
    public static final int REQUEST_SCUCESS = 10200;
    public static final int REQUEST_FAILD = 10400;
    public static final int PARAMETER_INCOMPLETE = 10401;//in existence
    public static final int INEXISTENCE=10403;//不存在
    public static final int EXSISTED = 10404;
    public static final int PARSE_WRONG = -1545;
    //网络请求的几种方式
    public static final int ENTITY_TYPE = 1;
    public static final int LIST_TYPE = 2;
    public static final int STRING_TYPE = 3;

    public final static class SERIAL {
        public final static String SWITCH8 = "SMT-JD0816A";
        public final static String SWITCH8_OLD = "ET-R0816A";

        public final static String SWITCH4 = "SMT-JD0416A";
        public final static String SWITCH4_OLD = "ET-R0416A";
        //4路弱电开关执行模块 窗帘
        public final static String SWITCH485_CURTAIN = "SMT-CLNET";
        //窗帘模块
        public final static String SWITCH4_CURTAIN = "SMT-KG04L";
        public final static String SWITCH4_CURTAIN_OLD = "ET-CR04A";

        //调光执行模块
        public final static String DIMMING4 = "SMT-JD0416T4L";
        public final static String DIMMING4_OLD = "ET-R0416D4I";
        public final static String DIMMING2 = "SMT-TG0201A";
        public final static String DIMMING2_OLD = "ET-D0201A";
        //彩灯控制模块
        public final static String COLORFUL_LIGHT = "SMT-DMX08";
        public final static String COLORFUL_LIGHT_OLD = "ET-DMX08A";
        //扩展模块
        public final static String EXTENDED_XINFEN = "SMT-XFZH";
        public final static String EXTENDED_DINUAN = "SMT-DNZH";
        //电量
        public final static String ELECTRICITY = "SMT-DL";
        public static final String ELECTRICITY_OLD="SP01A";
        //面板
        public final static String PANEL = "SMT-16MBXY";
        public final static String PANEL_OLD = "KPS128A";
        //IO模块
        public final static String IO = "SMT-IO0606";
        public final static String IO_OLD = "MI0606A";
        //视屏监控
        public final static String MONITOR = "SMT-JK1.0";
    }
    public final static class CONTROL_TYPE {
        public final static String  SWITCH = "switchLight";
        //4路弱电开关执行模块 窗帘
//        public final static String  = "SMT-CLNET";
        //窗帘模块
        public final static String SWITCH4_CURTAIN = "SMT-KG04L";
        public final static String SWITCH4_CURTAIN_OLD = "ET-CR04A";

        //调光执行模块
        public final static String DIMMING = "dimmerLight";

        //彩灯控制模块
        public final static String COLORFUL_LIGHT = "SMT-DMX08";
        public final static String COLORFUL_LIGHT_OLD = "ET-DMX08A";
        //扩展模块
        public final static String EXTENDED_XINFEN = "SMT-XFZH";
        public final static String EXTENDED_DINUAN = "SMT-DNZH";
        //电量
        public final static String ELECTRICITY = "SMT-DL";
        public static final String ELECTRICITY_OLD="SP01A";
        //面板
        public final static String INTELLIGENT_PANEL = "intelligentPanel";
        //IO模块
        public final static String IO_PANEL = "panel";


        //视屏监控
        public final static String MONITOR = "SMT-JK1.0";
    }
    public final static class DEVICETYPE {
        public final static int SWITCH = 1;
        public final static int DIMMING = 2;
        public final static int PANEL = 3;
        public final static int COLOREDLIGHT = 4;//colouredLightsList
        public final static int CURTAIN = 5;
        public final static int EXTENDED = 6;
        public final static int IO = 7;
    }

    public final static class TYPE {
        public final static int AREA = 1;
        public final static int CLASSIFY = 2;
        public final static int NOMINATE = 3;
        public final static int TYPE_AREA = 1;
        public final static int TYPE_HUMAN_FEELING= 1;
        public final static int TYPE_GAS= 2;
        public final static int TYPE_SMOKE= 3;
        public final static int TYPE_IO_PANEL= 4;
        public final static int TYPE_INTELLIGENT_PANEL= 5;
        //启动方式
        public final static int TYPE_INSTANT= 1;
        public final static int TYPE_TIME= 2;
        public final static int TYPE_LINK= 3;
        //联动设备
        public final static int TRIGGER= 17;
        public final static int NOT_TRIGGER= 18;
        public final static int LONG_PRESS= 19;
        //jsontype
        public final static int Scence= 1;
        public final static int TimeScence= 2;
        public final static int Device= 3;


//        public final static int CLASSIFY = 2;
//        public final static int NOMINATE = 3;


    }
    public final static class JSON_TYPE {
        public final static int SCENCE = 1;
        public final static int SCENCE_TIME = 2;
        public final static int DEVICE = 3;


    }
}
