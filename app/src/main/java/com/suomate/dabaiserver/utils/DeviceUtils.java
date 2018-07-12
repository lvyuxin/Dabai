package com.suomate.dabaiserver.utils;

import com.suomate.dabaiserver.utils.config.ContentStr;

/**
 * Created by fanxi on 2018/7/11.
 */

public class DeviceUtils {
    public static String getControlTypeName(String controlType) {
        String name = "";
        if (controlType.equals(ContentStr.Control_type.switchLight)) {
            name = ContentStr.Control_type_name.switchLight;
        } else if (controlType.equals(ContentStr.Control_type.electricDoor)) {
            name = ContentStr.Control_type_name.electricDoor;
        } else if (controlType.equals(ContentStr.Control_type.electromagneticlock)) {
            name = ContentStr.Control_type_name.electromagneticlock;
        } else if (controlType.equals(ContentStr.Control_type.electricDoor)) {
            name = ContentStr.Control_type_name.electromagneticlock;

        } else if (controlType.equals(ContentStr.Control_type.dimmerLight)) {
            name = ContentStr.Control_type_name.dimmerLight;
        } else if (controlType.equals(ContentStr.Control_type.colorDimmerLight)) {
            name = ContentStr.Control_type_name.colorDimmerLight;
        } else if (controlType.equals(ContentStr.Control_type.newWind)) {
            name = ContentStr.Control_type_name.newWind;
        } else if (controlType.equals(ContentStr.Control_type.floorHeating)) {
            name = ContentStr.Control_type_name.floorHeating;
        } else if (controlType.equals(ContentStr.Control_type.floorHeating)) {


        } else if (controlType.equals(ContentStr.Control_type.curtains)) {
            name = ContentStr.Control_type_name.curtains;
        } else if (controlType.equals(ContentStr.Control_type.windowCurtains)) {
            name = ContentStr.Control_type_name.windowCurtains;
            /**
             * io部分
             */
        } else if (controlType.equals(ContentStr.Control_type.humanFeeling)) {
            name = ContentStr.Control_type_name.humanFeeling;
        } else if (controlType.equals(ContentStr.Control_type.gas)) {
            name = ContentStr.Control_type_name.gas;
        } else if (controlType.equals(ContentStr.Control_type.smokeFeeling)) {
            name = ContentStr.Control_type_name.smokeFeeling;
            /**
             * io面板
             */
//        } else if (controlType.equals(ContentStr.Control_type.io_panel1)) {
//
//            name = ContentStr.Control_type_name.panel;
//        } else if (controlType.equals(ContentStr.Control_type.io_panel2)) {
//            name = ContentStr.Control_type_name.io_panel2;
//        } else if (controlType.equals(ContentStr.Control_type.io_panel3)) {
//            name = ContentStr.Control_type_name.io_panel3;
//        } else if (controlType.equals(ContentStr.Control_type.io_panel4)) {
//            name = ContentStr.Control_type_name.io_panel4;
//        } else if (controlType.equals(ContentStr.Control_type.io_panel5)) {
//            name = ContentStr.Control_type_name.io_panel5;
//        } else if (controlType.equals(ContentStr.Control_type.io_panel6)) {
//            name = ContentStr.Control_type_name.io_panel6;

            /**
             * 智能面板

             /**
             * 电量
             */
        } else if (controlType.equals(ContentStr.Control_type.electricityConversion)) {
            name = ContentStr.Control_type_name.electricityConversion;
        }
        return name;
    }


    public static String getControlTypeName(String controlType, int keyumber) {
        String name = null;
        if (controlType.equals(ContentStr.Control_type.panel)) {//普通面板
            switch (keyumber) {
                case 1:
                    name = ContentStr.Control_type_name.io_panel1;
                    break;
                case 2:
                    name = ContentStr.Control_type_name.io_panel2;
                    break;
                case 3:
                    name = ContentStr.Control_type_name.io_panel3;
                    break;
                case 4:
                    name = ContentStr.Control_type_name.io_panel4;
                    break;
                case 5:
                    name = ContentStr.Control_type_name.io_panel5;
                    break;
                case 6:
                    name = ContentStr.Control_type_name.io_panel6;
                    break;
            }

//                  */
//        } else if (controlType.equals(ContentStr.Control_type.intelligent_panel1)) {
//            name = ContentStr.Control_type_name.intelligent_panel1;
//        } else if (controlType.equals(ContentStr.Control_type.intelligent_panel2)) {
//            name = ContentStr.Control_type_name.intelligent_panel2;
//        } else if (controlType.equals(ContentStr.Control_type.intelligent_panel3)) {
//            name = ContentStr.Control_type_name.intelligent_panel3;
//        } else if (controlType.equals(ContentStr.Control_type.intelligent_panel4)) {
//            name = ContentStr.Control_type_name.intelligent_panel4;
//        } else if (controlType.equals(ContentStr.Control_type.intelligent_panel5)) {
//            name = ContentStr.Control_type_name.intelligent_panel5;
//        }else if (controlType.equals(ContentStr.Control_type.intelligent_panel6)) {
//            name = ContentStr.Control_type_name.intelligent_panel6;
//
        } else if (controlType.equals(ContentStr.Control_type.intelligentPanel)) {//智能面板
            switch (keyumber) {
                case 1:
                    name = ContentStr.Control_type_name.intelligent_panel1;
                    break;
                case 2:
                    name = ContentStr.Control_type_name.intelligent_panel2;
                    break;
                case 3:
                    name = ContentStr.Control_type_name.intelligent_panel3;
                    break;
                case 4:
                    name = ContentStr.Control_type_name.intelligent_panel4;
                    break;
                case 5:
                    name = ContentStr.Control_type_name.intelligent_panel5;
                    break;
                case 6:
                    name = ContentStr.Control_type_name.intelligent_panel6;
                    break;
            }
        }

        return null;
    }

    public static String  getDeviceType(String main_engine_id,String serial) {
        String type=null;
        if (main_engine_id.length() == 1) {
            type="ID00" + main_engine_id + "-D-" + serial;
        } else if (main_engine_id.length() == 2) {
            type="ID0" + main_engine_id + "-D-" + serial;
        } else if (main_engine_id.length() == 3) {
            type="ID" + main_engine_id + "-D-" + serial;
        }
        return type;
    }

}
