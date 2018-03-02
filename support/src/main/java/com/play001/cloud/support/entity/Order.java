package com.play001.cloud.support.entity;

import com.play001.cloud.support.entity.user.User;

import java.util.List;

//订单表
public class Order {
    public static final byte STATUS_UN_PAY = 1;//未付款
    public static final byte STATUS_PAID = 2;//待发货
    public static final byte STATUS_INVALID = 3;//已关闭
    public static final byte STATUS_UNRECEIVE = 4;//待收货
    public static final byte STATUS_UNCOMMENT = 5;//待评价
    public static final byte STATUS_COMPLETE = 6;//已完成
    private Long id;
    private User user;
    //总金额
    private Double amount;
    private Byte status;
    private String createTime;
    private List<OrderProduct> orderProducts;
    private OrderAddress orderAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public OrderAddress getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(OrderAddress orderAddress) {
        this.orderAddress = orderAddress;
    }
}
