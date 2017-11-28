package com.play001.cloud.webapi.entity;

import java.util.Date;

//身份token
public class Credential {
    private Integer id;
    private String token;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void resetToken(){//生成token

    }

}
