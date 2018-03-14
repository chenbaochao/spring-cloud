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
@RequestMapping(value="/administrator/loginLog")
public class AdminLoginLogController {

    /**
     * 登陆日志界面
     */
    @PermissionCode
    @RequestMapping(value = "/list/view", method = RequestMethod.GET)
    public String loginLog(Integer adminId, Model model)  {
        model.addAttribute("adminId", adminId);
        return "admin/login_log";
    }


}
