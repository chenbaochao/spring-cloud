package com.play001.cloud.support.api.rabbitmq;

import com.play001.cloud.support.api.service.NavigationService;
import com.play001.cloud.support.entity.RabbitMessage.NavigationRabbitMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * rabbitListener, 接收导航事件
 * 移除redis中的缓存
 */
@Component
public class NavigationRabbitMqListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NavigationService navigationService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("navigationQueue"),
            exchange = @Exchange("defaultExchange"),
            key = "NAVIGATION_CHANGE"))
    public void onReceiveMessage(NavigationRabbitMessage message){
        logger.info ("收到消息:"+message);
        navigationService.cachingTopBarNavigationBars();
        navigationService.cachingChannelNavigationBars();
    }
}
