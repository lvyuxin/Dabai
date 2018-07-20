package com.suomate.dabaiserver.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import com.suomate.dabaiserver.R;
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

    /**
     * 根据type设置icon
     * @param ivDeviceIcon
     * @param icon_type
     */
    public static void setIcon(ImageView ivDeviceIcon, String icon_type) {

        if (TextUtils.isEmpty(icon_type)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_moren);
            return;
        }
        //1、照明 :ic_shoot_light(射灯)、ic_dimming_light(调光)、
        // ic_lamp_with(灯带)、ic_switch_light、ic_normal_dimming_light(调光)、ic_droplight(吊灯)
        if (icon_type.equals(ContentStr.IconType.ic_shoot_light)) {//射灯
            ivDeviceIcon.setImageResource(R.mipmap.icon_shedeng);
        } else if (icon_type.equals(ContentStr.IconType.ic_dimming_light)) {//调光
            ivDeviceIcon.setImageResource(R.mipmap.icon_tiaoguang);
        } else if (icon_type.equals(ContentStr.IconType.ic_lamp_with)) {//灯带
            ivDeviceIcon.setImageResource(R.mipmap.icon_dengdai);
        } else if (icon_type.equals(ContentStr.IconType.ic_switch_light)) {//普通灯
            ivDeviceIcon.setImageResource(R.mipmap.icon_normal_light);
        } else if (icon_type.equals(ContentStr.IconType.ic_normal_dimming_light)) {//调光
            ivDeviceIcon.setImageResource(R.mipmap.icon_caideng);
        } else if (icon_type.equals(ContentStr.IconType.ic_droplight)) {//调光
            ivDeviceIcon.setImageResource(R.mipmap.icon_diaodeng);
            //2、窗帘：ic_normal_curtain、ic_single_curtian,ic_double_curtains
        } else if (icon_type.equals(ContentStr.IconType.ic_normal_curtain)) {//
            ivDeviceIcon.setImageResource(R.mipmap.icon_chuanglian);
        } else if (icon_type.equals(ContentStr.IconType.ic_double_curtains)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_double_chuanglian);
        } else if (icon_type.equals(ContentStr.IconType.ic_single_curtian)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_single_chuagnlian);
            //3、安防 ic_sensor ,ic_gas_alarm(燃气报警)、ic_sensor_normal、ic_smoke_alarm(烟雾报警)
        }else if (icon_type.equals(ContentStr.IconType.ic_sensor)) {//
            ivDeviceIcon.setImageResource(R.mipmap.icon_sensor);
        } else if (icon_type.equals(ContentStr.IconType.ic_gas_alarm)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_gas);
        } else if (icon_type.equals(ContentStr.IconType.ic_sensor_normal)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_sensor2);
        } else if (icon_type.equals(ContentStr.IconType.ic_smoke_alarm)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_smoke_alarm);
            //4、环境 ic_co2,ic_humidity（湿度）,ic_pm25,ic_tempture,ic_voc
        }else if (icon_type.equals(ContentStr.IconType.ic_co2)) {//
            ivDeviceIcon.setImageResource(R.mipmap.icon_co2);
        } else if (icon_type.equals(ContentStr.IconType.ic_humidity)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_humidity);
        } else if (icon_type.equals(ContentStr.IconType.ic_pm25)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_pm25);
        } else if (icon_type.equals(ContentStr.IconType.ic_tempture)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_tempture);
        }else if(icon_type.equals(ContentStr.IconType.ic_voc)){
            ivDeviceIcon.setImageResource(R.mipmap.icon_voc);
            //5、面板 ic_panel_key1,ic_panel_key2,ic_panel_key3,ic_panel_key4,ic_panel_key5,ic_panel_key6

        }else if (icon_type.equals(ContentStr.IconType.ic_panel_key1)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_panel1);
        } else if (icon_type.equals(ContentStr.IconType.ic_panel_key2)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_panel2);
        } else if (icon_type.equals(ContentStr.IconType.ic_panel_key3)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_panel3);
        } else if (icon_type.equals(ContentStr.IconType.ic_panel_key4)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_panel4);
        }else if(icon_type.equals(ContentStr.IconType.ic_panel_key5)){
            ivDeviceIcon.setImageResource(R.mipmap.icon_panel5);
        }else if(icon_type.equals(ContentStr.IconType.ic_panel_key6)){
            ivDeviceIcon.setImageResource(R.mipmap.icon_panel6);

            //6、地暖 ic_floor_heating
        }else if (icon_type.equals(ContentStr.IconType.ic_floor_heating)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_ground_wram);
            //7、新风 ic_fresh_air
        } else if (icon_type.equals(ContentStr.IconType.ic_fresh_air)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_fresh_air);
            //8、电量 ic_electricity
        } else if (icon_type.equals(ContentStr.IconType.ic_electricity)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_electricity);
            //9、监控 ic_monitor
        } else if (icon_type.equals(ContentStr.IconType.ic_monitor)) {
            ivDeviceIcon.setImageResource(R.mipmap.icon_monitor);
            //10、空调 ic_air_condition
        }else if(icon_type.equals(ContentStr.IconType.ic_air_condition)){
            ivDeviceIcon.setImageResource(R.mipmap.icon_air_condition);
        }else {

        }
    }

    public static String  getWeek( String strWeek) {
        String[] strings = strWeek.split(",");
        String week="";
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("1")) {
                week = week + "周一 ";
            } else if (strings[i].equals("2")) {
                week = week + "周二 ";
            } else if (strings[i].equals("3")) {
                week = week + "周三 ";
            } else if (strings[i].equals("4")) {
                week = week + "周四 ";
            } else if (strings[i].equals("5")) {
                week = week + "周五 ";
            } else if (strings[i].equals("6")) {
                week = week + "周六 ";
            } else if (strings[i].equals("7")) {
                week = week + "周日 ";
            }
        }
        return week;
    }


    public static String  getTime( String   time) {

        if (time.length()==1) {
            time="0"+time;
        }
        return time;

    }
}
