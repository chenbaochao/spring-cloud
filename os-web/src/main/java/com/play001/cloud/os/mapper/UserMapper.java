package com.play001.cloud.os.mapper;


import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import com.play001.cloud.os.mapper.UserMapper.UserFallback;

@FeignClient(name = "ZUUL", fallback = UserFallback.class)
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
    ResponseEntity<Byte[]> getCaptcha();

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
    @Component
    static class UserFallback implements UserMapper{
        @Override
        public ResponseEntity<String> getCredential(String key, String password, Long expiryDate) {
            return new ResponseEntity<String>().setErrMsg("网络繁忙");
        }

        @Override
        public String test() {
            return null;
        }

        @Override
        public ResponseEntity<Byte[]> getCaptcha() {
            return new ResponseEntity<Byte[]>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<String> register(User user, String code, String cookie) {
            return new ResponseEntity<String>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<User> getInfo(String userJwt) {
            return new ResponseEntity<User>().setErrMsg("网络繁忙");
        }
    }
}
