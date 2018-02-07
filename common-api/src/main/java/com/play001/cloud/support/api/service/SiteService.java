package com.play001.cloud.support.api.service;

import com.play001.cloud.support.api.mapper.SiteMapper;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.SiteConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SiteService {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private SiteMapper siteMapper;


    public ResponseEntity<SiteConf> getConf(){
        ResponseEntity<SiteConf> responseEntity = new ResponseEntity<>();
        SiteConf siteConf = (SiteConf)redisTemplate.opsForValue().get("siteConf");
        if(siteConf == null){
            siteConf = siteMapper.getConf();
        }
        responseEntity.setMessage(siteConf);
        return responseEntity;
    }

    /**
     * 缓存数据
     */
    public void cachingConf(){
        redisTemplate.delete("siteConf");
        redisTemplate.opsForValue().set("siteConf", siteMapper.getConf());
    }
}
