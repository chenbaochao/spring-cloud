package com.play001.cloud.credentialapi.entity;


import com.google.gson.Gson;

public class Credential {
   private Integer userId;
    private Long expiryDate;

    public Credential() {
    }

    public Credential(Integer userId, Long expiryDate) {
        this.userId = userId;
        this.expiryDate = expiryDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Long expiryDate) {
        this.expiryDate = expiryDate;
    }
    public String toJson(){
        return new Gson().toJson(this);
    }
}
