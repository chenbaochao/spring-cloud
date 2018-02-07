package com.play001.cloud.support.api.redis;

import com.play001.cloud.support.api.service.AdvertService;
import com.play001.cloud.support.api.service.NavigationService;
import com.play001.cloud.support.api.service.SiteService;
import com.play001.cloud.support.entity.AppInfo;
import com.play001.cloud.support.entity.RedisMessage;
import com.play001.cloud.support.enums.RedisMessageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MessageReceiver implements MessageListener{

    private Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    private RedisSerializer<Object> redisSerializer;

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AdvertService advertService;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private AppInfo appInfo;
    @Autowired
    private SiteService siteService;

    @Value("${spring.redis.database}")
    private int dbIndex;

    @Autowired
    @SuppressWarnings("unchecked")
    public MessageReceiver(RedisTemplate redisTemplate, StringRedisTemplate stringRedisTemplate) {
        logger.info("MessageReceiver----------------初始化");
        redisSerializer = redisTemplate.getDefaultSerializer();

        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 当cms端更改了数据后会发送消息到各个微服务端
     * 微服务端再根据消息的内容更新缓存
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            long timeout = 30;//消息创建时间距离收到消息已经超过了此时间,则不在处理该消息,单位秒
            RedisMessage redisMessage = (RedisMessage)redisSerializer.deserialize(message.getBody());
            logger.info(appInfo+"收到消息, 消息ID:"+redisMessage.getId());
            if(System.currentTimeMillis() > redisMessage.getTime()+timeout){
                return ;
            }
            //只有下面三种事件才抢占消息
            switch (redisMessage.getMsgType()){
                case ADVERT_CHANGE:
                case NAVIGATION_CHANGE:
                case SITE_CONF_CHANGE:
                    break;
                default:
                    return;
            }
            logger.info(appInfo+"-开始抢占消息");
            String ownerId[] = new String[1];//执行者的ID
            //开启事务,切换到公共数据库
            stringRedisTemplate.execute((RedisCallback<Object>)connection->{
                connection.select(RedisMessage.DB_INDEX);
                connection.setNX(redisSerializer.serialize(redisMessage.getId()), redisSerializer.serialize(appInfo.getId()));
                byte[] temp = connection.get(redisSerializer.serialize(redisMessage.getId()));
                ownerId[0] = (String) redisSerializer.deserialize(temp);
                if(ownerId[0] != null && ownerId[0].equals(appInfo.getId())){
                    logger.info(appInfo+" 抢占成功");
                    //设置缓存过期时间
                    connection.expire(redisSerializer.serialize(redisMessage.getId()),timeout);
                }else{
                    logger.info("抢占失败, 消息被 "+ownerId[0]+" 抢占");
                }
                connection.select(dbIndex);
                return null;
            });

            if(ownerId[0] == null || !ownerId[0].equals(appInfo.getId())){
                return;
            }

            switch (redisMessage.getMsgType()){
                case ADVERT_CHANGE:
                    advertService.cachingSliderAdvert();
                    advertService.cachingUnderSliderAdvert();
                    break;
                case NAVIGATION_CHANGE:
                    navigationService.cachingChannelNavigationBars();
                    navigationService.cachingTopBarNavigationBars();
                    break;
                case SITE_CONF_CHANGE:
                    siteService.cachingConf();
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
