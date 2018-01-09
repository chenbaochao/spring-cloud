package com.play001.cloud.cms.controller.api;


import com.play001.cloud.cms.entity.Admin;
import com.play001.cloud.cms.entity.AdminSessionData;
import com.play001.cloud.cms.service.AdminService;
import com.play001.cloud.cms.service.MenuService;
import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminApi {

    @Autowired
    private MenuService menuService;
    @Autowired
    private AdminService adminService;

    /**
     * 创建管理员
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response<Integer> create(@Validated Admin admin, BindingResult result, HttpSession session) throws IException {
        if(result.hasErrors()) throw new IException(result.getFieldErrors().get(0).getDefaultMessage());
        if(1 == admin.getRole().getId()) throw new IException("不能创建ROOT用户!");
        Admin creator = new Admin();
        //获取创建者信息
        AdminSessionData adminSessionData = (AdminSessionData) session.getAttribute("admin");
        creator.setId(adminSessionData.getId());
        admin.setCreator(creator);
        adminService.create(admin);
        return new Response<>(Response.SUCCESS);
    }

    /**
     * 登陆
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<Integer> login(String username, String password, String captchaCode, HttpSession session) throws IException {
        if(username == null || username.length() < 1 ||
                password == null || password.length() < 1 ||
                captchaCode == null || captchaCode.length() < 1) throw new IException("参数错误");
        adminService.login(username, password, captchaCode, session);
        return new Response<>(Response.SUCCESS);
    }

    /**
     * 获取管理员列表
     * @param offset 开始位置
     * @param limit 条数
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Map<String, Object> getList(Integer offset, Integer limit){
        List<Admin> adminList = adminService.Pagination(offset, limit);
        Map<String, Object> map = new HashMap<>();
        map.put("total", adminList.size());
        map.put("rows", adminList);
        return map;
    }

    /**
     * @param id 要删除的管理员ID, 不能为1,
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response<Integer> delete(Integer id) throws IException {
        if(id == null) throw new IException("ID不能为空");
        adminService.delete(id);
        return new Response<>(Response.SUCCESS);
    }

}
