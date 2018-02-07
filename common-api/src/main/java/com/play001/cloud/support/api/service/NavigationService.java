package com.play001.cloud.support.api.service;


import com.play001.cloud.support.api.mapper.NavigationMapper;
import com.play001.cloud.support.entity.Navigation;
import com.play001.cloud.support.entity.NavigationBar;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NavigationService {

    private final int TOP_BAR_NAVIGATION_ID = 1;
    private final int CHANNEL_NAVIGATION_ID = 6;

    private NavigationMapper navigationMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public NavigationService(NavigationMapper navigationMapper) {
        this.navigationMapper = navigationMapper;
    }





    /**
     * 获取首页-顶部导航栏
     */
    @SuppressWarnings("unchecked")
    public ResponseEntity<List<NavigationBar>> getTopBarNavigationBars(){
        ResponseEntity<List<NavigationBar>> responseEntity = new ResponseEntity<>();
        List<NavigationBar> navigationBars = (List<NavigationBar>)redisTemplate.opsForValue().get("topBarNavigationBars");
        if(navigationBars == null){
            navigationBars = navigationMapper.listNavigationBarByNavigationId(TOP_BAR_NAVIGATION_ID);
        }
        responseEntity.setMessage(navigationBars);
        return responseEntity;
    }

    /**
     * 获取首页-幻灯片下面六个小链接
     */
    @SuppressWarnings("unchecked")
    public ResponseEntity<List<NavigationBar>> getChannelNavigationBars(){
        ResponseEntity<List<NavigationBar>> responseEntity = new ResponseEntity<>();
        List<NavigationBar> navigationBars = (List<NavigationBar>)redisTemplate.opsForValue().get("channelNavigationBars");
        if(navigationBars == null){
            navigationBars = navigationMapper.listNavigationBarByNavigationId(CHANNEL_NAVIGATION_ID);
        }
        responseEntity.setMessage(navigationBars);
        return responseEntity;
    }


    //缓存首页-幻灯片下面六个小链接
    public void cachingChannelNavigationBars(){
        redisTemplate.delete("channelNavigationBars");
        List<NavigationBar> navigationBars = navigationMapper.listNavigationBarByNavigationId(CHANNEL_NAVIGATION_ID);
        redisTemplate.opsForValue().set("channelNavigationBars", navigationBars);
    }

    //缓存首页-顶部导航栏数据
    public void cachingTopBarNavigationBars(){
        redisTemplate.delete("topBarNavigationBars");
        List<NavigationBar> navigationBars = navigationMapper.listNavigationBarByNavigationId(TOP_BAR_NAVIGATION_ID);
        redisTemplate.opsForValue().set("topBarNavigationBars", navigationBars);
    }

    /*
     * 通过NavigationID查找NavigationBar(status == 1)

    public ResponseEntity<List<NavigationBar>> listNavigationBarByNavigationId(Integer navigationId){
        ResponseEntity<List<NavigationBar>> response = new ResponseEntity<>();
        List<NavigationBar> navigationBars = navigationMapper.listNavigationBarByNavigationId(navigationId);
        response.setMsgType(navigationBars);
        return response;
    }*/

    //查找
    public Navigation findById(Integer id){
        if(id != null){
            return navigationMapper.findById(id);
        }
        return null;
    }


}
