package com.play001.cloud.support.entity.RabbitMessage;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractRabbitMessage implements Serializable{
    protected long createTime;

    public AbstractRabbitMessage() {
    }

    public AbstractRabbitMessage(long createTime) {
        this.createTime = createTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
