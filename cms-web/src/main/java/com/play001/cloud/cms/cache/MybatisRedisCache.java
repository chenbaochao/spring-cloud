package com.play001.cloud.cms.cache;

import com.play001.cloud.support.util.SpringContextUtils;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MybatisRedisCache implements Cache{

    private String id;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //读写锁
    private ReadWriteLock lock = new ReentrantReadWriteLock();


    public MybatisRedisCache(String id) {
        this.id = id;
        logger.info("--------MybatisRedisCache初始化-----------------");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        RedisResource.INSTANCE.getRedisTemplate().opsForValue().set(key, value);
        //logger.info("MybatisRedisCache-加入元素");
    }

    @Override
    public Object getObject(Object key) {
        //logger.info("MybatisRedisCache-取元素");
        return  RedisResource.INSTANCE.getRedisTemplate().opsForValue().get(key);
    }

    @Override
    public Object removeObject(Object key) {
        //logger.info("MybatisRedisCache-删除元素");
        RedisResource.INSTANCE.getRedisTemplate().delete(key);
        return null;
    }

    @Override
    public void clear() {
        //logger.info("MybatisRedisCache-清空缓存");
        RedisResource.INSTANCE.getRedisTemplate().execute((RedisCallback<Object>) connection -> {
            connection.flushDb();
            return null;
        });
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return lock;
    }

    /**
     * 由于redisTemplate需要手动注入,但是直接用SpringContextUtils获取bean时,SpringContextUtils还并没有被初始化......
     */
    enum RedisResource{
        INSTANCE;
        private RedisTemplate<Object, Object> redisTemplate;
        @SuppressWarnings("unchecked")
        RedisResource(){
            redisTemplate = SpringContextUtils.getContext().getBean("redisTemplate", RedisTemplate.class);
        }
        public RedisTemplate<Object, Object> getRedisTemplate(){
            return redisTemplate;
        }
    }

}
