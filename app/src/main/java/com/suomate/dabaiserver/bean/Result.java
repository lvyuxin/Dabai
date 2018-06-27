package com.suomate.dabaiserver.bean;

/**
 * Created by lenovo on 2018/6/20.
 */

public class Result<T> {
    private  int code; // 状态码
    private T data; // 结果。
    private String message; // 提示信息。

    public Result(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
