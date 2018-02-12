package com.play001.cloud.os.mapper;


import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.User;
import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface UserMapper {

    /**
     * 获取jwt
     */
    @RequestMapping(value = "/user/getCredential", method = RequestMethod.GET)
    ResponseEntity<String> getCredential(@RequestParam("key") String key,
                                         @RequestParam("password") String password,
                                         @RequestParam("expiryDate") Long expiryDate);

    @RequestMapping(value = "/user/test", method = RequestMethod.GET)
    String test();
    /**
     * 获取验证码数据
     */
    @RequestMapping(value = "/user/captcha", method = RequestMethod.GET)
    ResponseEntity<byte[]> getCaptcha();

    /**
     * 注册
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    ResponseEntity<String> register(@RequestBody User user,
                                    @RequestParam("code") String code,
                                    @RequestHeader("registerCookie") String cookie);

    /**
     * 获取用户信息
     */
    @RequestMapping(value = "/user/getInfo", method = RequestMethod.GET)
    ResponseEntity<User> getInfo(@RequestHeader("userJwt")String userJwt);
}
