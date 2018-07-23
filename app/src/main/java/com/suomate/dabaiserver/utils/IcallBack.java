package com.suomate.dabaiserver.utils;

import android.graphics.Bitmap;
import org.json.JSONObject;
import java.util.List;

/**
 * Created by fanxi on 2018/7/8.
 */

public interface IcallBack {

    void callBack(List list);

    void callBack(String str);

    void callBack(JSONObject obj);
    void callBack(Object obj);
    void callBack(Bitmap bitmap);

    void confirmHandle();

    void callBack(int position);
    void callBack(int postion,boolean isSelect);
    void callBack(String  id,String name);
    void callBack(boolean boo);

}
