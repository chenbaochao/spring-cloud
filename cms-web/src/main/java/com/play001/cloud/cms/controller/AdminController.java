package com.play001.cloud.cms.controller;

import com.play001.cloud.cms.entity.Admin;
import com.play001.cloud.cms.entity.Role;
import com.play001.cloud.cms.service.AdminService;
import com.play001.cloud.cms.service.MenuService;
import com.play001.cloud.cms.service.RoleService;
import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        List<Menu> menus = menuService.getMenus();
        model.addAttribute("menus", menus);

        return "webfront/main";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model ) {
        model.addAttribute("roles", roleService.findAll());
        return "admin/create";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpSession session) {
        return "admin/list";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "admin/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.setAttribute("admin", null);
        response.sendRedirect("/admin/login");
    }

    /**
     * 修改信息页面
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, Integer id) throws IException {
        if(id == null) throw new IException("参数错误");
        List<Role> roles = roleService.findAll();
        Admin admin = adminService.findById(id);
        model.addAttribute("admin", admin);
        model.addAttribute("roles", roles);
        return "admin/update";

    }
}
