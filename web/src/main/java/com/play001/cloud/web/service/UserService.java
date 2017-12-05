package com.play001.cloud.web.service;


import com.play001.cloud.web.entity.User;
import com.play001.cloud.web.response.LoginResponse;
import com.play001.cloud.web.response.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient("ZUUL")
public interface UserService {

    @RequestMapping(value = "/credential/getCredential", method = RequestMethod.GET)
    LoginResponse getCredential(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("expiryDate") Long expiryDate);

    @RequestMapping(value = "/user/captcha", method = RequestMethod.GET)
    byte[] captcha();

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    Response register(@RequestBody User user,
                      @RequestParam("code") String code,
                      @RequestHeader("registerCookie") String cookie);

}
