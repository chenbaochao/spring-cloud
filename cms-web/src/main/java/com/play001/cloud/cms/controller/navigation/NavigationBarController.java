package com.play001.cloud.cms.controller.navigation;


import com.play001.cloud.cms.Interceptor.PermissionCode;
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
@RequestMapping("/navigation/bar")
public class NavigationBarController {

    @Autowired
    private NavigationService navigationService;
    @Autowired
    private NavigationBarService navigationBarService;

    /**
     * 导航栏界面
     */
    @PermissionCode("navigation_view")
    @RequestMapping(value = "/list/view", method = RequestMethod.GET)
    public String barList(Model model,Integer navigationId){
        if(navigationId != null){
            model.addAttribute("navigation", navigationService.findById(navigationId));
        }
        return "navigation/navigation_bar_list";
    }

    /**
     * 导航栏更新界面
     */
    @PermissionCode("navigation_update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateBar(Integer id, Model model){
        NavigationBar navigationBar = navigationBarService.findWithNavigation(id);
        model.addAttribute("navigationBar", navigationBar);
        return "navigation/navigation_bar_update";
    }
    /**
     * 创建导航栏
     */
    @PermissionCode("navigation_create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createBar(Integer navigationId, Model model){
        model.addAttribute("navigation",  navigationService.findById(navigationId));
        return "navigation/navigation_bar_create";
    }
}
