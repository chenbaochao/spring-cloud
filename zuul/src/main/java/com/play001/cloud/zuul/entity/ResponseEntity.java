package com.play001.cloud.zuul.entity;

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
        this.status = status;
        this.errMsg = errMsg;
    }

    /**
     * 传递了数据,默认操作成功
     * 注意当泛型T为String类型时,会产生歧义,
     * 为Response(String status)和Response(T message)编译器不知道调用哪一个
     */
    public ResponseEntity(T message) {
        this.status = SUCCESS;
        this.message = message;
    }


    public ResponseEntity<T> setMessage(T message) {
        this.status = SUCCESS;
        this.message = message;
        return this;
    }

    public ResponseEntity<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public ResponseEntity<T> setErrMsg(String errMsg) {
        this.status = ERROR;
        this.errMsg = errMsg;
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
