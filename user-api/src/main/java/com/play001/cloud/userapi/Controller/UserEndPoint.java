package com.play001.cloud.userapi.Controller;

import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.User;
import com.play001.cloud.common.entity.UserCredential;
import com.play001.cloud.common.util.JwtUtil;
import com.play001.cloud.userapi.service.UserService;
import com.play001.cloud.common.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UserEndPoint {

    @Autowired
    private UserService userService;

    /**
     * 登陆成功,返回令牌
     * @param key 用户名or邮箱or电话号码
     * @param password 密码
     * @param expiryDate 令牌有效时长
     * @return
     */
    @RequestMapping(value = "/getCredential", method = RequestMethod.GET)
    public Response<String> login(String key, String password, Long expiryDate){
        Response<String> response = new Response();
        try {
            response.setStatus(Response.SUCCESS);
            response.setMessage(userService.getCredential(key, password, expiryDate));
        }catch (Exception e){
            response.setStatus(Response.ERROR);
            response.setErrMsg(e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(@Validated @RequestBody User user, BindingResult errResult, String code, @RequestHeader String registerCookie) throws Exception {
        if(errResult.hasErrors()){
           throw new Exception(errResult.getFieldErrors().get(0).getDefaultMessage());
        }
        userService.insert(user, registerCookie, code);
        return new Response(Response.SUCCESS);
    }

    /**
     * 输出流中后36位为验证码的cookie,前面为验证码图片流
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public Response<byte[]> register(HttpServletResponse response) throws IOException {
        return userService.createCaptcha();
    }

    /**
     * 获取用户信息
     * 只能获取自己的信息
     * @throws Exception
     */
    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public Response<User> getInfo(@RequestHeader("userJwt")String userJwt) throws Exception {
        UserCredential userCredential = JwtUtil.getCredentialByJwt(userJwt);
        return new Response<>(userService.getInfo(userCredential.getUserId()));
    }
}
