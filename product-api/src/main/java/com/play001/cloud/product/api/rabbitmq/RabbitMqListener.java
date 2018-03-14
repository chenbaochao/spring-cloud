package com.play001.cloud.product.api.rabbitmq;

import com.play001.cloud.product.api.serivce.ProductService;
import com.play001.cloud.support.entity.RabbitMessage.ProductRabbitMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * rabbitListener, 接收产品修改,删除消息
 * 移除redis中的缓存
 */
@Component
public class RabbitMqListener {

    @Autowired
    private ProductService productService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "productQueue")
    public void onReceiveMessage(ProductRabbitMessage message){
        logger.info ("收到消息:"+message);
        Long productId = message.getProductId();
        productService.removeCachingById(productId);
    }
}
