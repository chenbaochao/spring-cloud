package com.play001.cloud.credentialapi.entity;


import com.google.gson.Gson;

public class Credential {
   private Integer userId;
   private String username;
    private Long expiryDate;

    public Credential() {
    }

    public Credential(Integer userId, String username, Long expiryDate) {
        this.userId = userId;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }
}
