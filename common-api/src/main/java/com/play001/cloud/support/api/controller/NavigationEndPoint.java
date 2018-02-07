package com.play001.cloud.support.api.controller;

import com.play001.cloud.support.api.service.NavigationService;
import com.play001.cloud.support.entity.NavigationBar;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/navigation")
public class NavigationEndPoint {

    @Autowired
    private NavigationService navigationService;

    /**
     * 获取首页-顶部导航栏
     */
    @RequestMapping(value = "/getTopBarNavigationBars", method = RequestMethod.GET)
    public ResponseEntity<List<NavigationBar>> getTopBarNavigationBars(){
        return navigationService.getTopBarNavigationBars();
    }

    /**
     * 获取首页-幻灯片下面六个小链接
     */
    @RequestMapping(value = "/getChannelNavigationBars", method = RequestMethod.GET)
    public ResponseEntity<List<NavigationBar>> getChannelNavigationBars(){
        return navigationService.getChannelNavigationBars();
    }

}
