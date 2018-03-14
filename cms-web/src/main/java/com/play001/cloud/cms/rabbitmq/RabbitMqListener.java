package com.play001.cloud.cms.rabbitmq;

import com.play001.cloud.cms.entity.LoginLog;
import com.play001.cloud.cms.mapper.admin.LoginLogMapper;
import com.play001.cloud.cms.service.AdminService;
import com.play001.cloud.cms.util.CommonUtil;
import com.play001.cloud.support.entity.RabbitMessage.LoginRabbitMessage;
import com.play001.cloud.support.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * rabbitListener, 接收登陆消息
 * 查询登陆地址并保存进登陆日志
 */
@Component
public class RabbitMqListener {

    @Autowired
    private LoginLogMapper loginLogMapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //@RabbitListener(queues = "loginQueue")
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("loginQueue"),
            exchange = @Exchange("defaultExchange"),
            key = "LOGIN"))
    public void onReceiveMessage(LoginRabbitMessage message){
        logger.info ("收到消息:"+message);
        LoginLog loginLog = new LoginLog();
        loginLog.setAdminId(message.getAdminId());
        loginLog.setIp(message.getIp());
        loginLog.setTime(DateUtil.getTime(message.getCreateTime()));
        //查找登陆地址
        loginLog.setLocation(CommonUtil.getLocationByIp(loginLog.getIp()));
        loginLogMapper.add(loginLog);

    }
}
