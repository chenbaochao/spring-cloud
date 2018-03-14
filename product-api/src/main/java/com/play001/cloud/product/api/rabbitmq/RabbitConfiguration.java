package com.play001.cloud.product.api.rabbitmq;

import com.play001.cloud.support.enums.RabbitEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitMq配置
 */
@Configuration
public class RabbitConfiguration {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public Exchange myExchange(){
        logger.info("defaultExchange init");
        return new DirectExchange("defaultExchange", false, false);
    }

    @Bean
    public Queue loginQueue(){
        logger.info("queue init");
        return new Queue("productQueue", false);
    }

    @Bean
    @Autowired
    public Binding binding(Queue queue, Exchange exchange){
        logger.info("binding init");
        return  BindingBuilder.bind(queue).to(exchange).with(RabbitEnum.PRODUCT_CHANGE.getRouteKey()).noargs();
    }
}
