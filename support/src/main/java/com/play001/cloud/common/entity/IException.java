package com.play001.cloud.common.entity;

/**
 * 自定义异常,抛出的错误信息要返回,其余的异常不返回抛出的错误
 */
public class IException extends Exception {
    public IException(String message) {
        super(message);
    }
}
