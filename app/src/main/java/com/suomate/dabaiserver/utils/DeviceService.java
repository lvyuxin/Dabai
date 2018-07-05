package com.suomate.dabaiserver.utils;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.suomate.dabaiserver.utils.config.AppConfig;
import com.suomate.dabaiserver.utils.config.ContentStr;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by 94553 on 2017/11/22.
 */

public class DeviceService extends Service {
    private Socket mClientSocket;
    private DeviceThread mDeviceThread;
    private DataOutputStream mDataOutputStream;
    private DeviceReceiver mDeviceReceiver;
    @Override
    public void onCreate() {
        super.onCreate();
        mDeviceThread = new DeviceThread();
        mDeviceThread.start();
        if (mDeviceReceiver == null) {
            mDeviceReceiver = new DeviceReceiver();
            IntentFilter intentFilter = new IntentFilter(ContentStr.TCP_BroadCast.TCP_OPEN_AIR_CONDITION);
            registerReceiver(mDeviceReceiver, intentFilter);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class DeviceThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                // 客户端 Socket 可以通过指定 IP 地址或域名两种方式来连接服务器端,实际最终都是通过 IP 地址来连接服务器
                // 新建一个Socket，指定其IP地址及端口号
                mClientSocket = new Socket("101.201.50.1", 8000);
                //mClientSocket = new Socket("192.168.1.221", 8000);
                // 客户端socket在接收数据时，有两种超时：1. 连接服务器超时，即连接超时；2. 连接服务器成功后，接收服务器数据超时，即接收超时
                // 设置 socket 读取数据流的超时时间
                mClientSocket.setSoTimeout(5000);
                // 发送数据包，默认为 false，即客户端发送数据采用 Nagle 算法；
                // 但是对于实时交互性高的程序，建议其改为 true，即关闭 Nagle 算法，客户端每发送一次数据，无论数据包大小都会将这些数据发送出去
                mClientSocket.setTcpNoDelay(true);
                // 设置客户端 socket 关闭时，close() 方法起作用时延迟 30 秒关闭，如果 30 秒内尽量将未发送的数据包发送出去
                mClientSocket.setSoLinger(true, 30);
                // 设置输出流的发送缓冲区大小，默认是4KB，即4096字节
                mClientSocket.setSendBufferSize(4096);
                // 设置输入流的接收缓冲区大小，默认是4KB，即4096字节
                mClientSocket.setReceiveBufferSize(4096);
                // 作用：每隔一段时间检查服务器是否处于活动状态，如果服务器端长时间没响应，自动关闭客户端socket
                // 防止服务器端无效时，客户端长时间处于连接状态
                mClientSocket.setKeepAlive(true);
                byte[] guidByte = getGuidByte();
                //PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mClientSocket.getOutputStream())), true);
                mDataOutputStream = new DataOutputStream(mClientSocket.getOutputStream());
                mDataOutputStream.write(guidByte);
            }catch (Exception e){
                mClientSocket=null;
                e.printStackTrace();
            }finally {
                getData();

            }
        }
    }

    /*public static byte[] byte2hex(byte[] b) {
        int length = b.length;
        byte[] b2 = new byte[length << 1];
        int pos;
        for(int i=0; i<length; i++) {
            pos = 2*i;
            b2[pos] = b[(b[i] & 0xf0) >> 4];
            b2[pos+1] = b[b[i] & 0x0f];
        }
        return b2;
    }*/

    private void getData() {
        /* * * * * * * * * * Socket 客户端读取服务器端响应数据 * * * * * * * * * */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // serverSocket.isConnected 代表是否连接成功过
                    // 判断 Socket 是否处于连接状态
                    while (true){
                        if(mClientSocket.isConnected()) {
                            InputStream isRead = mClientSocket.getInputStream();
                            // 缓冲区
                            byte[] buffer = new byte[isRead.available()];
                            // 读取缓冲区
                            isRead.read(buffer);
                            // 转换为字符串
                            if (buffer.length > 0) {
                                String responseInfo = bytes2hex03(buffer);
                                LogUtils.e("fancyresponseInfo:"+responseInfo);
                                String ipUrl = responseInfo.substring(12, 16);
                                if (ipUrl.equals("03f7")) {//1015协议，代表除空调，空气盒子外的所有设备  //03f7
                                    String content = new String(buffer);
                                    LogUtils.e("fancycontent"+content);
                                    content = content.substring(content.length() - 17);
                                    String[] tag = content.split("=");
                                    Intent intent = new Intent(ContentStr.TCP_BroadCast.TCP_OPEN_STATE);
                                    intent.putExtra("tag", tag[0]);
                                    intent.putExtra("state", tag[1]);
                                    sendBroadcast(intent);
                                }else {//1019协议
                                    String content = new String(buffer);
                                    LogUtils.logPrintAll("空调开关content:" + content);
//                                    if (content.contains("air_conditioner")) {
//                                        int endLength = content.indexOf("}");
//                                        content = content.substring(content.indexOf("{\"air_conditioner"), endLength + 4);
//                                        LogUtils.e("fancycy空调：" + content.toString());
//                                        LogUtils.e("空调长度：" + content.length());
//                                        Intent intent = new Intent(C.TCP_BroadCast.TCP_AIR_CONDITION_STATE);
//                                        intent.putExtra("air_condition", content);
//                                        sendBroadcast(intent);
//                                        if (content.length() < 1000) {
//
//                                        }
//                                    }
                                    LogUtils.e("空调开关contentboolean:"+content.contains("air_conditioner"));
                                    if(content.contains("air_conditioner")){
                                        int startIndex = content.indexOf("air_conditioner");
                                        String useJson = content.substring(startIndex-2,content.indexOf("}]}}")+4);
                                        LogUtils.e("空调开关contentuseJson=",useJson.replaceFirst(":",""));
                                        Intent intent = new Intent(ContentStr.TCP_BroadCast.TCP_AIR_CONDITION_STATE);
                                        intent.putExtra("air_condition", useJson);
                                        sendBroadcast(intent);
                                    }
                                    LogUtils.logPrintAll("空调开关:" + content);
                                }
                                // 日志中输出
                            }
                        }
                    }
                    // 关闭网络
                    //mClientSocket.close();
                }
                catch (Exception e) {
                    mClientSocket=null;
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static String bytes2hex03(byte[] bytes)
    {
        final String HEX = "0123456789abcdef";
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes)
        {
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt((b >> 4) & 0x0f));
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt(b & 0x0f));
        }

        return sb.toString();
    }

    private byte[] getNewBuffer(byte[] buffer, int i, int i1) {
        byte[] bs = new byte[i1];
        System.arraycopy(buffer, i, bs, 0, i1);
        return bs;
    }

    public static String InputStreamTOString(InputStream in) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while((count = in.read(data,0,4096)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return new String(outStream.toByteArray(),"ISO-8859-1");
    }

    private class DeviceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ContentStr.TCP_BroadCast.TCP_OPEN_AIR_CONDITION)) {
                if (mClientSocket == null || !mClientSocket.isConnected()) {
                    try {
                        // 客户端 Socket 可以通过指定 IP 地址或域名两种方式来连接服务器端,实际最终都是通过 IP 地址来连接服务器
                        // 新建一个Socket，指定其IP地址及端口号
                        mClientSocket = new Socket("101.201.50.1", 8000);
                        //mClientSocket = new Socket("192.168.1.221", 8000);
                        // 客户端socket在接收数据时，有两种超时：1. 连接服务器超时，即连接超时；2. 连接服务器成功后，接收服务器数据超时，即接收超时
                        // 设置 socket 读取数据流的超时时间
                        mClientSocket.setSoTimeout(5000);
                        // 发送数据包，默认为 false，即客户端发送数据采用 Nagle 算法；
                        // 但是对于实时交互性高的程序，建议其改为 true，即关闭 Nagle 算法，客户端每发送一次数据，无论数据包大小都会将这些数据发送出去
                        mClientSocket.setTcpNoDelay(true);
                        // 设置客户端 socket 关闭时，close() 方法起作用时延迟 30 秒关闭，如果 30 秒内尽量将未发送的数据包发送出去
                        mClientSocket.setSoLinger(true, 30);
                        // 设置输出流的发送缓冲区大小，默认是4KB，即4096字节
                        mClientSocket.setSendBufferSize(4096);
                        // 设置输入流的接收缓冲区大小，默认是4KB，即4096字节
                        mClientSocket.setReceiveBufferSize(4096);
                        // 作用：每隔一段时间检查服务器是否处于活动状态，如果服务器端长时间没响应，自动关闭客户端socket
                        // 防止服务器端无效时，客户端长时间处于连接状态
                        mClientSocket.setKeepAlive(true);
                        byte[] guidByte = getGuidByte();
                        //PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(mClientSocket.getOutputStream())), true);
                        mDataOutputStream = new DataOutputStream(mClientSocket.getOutputStream());
                        mDataOutputStream.write(guidByte);
                    }catch (Exception e){
                        mClientSocket=null;
                        e.printStackTrace();
                    }finally {
                        getData();
                    }
                }
                byte[] str = intent.getByteArrayExtra("air_condition");
                LogUtils.e("开空调：" + str[99]);
                try {
                    if (mDataOutputStream != null) {
                        mDataOutputStream.write(str);
                    }
                } catch (IOException e) {
                    mClientSocket=null;
                    e.printStackTrace();
                }finally {
                    getData();
                }
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private byte[] getGuidByte() {
        byte[] a = new byte[4];
        a[0] = (byte) (Integer.parseInt(AppConfig.getInstance().getGuid()) & 0xff);
        a[1] = (byte) (Integer.parseInt(AppConfig.getInstance().getGuid()) >> 8 & 0xff);
        a[2] = (byte) (Integer.parseInt(AppConfig.getInstance().getGuid()) >> 16 & 0xff);
        a[3] = (byte) (Integer.parseInt(AppConfig.getInstance().getGuid()) >> 24 & 0xff);
        byte[] b = new byte[24];
        b[0] = 0;
        b[1] = 0;
        b[2] = 0;
        b[3] = 24;
        b[4] = 0;
        b[5] = -59;
        b[6] = 3;
        b[7] = -23;
        b[8] = 0;
        b[9] = 0;
        b[10] = 0;
        b[11] = 0;
        b[12] = a[0];
        b[13] = a[1];
        b[14] = a[2];
        b[15] = a[3];
        b[16] = 0;
        b[17] = 0;
        b[18] = 0;
        b[19] = 0;
        b[20] = 0;
        b[21] = 0;
        b[22] = 0;
        b[23] = 1;
        return b;
    }
}
