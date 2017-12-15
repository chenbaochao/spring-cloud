package com.play001.cloud.web.controller;


import com.play001.cloud.web.entity.User;
import com.play001.cloud.web.response.Response;
import com.play001.cloud.web.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @Value("${credential.expiryDate}")
    private Long expiryDate;//有效期
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "user/login";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "user/register";
    }
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletResponse response) throws Exception{
        userService.setCaptcha(response);
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public Response doLogin(String key, String password, HttpServletResponse response) throws Exception {
        if(key == null || password == null){
            return new Response(Response.ERROR, "用户名或密码为空");
        }
        Response<String> responseMsg  = userService.getCredential(key, password, expiryDate);
        //成功登陆后,要设置cookie,这样浏览器每次请求都会带上口令cookie
        response.addCookie(new Cookie("credential", responseMsg.getMessage()));
        return responseMsg;
    }
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    @ResponseBody
    public Response doRegister(User user, String code, HttpServletRequest request){
        return  userService.register(user, code, request);
    }
}
