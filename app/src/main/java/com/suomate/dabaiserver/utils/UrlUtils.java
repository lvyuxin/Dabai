package com.suomate.dabaiserver.utils;

/**
 * 所有的url类
 * Created by lenovo on 2018/6/20.
 * http://101.201.50.1:808/a_new_dabai/public/index.php/device/update
 */

public class UrlUtils {
    public static final String BASEWEBSITE = "http://101.201.50.1:808";
    public static final String BASEWEBSITE1 = "http://101.201.50.1:8080";
    //添加设备部分
    public static final String GET_READJSON = BASEWEBSITE1 + "/readJson";
    public static final String getDeviceList = BASEWEBSITE + "/a_new_dabai/public/index.php/device/allInfo";
    public static final String AREA_LIST = BASEWEBSITE + "/a_new_dabai/public/index.php/area/info";
    public static final String CLASSIFY_LIST = BASEWEBSITE + "/a_new_dabai/public/index.php/classify/info";
    public static final String CUSTOM_AREA_NAME = BASEWEBSITE + "/a_new_dabai/public/index.php/area/add";
    public static final String CUSTOM_CLASSIFY_NAME = BASEWEBSITE + "/a_new_dabai/public/index.php/classify/add";
    public static final String DELETE_CLASSIFY_ITEM = BASEWEBSITE + "/a_new_dabai/public/index.php/classify/delete";
    public static final String DELETE_AREA_ITEM = BASEWEBSITE + "/a_new_dabai/public/index.php/area/delete";
    public static final String DEVICE_ADD = BASEWEBSITE + "/a_new_dabai/public/index.php/device/add";

    public static final String DEVICE_UPDATE = BASEWEBSITE + "/a_new_dabai/public/index.php/device/update";
    public static final String CHECK_DEVICE_PORT = BASEWEBSITE + "/a_new_dabai/public/index.php/device/isHasDevice";
    public static final String getDeciceAccessId = BASEWEBSITE + "/a_new_dabai/public/index.php/device/getDeciceAccessId";
    //区域部分
    public static final String AREA_DEVICE = BASEWEBSITE + "/a_new_dabai/public/index.php/device/getDeciceAccessArea";
    public static final String AREA_DEVICE_DELETE = BASEWEBSITE + "/a_new_dabai/public/index.php/device/delete";
    //控制开关的接口
    public static final String SEND_ORDER = BASEWEBSITE1 + "/se.....ndMsg";
    //功能部分
    public static final String FUNCTION_DEVICE_LIST = BASEWEBSITE + "/a_new_dabai/public/index.php/device/getDeciceAccessClassify";
    //一键添加场景功能
    public static final String ADD_SCENCE = BASEWEBSITE + "/a_new_dabai/public/index.php/scene/addS";
    public static final String SCENCE_LIST = BASEWEBSITE + "/a_new_dabai/public/index.php/sceneInfo/info";
    public static final String SCENCE_TIME_LIST = BASEWEBSITE + "/a_new_dabai/public/index.php/sceneInfo/timerInfo";
    public static final String DELETE_SCENCE = BASEWEBSITE + "/a_new_dabai/public/index.php/scene/delete";
    public static final String ADD_TIME_SCENCE = BASEWEBSITE + "/a_new_dabai/public/index.php/timer/addT";
    public static final String addLinkage = BASEWEBSITE + "/a_new_dabai/public/index.php/linkage/addLinkage";
    public static final String SCENCE_DETAIL = BASEWEBSITE + "/a_new_dabai/public/index.php/sceneInfo/getSceneInfo";
    public static final String getIoDevice = BASEWEBSITE + "/a_new_dabai/public/index.php/linkage/getIoDevice";
    public static final String getSceneDeviceAccessClassify = BASEWEBSITE + "/a_new_dabai/public/index.php/sceneInfo/getSceneDeviceAccessClassify";
    //面板
    //单个面板的按键信息
    public static final String getPanelNumberInfo = BASEWEBSITE + "/a_new_dabai/public/index.php/device/getPanelNumberInfo";
    //功能一级//http://101.201.50.1:808/a_new_dabai/public/index.php/home/clasifyInfo?guid=123456975
//    http://101.201.50.1:808/a_new_dabai/public/index.php/main/bindEmail
    public static final String bindEmail = BASEWEBSITE + "/a_new_dabai/public/index.php/main/bindEmail";
    //绑定邮箱
    public static final String CLASSIFY_DEVICE_LIST = BASEWEBSITE + "/a_new_dabai/public/index.php/home/clasifyInfo";

    //    public static final String

}
