package com.play001.cloud.support.entity.RabbitMessage;

public class ProductRabbitMessage extends AbstractRabbitMessage {
    private Long productId;
    //0=更新, 1=删除
    private Byte type;
    public static final byte UPDATE = 0;
    public static final byte DELETE = 1;

    public ProductRabbitMessage() {
    }

    public ProductRabbitMessage(Long productId, Byte type, long time) {
        super(time);
        this.productId = productId;
        this.type = type;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProductRabbitMessage{" +
                "productId=" + productId +
                ", type=" + type +
                ", createTime=" + createTime +
                '}';
    }
}
