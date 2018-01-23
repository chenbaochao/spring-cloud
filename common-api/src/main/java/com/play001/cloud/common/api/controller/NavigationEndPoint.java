package com.play001.cloud.common.api.controller;

import com.play001.cloud.common.api.service.NavigationService;
import com.play001.cloud.common.entity.Navigation;
import com.play001.cloud.common.entity.NavigationBar;
import com.play001.cloud.common.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/navigation")
public class NavigationEndPoint {

    @Autowired
    private NavigationService navigationService;

    /**
     * 获取首页-顶部导航栏
     */
    @RequestMapping(value = "/getTopBarNavigationBars", method = RequestMethod.GET)
    public Response<List<NavigationBar>> getTopBarNavigationBars(){
        return navigationService.listNavigationBarByNavigationId(1);
    }

    /**
     * 获取首页-幻灯片下面六个小链接
     */
    @RequestMapping(value = "/getNavigationBars1", method = RequestMethod.GET)
    public Response<List<NavigationBar>> getNavigationBars1(){
        return navigationService.listNavigationBarByNavigationId(6);
    }

}
