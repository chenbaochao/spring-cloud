package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.navigation.NavigationBarMapper;
import com.play001.cloud.support.entity.NavigationBar;
import com.play001.cloud.support.entity.RabbitMessage.NavigationRabbitMessage;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.enums.RabbitEnum;
import com.play001.cloud.support.util.DateUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 导航栏
 */
@Service
public class NavigationBarService {

    @Autowired
    private NavigationBarMapper navigationBarMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 导航栏分页
     * @param navigationId 导航ID
     * @param offset 开始位置
     * @param limit 数据条数
     */
    public Map<String, Object> getList(Integer navigationId, Integer offset, Integer limit){
        Map<String, Object> map = new HashMap<>();
        map.put("total", navigationBarMapper.count(navigationId));
        map.put("rows", navigationBarMapper.pagination(navigationId, offset, limit));
        return map;
    }

    /**
     * 设置显示或者隐藏
     */
    public ResponseEntity<Integer> setStatus(Integer id, Byte status){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(id == null || status == null){
            return responseEntity.setErrMsg("参数错误");
        }
        navigationBarMapper.setStatus(id, status);

        NavigationRabbitMessage rabbitMessage = new NavigationRabbitMessage(System.currentTimeMillis());
        rabbitTemplate.convertAndSend("defaultExchange", RabbitEnum.NAVIGATION_CHANGE.getRouteKey(), rabbitMessage);
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }

    /*
    public NavigationBar findById(Integer id){
        return navigationBarMapper.findById(id);
    }
    */

    public NavigationBar findWithNavigation(Integer id){
        return navigationBarMapper.findWithNavigation(id);
    }
    /**
     * 更新导航栏
     */
    public ResponseEntity<Integer> update(NavigationBar navigationBar) {
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(navigationBar.getId() == null ){
            return responseEntity.setErrMsg("缺少参数ID");
        }
        navigationBarMapper.update(navigationBar);

        NavigationRabbitMessage rabbitMessage = new NavigationRabbitMessage(System.currentTimeMillis());
        rabbitTemplate.convertAndSend("defaultExchange", RabbitEnum.NAVIGATION_CHANGE.getRouteKey(), rabbitMessage);
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }

    /**
     * 创建导航栏
     */
    public ResponseEntity<Integer> create(NavigationBar navigationBar) {
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        navigationBar.setCreateTime(DateUtil.getTime());
        if(navigationBar.getNavigation().getId() == null){
            return responseEntity.setErrMsg("所属分类错误");
        }
        navigationBarMapper.add(navigationBar);

        NavigationRabbitMessage rabbitMessage = new NavigationRabbitMessage(System.currentTimeMillis());
        rabbitTemplate.convertAndSend("defaultExchange", RabbitEnum.NAVIGATION_CHANGE.getRouteKey(), rabbitMessage);
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }

    /**
     * 删除导航栏
     */
    public ResponseEntity<Integer> delete(Integer id) {
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(id == null){
            return responseEntity.setErrMsg("参数错误");
        }
        navigationBarMapper.delete(id);

        NavigationRabbitMessage rabbitMessage = new NavigationRabbitMessage(System.currentTimeMillis());
        rabbitTemplate.convertAndSend("defaultExchange", RabbitEnum.NAVIGATION_CHANGE.getRouteKey(), rabbitMessage);
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }
}
