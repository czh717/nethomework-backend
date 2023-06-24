package com.example.demo.jni;

public class NativeMethods {
    static {
        System.loadLibrary("NativeMethods");
    }

    // 发送消息相关方法
    public native String udpSend(int sPort,int tPort, String sIp, String tIp, String content);
    public native String ipSend(int sPort,int tPort, String sIp, String tIp, String content);
    public native String getMacAddress(String ip);
    public native String ethSend(String sMacAddress,String tMacAddress, String content);
    public native String tapSend(String content);


    // 接收消息相关方法
    public native String tapRcv();
    public native String ethRcv(String tapData);
    public native String ipRcv(String macData);
    public native String udpRcv(String ipData);
}
