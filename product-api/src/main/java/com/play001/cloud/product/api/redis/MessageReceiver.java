package com.play001.cloud.product.api.redis;

import com.play001.cloud.product.api.serivce.ProductService;
import com.play001.cloud.support.entity.AppInfo;
import com.play001.cloud.support.entity.RedisMessage;
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

@Component
public class MessageReceiver implements MessageListener{

    private Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    private RedisSerializer<Object> redisSerializer;

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AppInfo appInfo;
    @Autowired
    private ProductService productService;

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
                case PRODUCT_CHANGE:
                    break;
                default:
                    return;
            }
            logger.info(appInfo+"-开始抢占消息");
            /*
                执行者的ID,如果不设置成数组类型,在拉姆达表达式里面无法更改值
             */
            String ownerId[] = new String[1];
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
                case PRODUCT_CHANGE:
                    productService.removeCachingById(redisMessage.getProductId());
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
