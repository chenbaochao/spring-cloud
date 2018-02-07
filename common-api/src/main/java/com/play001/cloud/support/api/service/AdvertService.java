package com.play001.cloud.support.api.service;


import com.play001.cloud.support.entity.Advert;
import com.play001.cloud.support.api.mapper.AdvertMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.play001.cloud.support.enums.AdvertEnum.SLIDER_CATEGORY_ID;
import static com.play001.cloud.support.enums.AdvertEnum.UNDER_SLIDER_CATEGORY_ID;

import static com.play001.cloud.support.enums.AdvertEnum.SLIDER_DATA_NUMBER;
import static com.play001.cloud.support.enums.AdvertEnum.UNDER_SLIDER_DATA_NUMBER;
/**
 * 各种广告
 */
@Service
public class AdvertService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private AdvertMapper advertMapper;

    //@Autowired
    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @Autowired
    public AdvertService(AdvertMapper advertMapper) {
        this.advertMapper = advertMapper;
    }


    /**
     * 获取首页图片轮播数据
     */
    @SuppressWarnings("unchecked")
    public List<Advert> getSliderAdvert() {
        //缓存中有数据就从缓存中取
        List<Advert> sliderAdverts = (List<Advert>)redisTemplate.opsForValue().get("sliderAdverts");
        if(sliderAdverts == null){
            logger.info("find from mysql---------------");
            return advertMapper.getAdvertsByCategoryId(SLIDER_CATEGORY_ID.value(), SLIDER_DATA_NUMBER.value());
        }else{
            logger.info("find from redis---------------");
            return sliderAdverts;
        }

    }

    /**
     * 轮播下面的广告
     */
    @SuppressWarnings("unchecked")
    public List<Advert> getUnderSliderAdvert() {
        //缓存中有数据就从缓存中取
        List<Advert> underSliderAdvert = (List<Advert>)redisTemplate.opsForValue().get("underSliderAdvert");
        if(underSliderAdvert == null){
            logger.info("find from mysql---------------");
            return advertMapper.getAdvertsByCategoryId(UNDER_SLIDER_CATEGORY_ID.value(), UNDER_SLIDER_DATA_NUMBER.value());
        }else {
            logger.info("find from redis---------------");
            return underSliderAdvert;
        }

    }

    /**
     * 缓存首页图片轮播数据到redis
     */
    public void cachingSliderAdvert(){
        logger.info("缓存首页图片轮播数据到redis---------------");
        redisTemplate.delete("sliderAdverts");
        List<Advert> sliderAdverts = advertMapper.getAdvertsByCategoryId(SLIDER_CATEGORY_ID.value(), SLIDER_DATA_NUMBER.value());
        redisTemplate.opsForValue().set("sliderAdverts", sliderAdverts);
    }
    /**
     * 缓存轮播下面的广告
     */
    public void cachingUnderSliderAdvert(){
        logger.info("缓存轮播下面的广告数据到redis---------------");
        redisTemplate.delete("underSliderAdvert");
        List<Advert> underSliderAdvert = advertMapper.getAdvertsByCategoryId(UNDER_SLIDER_CATEGORY_ID.value(), UNDER_SLIDER_DATA_NUMBER.value());
        redisTemplate.opsForValue().set("underSliderAdvert", underSliderAdvert);
    }
}
