package com.play001.cloud.cms.service;


import com.play001.cloud.cms.mapper.NavigationMapper;
import com.play001.cloud.common.entity.Navigation;
import com.play001.cloud.common.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NavigationService {

    @Autowired
    private NavigationMapper navigationMapper;

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
    public Response<Integer> setStatus(Integer id, Boolean status){
        if(status != null && id != null){
            navigationMapper.setStatus(id, status);
            return new Response<Integer>().setStatus(Response.SUCCESS);
        }
        return new Response<Integer>().setErrMsg("参数错误");
    }

    /**
     * 更新导航栏
     * 只更新name, remarks, status
     */
    public Response<Integer> update(Navigation navigation){
        navigationMapper.update(navigation);
        return new Response<Integer>().setStatus(Response.SUCCESS);
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
