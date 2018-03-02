package com.play001.cloud.support.entity;
import com.play001.cloud.support.entity.user.User;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Comment implements Serializable{

    public static final Byte STATUS_HIDDEN = 0;
    public static final Byte STATUS_SHOW = 1;
    private Long id;
    @NotNull
    private Long orderId;
    //os_order_product

    @NotNull
    private Long orderProductId;
    private Long productId;
    @NotNull
    private User user;
    @NotNull
    private Byte star;
    @NotBlank
    private String content;
    @NotNull
    private Byte status;
    private String createTime;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Byte getStar() {
        return star;
    }

    public void setStar(Byte star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Long orderProductId) {
        this.orderProductId = orderProductId;
    }
}