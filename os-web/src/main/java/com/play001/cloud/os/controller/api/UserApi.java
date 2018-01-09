package com.play001.cloud.os.controller.api;

import com.play001.cloud.common.entity.Response;
import com.play001.cloud.common.entity.User;
import com.play001.cloud.os.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/user")
@RestController
public class UserApi {

    @Autowired
    private UserServiceImpl userService;

    @Value("${credential.expiryDate}")
    private Long expiryDate;//登陆口令有效期

    /**
     * 登陆
     * @param key 用户名/手机/邮箱
     * @param password 密码
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public Response doLogin(String key, String password, HttpServletResponse response) throws Exception {
        if(key == null || password == null){
            return new Response(Response.ERROR, "用户名或密码为空");
        }
        Response<String> responseMsg  = userService.getCredential(key, password, expiryDate);
        //成功登陆后,要设置cookie,这样浏览器每次请求都会带上口令cookie
        Cookie cookie = new Cookie("userJwt", responseMsg.getMessage());
        cookie.setPath("/");
        response.addCookie(cookie);
        return responseMsg;
    }

    /**
     * 注册
     * @param user 用户实体
     * @param code 验证码
     */
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public Response doRegister(User user, String code, @CookieValue("registerCookie")String registerCookie){
        return  userService.register(user, code, registerCookie);
    }
}
