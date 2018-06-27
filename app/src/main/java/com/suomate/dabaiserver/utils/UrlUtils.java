package com.suomate.dabaiserver.utils;

/**
 * 所有的url类
 * Created by lenovo on 2018/6/20.
 * http://101.201.50.1:808/readJson
 * http://101.201.50.1:808/a_new_dabai/public/index.php/classify/add?guid=123456975&name="dd"
 */

public class UrlUtils {
    public static final String BASEWEBSITE = "http://101.201.50.1:808";
    public static final String BASEWEBSITE1 = "http://101.201.50.1:8080";
    public static final String GET_READJSON = BASEWEBSITE1 + "/readJson";
    public static final String getDeviceList = BASEWEBSITE + "/a_new_dabai/public/index.php/device/allInfo";
    public static final String AREA_LIST = BASEWEBSITE + "/a_new_dabai/public/index.php/area/info";
    public static final String CLASSIFY_LIST = BASEWEBSITE + "/a_new_dabai/public/index.php/classify/info";
    public static final String CUSTOM_AREA_NAME = BASEWEBSITE + "/a_new_dabai/public/index.php/area/add";
    public static final String CUSTOM_CLASSIFY_NAME = BASEWEBSITE + "/a_new_dabai/public/index.php/classify/add";
    public static final String DELETE_CLASSIFY_ITEM = BASEWEBSITE + "/a_new_dabai/public/index.php/classify/delete";
    public static final String DELETE_AREA_ITEM = BASEWEBSITE + "/a_new_dabai/public/index.php/area/delete";

}
