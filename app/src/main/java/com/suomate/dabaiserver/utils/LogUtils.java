package com.suomate.dabaiserver.utils;
import android.util.Log;

/**
 * Created by 94553 on 2017/6/6.
 */

public class LogUtils {
    private LogUtils()
    {
		/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    private static final boolean IS_DEBUG = true;
    private static final String TAG = "smart";
    /**
     * 打印全部
     */
    private static int LOG_MAXLENGTH = 2000;
    public static void logPrintAll(String msg) {
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAXLENGTH;
        for (int i = 0; i < 100; i++) {
            if (strLength > end) {
                Log.e("part_" + i, msg.substring(start, end));
                start = end;
                end = end + LOG_MAXLENGTH;
            } else {
                Log.e("part_" + i, msg.substring(start, strLength));
                break;
            }
        }
    }

    // 下面四个是默认tag的函数
    public static void i(String msg)
    {
        if (IS_DEBUG)
            Log.i(TAG, msg);
    }

    public static void d(String msg)
    {
        if (IS_DEBUG)
            Log.d(TAG, msg);
    }

    public static void e(String msg)
    {
        if (IS_DEBUG)
            Log.e(TAG, msg);
    }

    public static void v(String msg)
    {
        if (IS_DEBUG)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg)
    {
        if (IS_DEBUG)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg)
    {
        if (IS_DEBUG)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg)
    {
        if (IS_DEBUG)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg)
    {
        if (IS_DEBUG)
            Log.v(tag, msg);
    }
}
