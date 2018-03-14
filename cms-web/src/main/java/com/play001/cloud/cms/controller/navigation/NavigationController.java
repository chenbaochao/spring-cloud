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
@RequestMapping("/navigation")
public class NavigationController {

    @Autowired
    private NavigationService navigationService;
    @Autowired
    private NavigationBarService navigationBarService;

    @RequestMapping(value = "/list/view", method = RequestMethod.GET)
    public String list(){
        return "navigation/navigation_list";
    }


    /**
     * 更新导航界面
     */
    @PermissionCode("navigation_update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Integer id, Model model){
        model.addAttribute("navigation",  navigationService.findById(id));
        return "navigation/navigation_update";
    }

}
