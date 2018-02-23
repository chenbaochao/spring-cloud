package com.play001.cloud.support.entity;

import com.google.gson.Gson;

/**
 * 用于ajax返回各种信息
 * 通用类型
 */
public class ResponseEntity<T> {
    public static  final String  SUCCESS = "SUCCESS";
    public static  final String  ERROR = "ERROR";
    private String status;
    private String errMsg;
    private T message;


    public ResponseEntity() {}

    public ResponseEntity(String status) {
        this.status = status;
    }

    public ResponseEntity(String status, String errMsg) {
        this(status);
        this.errMsg = errMsg;
    }


    public ResponseEntity<T> setMessage(T message) {
        //重大BUG, feign请求返回数据时,即使message为null,也会调用setMessage方法
        if(message != null) {
            this.status = SUCCESS;
            this.message = message;
        }
        return this;
    }

    public ResponseEntity<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public ResponseEntity<T> setErrMsg(String errMsg) {
        //重大BUG, feign请求返回数据时,errMsg,也会调用setErrMsg方法
        if(errMsg != null) {
            this.status = ERROR;
            this.errMsg = errMsg;
        }
        return this;
    }
    public String getStatus() {
        return status;
    }
    public T getMessage() {
        return message;
    }
    public String getErrMsg() { return errMsg; }
    public String toJson(){
        return new Gson().toJson(this);
    }
}
