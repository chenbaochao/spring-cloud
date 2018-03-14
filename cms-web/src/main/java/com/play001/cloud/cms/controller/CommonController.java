package com.play001.cloud.cms.controller;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.entity.AdminSessionData;
import com.play001.cloud.cms.service.AdminService;
import com.play001.cloud.cms.service.MenuService;
import com.play001.cloud.cms.service.RoleService;
import com.play001.cloud.support.entity.Menu;
import com.play001.cloud.support.util.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Controller
public class CommonController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private AdminService adminService;

    /**
     * 验证码
     * @param model 验证码的用途 如login,获取时用model+CaptchaCode获取,如loginCaptchaCode
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletResponse response, HttpSession session,String model) throws IOException {
        if(model != null && model.length() > 0){
            String captchaCode = Captcha.randCaptchaCode();
            /* 需要注意的是这里存入的captchaCode是全小写的 */
            session.setAttribute(model+"CaptchaCode", captchaCode.toLowerCase());
            BufferedImage bi = Captcha.createCaptchaImg(captchaCode);
            ImageIO.write(bi, "JPG", response.getOutputStream());
        }
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String message(Model model, String message){
        message = message == null?"":message;
        model.addAttribute("message", message);
        return "common/message";
    }

    /**
     * 后台首页
     */
    @PermissionCode
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, HttpSession session ){
        AdminSessionData adminSessionData = (AdminSessionData)session.getAttribute("admin");
        List<Menu> menus = menuService.getMenusByRoleId(adminSessionData.getRole().getId());
        model.addAttribute("admin", adminService.findById(adminSessionData.getId()));
        model.addAttribute("menus", menus);
        return "index/main";
    }
    /**
     * 登陆面面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "index/login";
    }

    /**
     * 首页
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpSession session, HttpServletResponse response) throws IOException {
        //已登录跳转到后台.未登录跳转到登陆页面
        if(session.getAttribute("admin") != null){
            response.sendRedirect("/index");
        }else{
            response.sendRedirect("/login");
        }
        return null;
    }
    //注销
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.setAttribute("admin", null);
        response.sendRedirect("/login");
    }
}
