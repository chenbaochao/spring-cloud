package com.play001.cloud.web.controller;


import com.play001.cloud.common.entity.User;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.web.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 所有api接口放在这里,便于扑捉全局异常,并返回错误信息
 */
@RestController
public class ApiController {

    @Autowired
    private UserServiceImpl userService;

    @Value("${credential.expiryDate}")
    private Long expiryDate;//登陆口令有效期

    /**
     * 登陆
     * @param key 用户名/手机/邮箱
     * @param password 密码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/doLogin", method = RequestMethod.POST)
    public Response doLogin(String key, String password, HttpServletResponse response) throws Exception {
        if(key == null || password == null){
            return new Response(Response.ERROR, "用户名或密码为空");
        }
        Response<String> responseMsg  = userService.getCredential(key, password, expiryDate);
        //成功登陆后,要设置cookie,这样浏览器每次请求都会带上口令cookie
        response.addCookie(new Cookie("credential", responseMsg.getMessage()));
        return responseMsg;
    }

    /**
     * 注册
     * @param user 用户实体
     * @param code 验证码
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/doRegister", method = RequestMethod.POST)
    public Response doRegister(User user, String code, HttpServletRequest request){
        return  userService.register(user, code, request);
    }
}
