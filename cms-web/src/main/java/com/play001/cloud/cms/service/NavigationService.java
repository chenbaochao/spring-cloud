package com.play001.cloud.cms.service;


import com.play001.cloud.cms.mapper.navigation.NavigationMapper;
import com.play001.cloud.support.entity.Navigation;
import com.play001.cloud.support.entity.RabbitMessage.NavigationRabbitMessage;
import com.play001.cloud.support.entity.RedisMessage;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.enums.RabbitEnum;
import com.play001.cloud.support.enums.RedisMessageEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NavigationService {

    @Autowired
    private NavigationMapper navigationMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 获取所有的导航
     */
    public Map<String, Object> getList(){
        Map<String, Object> map = new HashMap<>();
        List<Navigation> navigation = navigationMapper.findAll();
        map.put("total", navigation.size());
        map.put("rows",navigation);
        return map;
    }
    /**
     * 设置导航状态
     */
    public ResponseEntity<Integer> setStatus(Integer id, Boolean status){
        if(status != null && id != null){
            navigationMapper.setStatus(id, status);
            return new ResponseEntity<Integer>().setStatus(ResponseEntity.SUCCESS);
        }
        NavigationRabbitMessage rabbitMessage = new NavigationRabbitMessage(System.currentTimeMillis());
        rabbitTemplate.convertAndSend("defaultExchange", RabbitEnum.NAVIGATION_CHANGE.getRouteKey(), rabbitMessage);
        return new ResponseEntity<Integer>().setErrMsg("参数错误");
    }

    /**
     * 更新导航栏
     * 只更新name, remarks, status
     */
    public ResponseEntity<Integer> update(Navigation navigation){
        navigationMapper.update(navigation);
        //redisTemplate.convertAndSend(RedisMessage.CHANNEL, new RedisMessage(RedisMessageEnum.NAVIGATION_CHANGE));
        //通知其它微服务更新缓存
        NavigationRabbitMessage rabbitMessage = new NavigationRabbitMessage(System.currentTimeMillis());
        rabbitTemplate.convertAndSend("defaultExchange", RabbitEnum.NAVIGATION_CHANGE.getRouteKey(), rabbitMessage);
        return new ResponseEntity<Integer>().setStatus(ResponseEntity.SUCCESS);
    }

    //查找
    public Navigation findById(Integer id){
        if(id != null){
            return navigationMapper.findById(id);
        }
        return null;
    }

    /**
     * 通过导航栏ID查找导航
     */
    public Navigation findByNavigationBarId(Integer navigationBarId){
        if(navigationBarId != null){
            return navigationMapper.findByNavigationBarId(navigationBarId);
        }
        return null;
    }
}
