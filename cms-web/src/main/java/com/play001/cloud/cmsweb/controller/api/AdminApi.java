package com.play001.cloud.cmsweb.controller.api;

import com.play001.cloud.cmsweb.entity.Admin;
import com.play001.cloud.cmsweb.entity.AdminSessionData;
import com.play001.cloud.cmsweb.service.AdminService;
import com.play001.cloud.cmsweb.service.MenuService;
import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminApi {

    @Autowired
    private MenuService menuService;
    @Autowired
    private AdminService adminService;

    /**
     * 创建用户
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response<Integer> create(@Validated Admin admin, BindingResult result, HttpSession session) throws IException {
        if(result.hasErrors()) throw new IException(result.getFieldErrors().get(0).getDefaultMessage());
        AdminSessionData adminSessionData = (AdminSessionData) session.getAttribute("admin");
        adminService.create(admin);
        return new Response<>(Response.SUCCESS);
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<Integer> login(String username, String password, String captchaCode, HttpSession session) throws IException {
        if(username == null || username.length() < 1 ||
                password == null || password.length() < 1 ||
                captchaCode == null || captchaCode.length() < 1) throw new IException("参数错误");
        adminService.login(username, password, captchaCode, session);
        return new Response<>(Response.SUCCESS);
    }
}
