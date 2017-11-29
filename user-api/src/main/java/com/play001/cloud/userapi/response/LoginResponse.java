package com.play001.cloud.userapi.response;


/**
 * 用于ajax返回各种信息
 * 通用类型
 */
public class LoginResponse extends Response{

    private Integer userId;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
