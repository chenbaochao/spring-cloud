package com.play001.cloud.common.entity;


import com.google.gson.Gson;

public class UserCredential {
   private Long userId;
   private String username;
    private Long expiryDate;

    public UserCredential() {
    }

    public UserCredential(Long userId, String username, Long expiryDate) {
        this.userId = userId;
        this.username = username;
        this.expiryDate = expiryDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
