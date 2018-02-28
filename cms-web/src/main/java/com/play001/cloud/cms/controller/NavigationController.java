package com.play001.cloud.cms.controller;


import com.play001.cloud.cms.service.NavigationBarService;
import com.play001.cloud.cms.service.NavigationService;
import com.play001.cloud.support.entity.NavigationBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 导航, 导航栏
 * 一个导航(Navigation)下面对应多个导航栏(NavigationBar)
 */
@Controller
@RequestMapping("/navigation")
public class NavigationController {

    @Autowired
    private NavigationService navigationService;
    @Autowired
    private NavigationBarService navigationBarService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(){
        return "navigation/list";
    }

    /**
     * 更新导航界面
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Integer id, Model model){
        model.addAttribute("navigation",  navigationService.findById(id));
        return "/navigation/update";
    }

    /**
     * 导航栏界面
     */
    @RequestMapping(value = "/barList", method = RequestMethod.GET)
    public String barList(Model model,Integer navigationId){
        if(navigationId != null){
            model.addAttribute("navigation", navigationService.findById(navigationId));
        }
        return "navigation/bar_list";
    }

    /**
     * 导航栏更新界面
     */
    @RequestMapping(value = "/bar/update", method = RequestMethod.GET)
    public String updateBar(Integer id, Model model){
        NavigationBar navigationBar = navigationBarService.findWithNavigation(id);
        model.addAttribute("navigationBar", navigationBar);
        return "/navigation/bar_update";
    }
    /**
     * 创建导航栏
     */
    @RequestMapping(value = "/bar/create", method = RequestMethod.GET)
    public String createBar(Integer navigationId, Model model){
        model.addAttribute("navigation",  navigationService.findById(navigationId));
        return "/navigation/bar_create";
    }
}
