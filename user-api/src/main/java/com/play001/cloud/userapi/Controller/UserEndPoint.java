package com.play001.cloud.userapi.Controller;

import com.play001.cloud.userapi.entity.User;
import com.play001.cloud.userapi.service.UserService;
import com.play001.cloud.userapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEndPoint {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(User user) throws Exception {
        userService.insert(user);
        return new Response(Response.SUCCESS);
    }

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public Response register(){

        return new Response(Response.SUCCESS);
    }
}
