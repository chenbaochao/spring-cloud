package com.play001.cloud.os.service;


import com.play001.cloud.common.entity.User;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface UserService {

    /**
     * 获取jwt
     */
    @RequestMapping(value = "/user/getCredential", method = RequestMethod.GET)
    Response<String> getCredential(@RequestParam("key") String key,
                                @RequestParam("password") String password,
                                @RequestParam("expiryDate") Long expiryDate);

    /**
     * 获取验证码数据
     */
    @RequestMapping(value = "/user/captcha", method = RequestMethod.GET)
    Response<byte[]> getCaptcha();

    /**
     * 注册
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    Response<String> register(@RequestBody User user,
                      @RequestParam("code") String code,
                      @RequestHeader("registerCookie") String cookie);

    /**
     * 获取用户信息
     */
    @RequestMapping(value = "/user/getInfo", method = RequestMethod.GET)
    Response<User> getInfo(@RequestHeader("userJwt")String userJwt);
}
