package com.play001.cloud.support.entity;

import com.play001.cloud.support.entity.user.UserAddress;

//订单-收货地址信息
public class OrderAddress {
    private Long id;
    private Long orderId;
    private String username;
    private String userPhone;
    private String userAddr;
    private String zipcode;

    public OrderAddress(Long id, UserAddress userAddress) {
        this.orderId = id;
        this.username = userAddress.getUsername();
        this.userPhone = userAddress.getUserPhone();
        this.userAddr = userAddress.getUserAddress();
        this.zipcode = userAddress.getZipcode();
    }

    public OrderAddress() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
