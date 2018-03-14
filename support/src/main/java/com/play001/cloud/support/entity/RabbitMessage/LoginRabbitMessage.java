package com.play001.cloud.support.entity.RabbitMessage;

import java.io.Serializable;

/**
 * 用户登陆成功消息
 * 保存登陆日志
 */
public class LoginRabbitMessage extends AbstractRabbitMessage {

    //登陆管理员Id
    private Integer adminId;
    //登陆IP
    private String ip;
    public LoginRabbitMessage() {
    }


    public LoginRabbitMessage(long time, Integer adminId, String ip) {
        super(time);
        this.adminId = adminId;
        this.ip = ip;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "LoginRabbitMessage{" +
                "adminId=" + adminId +
                ", ip='" + ip + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
