package com.play001.cloud.common.api.service;


import com.play001.cloud.common.api.mapper.NavigationMapper;
import com.play001.cloud.common.entity.Navigation;
import com.play001.cloud.common.entity.NavigationBar;
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
     * 通过NavigationID查找NavigationBar(status == 1)
     */
    public Response<List<NavigationBar>> listNavigationBarByNavigationId(Integer navigationId){
        Response<List<NavigationBar>> response = new Response<>();
        List<NavigationBar> navigationBars = navigationMapper.listNavigationBarByNavigationId(navigationId);
        response.setMessage(navigationBars);
        return response;
    }



    //查找
    public Navigation findById(Integer id){
        if(id != null){
            return navigationMapper.findById(id);
        }
        return null;
    }


}
