package com.play001.cloud.support.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

//应用信息
@Component
public class AppInfo {
    //唯一标识符
    private  final String id = UUID.randomUUID().toString();

    //服务端口
    @Value("${server.port}")
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return  "id=" + id + ", port=" + port;
    }
}
