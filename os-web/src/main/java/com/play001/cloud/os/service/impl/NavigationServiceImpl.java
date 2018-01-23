package com.play001.cloud.os.service.impl;


import com.netflix.discovery.converters.Auto;
import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.NavigationBar;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.os.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NavigationServiceImpl {

    @Autowired
    private NavigationService navigationService;

    /**
     * 首页-顶部 数据
     */
    public List<NavigationBar> getTopBarNavigationBars() throws IException {
        Response<List<NavigationBar>> response = navigationService.getTopBarNavigationBars();
        if(Objects.equals(response.getStatus(), Response.ERROR)){
            throw  new IException(response.getErrMsg());
        }
        return response.getMessage();
    }

    /**
     * 首页-轮播图下方六个小链接
     */
    public List<NavigationBar> getNavigationBars1() throws IException {
        Response<List<NavigationBar>> response = navigationService.getNavigationBars1();
        if(Objects.equals(response.getStatus(), Response.ERROR)){
            throw  new IException(response.getErrMsg());
        }
        return response.getMessage();
    }
}
