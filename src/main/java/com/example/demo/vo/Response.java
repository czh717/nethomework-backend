package com.example.demo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    public Response(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static Response<Object> buildSuccess() {
        return new Response<>(200, "", null);
    }


    public static <T> Response<T> buildSuccess(T result) {
        return new Response<>(200, "", result);
    }

    public static  Response<Object> buildFailure(Integer code, String message) {
        return new Response<>(code, message, null);
    }
}