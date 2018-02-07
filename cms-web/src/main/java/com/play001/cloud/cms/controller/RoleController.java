package com.play001.cloud.cms.controller;

import com.google.gson.Gson;
import com.play001.cloud.cms.entity.Role;
import com.play001.cloud.cms.service.MenuService;
import com.play001.cloud.cms.service.RoleService;
import com.play001.cloud.support.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    private MenuService menuService;
    private RoleService roleService;

    @Autowired
    public RoleController(MenuService menuService, RoleService roleService) {
        this.menuService = menuService;
        this.roleService = roleService;
    }

    /**
     * 创建role
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model){
        List<Menu> menus = menuService.getAllMenus();
        model.addAttribute("menusJson", new Gson().toJson(menus));
        return "role/role_create";
    }
    //列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(){
        return "role/role_list";
    }
    //更新
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, Integer id){
        Role role = roleService.findById(id);
        List<Menu> menus = menuService.getAllMenus();
        model.addAttribute("role",role);
        model.addAttribute("permissionsJson",new Gson().toJson(role.getPermissions()));
        model.addAttribute("menusJson", new Gson().toJson(menus));
        return "role/role_update";
    }
}
