package com.play001.cloud.support.entity;

import com.play001.cloud.support.enums.RedisMessageEnum;

import java.io.Serializable;
import java.util.UUID;

public class RedisMessage implements Serializable {
    public static final String CHANNEL = "DATA_CHANGE_CHANNEL";
    //抢占消息时,锁的数据库索引
    public static final int DB_INDEX = 1;
    private RedisMessageEnum msgType;
    //只有更改了产品数据,值才有效
    private Long productId;
    /*
        消息的唯一标识符
     */
    private String id;
    /*
        消息创建时间
     */
    private Long time;

    public RedisMessage(RedisMessageEnum msgType) {
        this.msgType = msgType;
        this.id = UUID.randomUUID().toString();
        this.time = System.currentTimeMillis();
    }
    public RedisMessageEnum getMsgType() {
        return msgType;
    }

    public void setMsgType(RedisMessageEnum msgType) {
        this.msgType = msgType;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
