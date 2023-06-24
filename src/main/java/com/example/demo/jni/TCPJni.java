package com.example.demo.jni;

public class TCPJni {
    static {
        System.loadLibrary("tcp");
    }

    public static native String rcv();
}
