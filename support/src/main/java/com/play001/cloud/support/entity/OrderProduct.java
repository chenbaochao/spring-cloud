package com.play001.cloud.support.entity;

//订单详细信息
public class OrderProduct {
    private Long id;
    private Long productId;
    private String productName;
    private Image productThumb;
    private String productSpecName;
    private Byte productNumber;
    private Integer productAmount;
    private Byte commentStatus;

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

    public Byte getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Byte productNumber) {
        this.productNumber = productNumber;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public Byte getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Byte commentStatus) {
        this.commentStatus = commentStatus;
    }
}
