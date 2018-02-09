package com.play001.cloud.user.api.controller;

import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.User;
import com.play001.cloud.support.entity.user.UserCredential;
import com.play001.cloud.support.util.JwtUtil;
import com.play001.cloud.user.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UserEndPoint {

    @Autowired
    private UserService userService;

    @Value("${server.port}")
    private  String port;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        String str = "test="+port;
        return str;
    }

    /**
     * 登陆成功,返回令牌
     * @param key 用户名or邮箱or电话号码
     * @param password 密码
     * @param expiryDate 令牌有效时长
     */
    @RequestMapping(value = "/getCredential", method = RequestMethod.GET)
    public ResponseEntity<String> login(String key, String password, Long expiryDate){
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        try {
            responseEntity.setStatus(ResponseEntity.SUCCESS);
            responseEntity.setMessage(userService.getCredential(key, password, expiryDate));
        }catch (Exception e){
            responseEntity.setStatus(ResponseEntity.ERROR);
            responseEntity.setErrMsg(e.getMessage());
        }
        return responseEntity;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@Validated @RequestBody User user, BindingResult errResult, String code, @RequestHeader String registerCookie) throws Exception {
        if(errResult.hasErrors()){
           throw new Exception(errResult.getFieldErrors().get(0).getDefaultMessage());
        }
        userService.insert(user, registerCookie, code);
        return new ResponseEntity(ResponseEntity.SUCCESS);
    }

    /**
     * 输出流中后36位为验证码的cookie,前面为验证码图片流
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public ResponseEntity<byte[]> register() throws IOException {
        return userService.createCaptcha();
    }

    /**
     * 获取用户信息
     * 只能获取自己的信息
     */
    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public ResponseEntity<User> getInfo(@RequestHeader("userJwt")String userJwt) throws Exception {
        UserCredential userCredential = JwtUtil.getCredentialByJwt(userJwt);
        return new ResponseEntity<>(userService.getInfo(userCredential.getUserId()));
    }
}
