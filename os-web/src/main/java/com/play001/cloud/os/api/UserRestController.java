package com.play001.cloud.os.api;

import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.User;
import com.play001.cloud.os.service.UserService;
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
public class UserRestController {

    @Autowired
    private UserService userService;

    @Value("${credential.expiryDate}")
    private Long expiryDate;//登陆口令有效期


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return null;
    }
    /**
     * 登陆
     * @param key 用户名/手机/邮箱
     * @param password 密码
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ResponseEntity<String> doLogin(String key, String password, HttpServletResponse response) throws Exception {
        if(key == null || password == null){
            return new ResponseEntity<String>().setErrMsg("用户名或密码为空");
        }
        ResponseEntity<String> responseEntityMsg = userService.getCredential(key, password, expiryDate);
        if(responseEntityMsg.getStatus().equals(ResponseEntity.SUCCESS)){
            //成功登陆后,要设置cookie,这样浏览器每次请求都会带上口令cookie
            Cookie cookie = new Cookie("userJwt", responseEntityMsg.getMessage());
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return responseEntityMsg;
    }

    /**
     * 注册
     * @param user 用户实体
     * @param code 验证码
     */
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public ResponseEntity doRegister(User user, String code, @CookieValue("registerCookie")String registerCookie){
        return  userService.register(user, code, registerCookie);
    }
}
