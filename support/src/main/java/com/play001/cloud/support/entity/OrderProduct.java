package com.play001.cloud.support.entity;

import com.play001.cloud.support.entity.product.Product;
import com.play001.cloud.support.entity.product.Specification;
import com.play001.cloud.support.entity.user.ShopCart;

//订单详细信息
public class OrderProduct {
    public final byte UNCOMMENT = 0;//未评论
    public final byte COMMENTED = 1;//已评论
    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private Image productThumb;
    private String productSpecName;
    //购买数量
    private Integer productNumber;
    //总金额
    private Double productAmount;
    //评论状态
    private Byte commentStatus;

    public OrderProduct(ShopCart shopCart) {
        this.productId = shopCart.getProduct().getId();
        this.productName = shopCart.getProduct().getName();
        this.productThumb = shopCart.getProduct().getThumb();
        this.productSpecName = shopCart.getSpec().getName();
        this.productNumber = shopCart.getBuyNumber();

        this.commentStatus = UNCOMMENT;
    }

    public OrderProduct() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Image getProductThumb() {
        return productThumb;
    }

    public void setProductThumb(Image productThumb) {
        this.productThumb = productThumb;
    }

    public String getProductSpecName() {
        return productSpecName;
    }

    public void setProductSpecName(String productSpecName) {
        this.productSpecName = productSpecName;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public Double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Double productAmount) {
        this.productAmount = productAmount;
    }

    public Byte getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Byte commentStatus) {
        this.commentStatus = commentStatus;
    }
}
