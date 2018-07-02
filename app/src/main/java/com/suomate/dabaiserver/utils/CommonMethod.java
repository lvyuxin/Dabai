package com.suomate.dabaiserver.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 94553 on 2017/7/24.
 */

public class CommonMethod {


    /**
     * 获取系统当前时间格式(yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurDateTime() {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(now);
    }




    /**
     * 截取tag的id位数，然后由16进制转化为10进制
     *
     * @param tag
     * @return
     */
    public static String getSubstr(String tag, int begin, int end) {
        String s1="";
        try {
            String substring1 = tag.substring(begin, end);
            s1 = String.valueOf(Integer.valueOf(substring1, 16)) + "";//十六进制转化为10进制
            return s1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return s1;

    }

    /**
     * 十进制转十六进制
     *
     * @param value 十进制数字
     * @return
     */
    public static String getHexStringValue(int value) {
        String hexValue = Integer.toHexString(value);
        if (hexValue.length() == 1) {
            hexValue = "0" + hexValue;
        }
        return hexValue;
    }


    /**
     * 时间戳转时间格式
     *
     * @param value yyyy-MM-dd HH:mm
     * @return
     */
    public static String TimeToDate(int value) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        long tempValue = Long.valueOf(String.valueOf(value));
        return sdf.format(new Date(tempValue * 1000));
    }
    /**
     * 把时间字符串转化为时间的标准格式
     * @param time
     * @return
     */
    public static String timeshourmin(String time) {
        Long time1 = Long.valueOf(time);
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String times = sdr.format(new Date(time1*1000L));
        return times;
    }





    public static Bitmap setUrlToBitmap(String imageurl) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(imageurl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 将网络图片转化为bitmap的格式
     *
     * @param url
     * @return
     */
    public static Bitmap getBitmap(final String url) {
        final Bitmap[] bitmap = {null};

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap[0] = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap[0];
    }




    /**
     * 子字符串modelStr在字符串str中第count次出现时的下标
     * @param str
     * @param modelStr
     * @param count
     * @return
     */
    public static  int getFromIndex(String str, String modelStr, Integer count) {
        //对子字符串进行匹配
        Matcher slashMatcher = Pattern.compile(modelStr).matcher(str);
        int index = 0;
        //matcher.find();尝试查找与该模式匹配的输入序列的下一个子序列
        while(slashMatcher.find()) {
            index++;
            //当modelStr字符第count次出现的位置
            if(index == count){
                break;
            }
        }
        //matcher.start();返回以前匹配的初始索引。
        return slashMatcher.start();
    }

}
