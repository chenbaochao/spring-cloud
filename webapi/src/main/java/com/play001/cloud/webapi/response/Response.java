package com.play001.cloud.webapi.response;

/**
 * 用于ajax返回各种信息
 * 通用类型
 */
public class Response {
    public static  final String  SUCCESS = "SUCCESS";
    public static  final String  ERROR = "ERROR";
    protected String status;
    protected String errMsg;

    public Response() {
    }

    public Response(String status, String errMsg) {
        this.status = status;
        this.errMsg = errMsg;
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
