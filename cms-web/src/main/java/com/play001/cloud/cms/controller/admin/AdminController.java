package com.play001.cloud.cms.controller.admin;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.entity.Admin;
import com.play001.cloud.cms.entity.AdminSessionData;
import com.play001.cloud.cms.entity.Role;
import com.play001.cloud.cms.service.AdminService;
import com.play001.cloud.cms.service.MenuService;
import com.play001.cloud.cms.service.RoleService;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value="/administrator")
public class AdminController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private AdminService adminService;



    @PermissionCode("admin_create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model ) {
        model.addAttribute("roles", roleService.findAll());
        return "administrator/administrator_create";
    }
    @PermissionCode("admin_view")
    @RequestMapping(value = "/list/view", method = RequestMethod.GET)
    public String list(HttpSession session) {
        return "administrator/administrator_list";
    }




    /**
     * 修改信息页面
     */
    @PermissionCode
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, Integer id) throws IException {
        if(id == null) throw new IException("参数错误");
        List<Role> roles = roleService.findAll();
        Admin admin = adminService.findById(id);
        model.addAttribute("admin", admin);
        model.addAttribute("roles", roles);
        return "administrator/administrator_update";
    }


    /**
     * 个人信息界面
     */
    @PermissionCode
    @RequestMapping(value = "/self", method = RequestMethod.GET)
    public String info(Model model, HttpSession session){
        AdminSessionData adminSessionData  = (AdminSessionData)session.getAttribute("admin");
        model.addAttribute("admin",adminService.findById(adminSessionData.getId()));
        return "administrator/administrator_self_info";
    }
    /**
     * 修改头像界面
     */
    @PermissionCode
    @RequestMapping(value = "/avatar", method = RequestMethod.GET)
    public String avatar(HttpSession session, Model model){
        AdminSessionData adminSessionData  = (AdminSessionData)session.getAttribute("admin");
        model.addAttribute("admin",adminService.findById(adminSessionData.getId()));
        return "administrator/administrator_self_avatar";
    }

}
