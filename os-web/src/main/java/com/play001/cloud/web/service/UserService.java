package com.play001.cloud.web.service;


import com.play001.cloud.common.entity.User;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.web.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface UserService {

    @RequestMapping(value = "/credential/getCredential", method = RequestMethod.GET)
    Response<String> getCredential(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("expiryDate") Long expiryDate);

    @RequestMapping(value = "/user/captcha", method = RequestMethod.GET)
    Response<byte[]> getCaptcha();

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    Response<String> register(@RequestBody User user,
                      @RequestParam("code") String code,
                      @RequestHeader("registerCookie") String cookie);


}
