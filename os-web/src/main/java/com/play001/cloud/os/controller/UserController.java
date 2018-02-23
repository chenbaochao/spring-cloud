package com.play001.cloud.os.controller;


import com.play001.cloud.os.service.UserAddressService;
import com.play001.cloud.os.service.UserService;

import com.play001.cloud.support.entity.IException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {



    @Autowired
    private UserService userService;

    //登陆页面
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "user/login";
    }
    //注册页面
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "user/register";
    }
    //验证码
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletResponse response) throws Exception{
        userService.setCaptcha(response);
    }
    //用户首页
    @RequestMapping(value = "/portal", method = RequestMethod.GET)
    public String portal(Model model, @CookieValue("userJwt")String userJwt) throws Exception{
        model.addAttribute("user", userService.getInfo(userJwt));
        return "usercenter/portal";
    }



}
