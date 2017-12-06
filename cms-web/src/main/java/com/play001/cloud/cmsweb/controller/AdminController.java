package com.play001.cloud.cmsweb.controller;

import com.play001.cloud.cmsweb.entity.Menu;
import com.play001.cloud.cmsweb.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

    @Autowired
    private MenuServiceImpl menuService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) throws Exception {
        List<Menu> menus = menuService.getMenus();
        model.addAttribute("menus", menus);
        return "webfront/main";
    }


}
