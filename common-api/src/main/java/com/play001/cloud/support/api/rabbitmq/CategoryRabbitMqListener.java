package com.play001.cloud.support.api.rabbitmq;

import com.play001.cloud.support.api.service.NavigationService;
import com.play001.cloud.support.entity.RabbitMessage.NavigationRabbitMessage;
import com.play001.cloud.support.enums.RabbitEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * rabbitListener, 接收产品目录信息
 * 重新缓存信息
 */
@Component
public class CategoryRabbitMqListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NavigationService navigationService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("categoryQueue"),
            exchange = @Exchange("defaultExchange"),
            key = "CATEGORY_CHANGE"))
    public void onReceiveMessage(NavigationRabbitMessage message){
        logger.info ("收到消息:"+message);
        navigationService.cachingTopBarNavigationBars();
        navigationService.cachingChannelNavigationBars();
    }
}
