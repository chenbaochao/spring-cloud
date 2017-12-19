package com.play001.cloud.web.response;


/**
 * 用于ajax返回
 * 通用类型
 */
public class LoginResponse extends Response{

    private String credential;//加密后的身份令牌

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }
}
