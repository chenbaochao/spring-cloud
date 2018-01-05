package com.play001.cloud.cmsweb.controller;

import com.play001.cloud.cmsweb.entity.AdminSessionData;
import com.play001.cloud.cmsweb.service.MenuService;
import com.play001.cloud.cmsweb.service.RoleService;
import com.play001.cloud.common.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        List<Menu> menus = menuService.getMenus();
        model.addAttribute("menus", menus);

        return "webfront/main";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model, HttpSession session) {
        model.addAttribute("roles", roleService.findAll());
        return "admin/create";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpSession session) {
        AdminSessionData adminSessionData = new AdminSessionData();
        HashMap<String, Boolean> t = new HashMap<>();
        t.put("admin_create", true);
        adminSessionData.setPermission(t);
        session.setAttribute("permission", adminSessionData);
        return "admin/list";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "admin/login";
    }
}
