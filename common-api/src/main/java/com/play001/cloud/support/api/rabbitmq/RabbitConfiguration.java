package com.play001.cloud.support.api.rabbitmq;

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

/*    @Bean
    public Queue navigationQueue(){
        logger.info("queue init");
        return new Queue("navigationQueue", false);
    }

    @Bean
    @Autowired
    public Binding navigationBinding(Queue navigationQueue, Exchange exchange){
        logger.info("binding init");
        return  BindingBuilder.bind(navigationQueue).to(exchange).with(RabbitEnum.NAVIGATION_CHANGE.getRouteKey()).noargs();
    }*/

/*    @Bean
    public Queue categoryQueue(){
        logger.info("queue init");
        return new Queue("categoryQueue", false);
    }

    @Bean
    @Autowired
    public Binding categoryBinding(Queue categoryQueue, Exchange exchange){
        logger.info("binding init");
        return  BindingBuilder.bind(categoryQueue).to(exchange).with(RabbitEnum.NAVIGATION_CHANGE.getRouteKey()).noargs();
    }*/
}
