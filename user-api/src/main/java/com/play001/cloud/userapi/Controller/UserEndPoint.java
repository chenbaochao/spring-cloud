package com.play001.cloud.userapi.Controller;

import com.play001.cloud.userapi.entity.User;
import com.play001.cloud.userapi.service.UserService;
import com.play001.cloud.userapi.entity.Response;
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
    public void register(HttpServletResponse response) throws IOException {
        userService.createCaptcha(response);
    }

}
