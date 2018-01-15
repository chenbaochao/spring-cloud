package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.NavigationBarMapper;
import com.play001.cloud.common.entity.NavigationBar;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.common.util.DateUtil;
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
    public Response<Integer> setStatus(Integer id, Boolean status){
        Response<Integer> response = new Response<>();
        if(id == null || status == null){
            return response.setErrMsg("参数错误");
        }
        navigationBarMapper.setStatus(id, status);
        return response.setStatus(Response.SUCCESS);
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
    public Response<Integer> update(NavigationBar navigationBar) {
        Response<Integer> response = new Response<>();
        if(navigationBar.getId() == null ){
            return response.setErrMsg("缺少参数ID");
        }
        navigationBarMapper.update(navigationBar);
        return response.setStatus(Response.SUCCESS);
    }

    /**
     * 创建导航栏
     */
    public Response<Integer> create(NavigationBar navigationBar) {
        Response<Integer> response = new Response<>();
        navigationBar.setCreateTime(DateUtil.getTime());
        if(navigationBar.getNavigation().getId() == null){
            return response.setErrMsg("所属分类错误");
        }
        navigationBarMapper.add(navigationBar);
        return response.setStatus(Response.SUCCESS);
    }

    /**
     * 删除导航栏
     */
    public Response<Integer> delete(Integer id) {
        Response<Integer> response = new Response<>();
        if(id == null){
            return response.setErrMsg("参数错误");
        }
        navigationBarMapper.delete(id);
        return response.setStatus(Response.SUCCESS);
    }
}
