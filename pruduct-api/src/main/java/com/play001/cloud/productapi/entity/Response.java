package com.play001.cloud.productapi.entity;

/**
 * 用于ajax返回各种信息
 * 通用类型
 */
public class Response<T> {
    public static  final String  SUCCESS = "SUCCESS";
    public static  final String  ERROR = "ERROR";
    protected String status;
    protected String errMsg;
    T message;
    public Response() {
    }

    public Response(String status) {
        this.status = status;
    }

    public Response(String status, String errMsg) {
        this.status = status;
        this.errMsg = errMsg;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
