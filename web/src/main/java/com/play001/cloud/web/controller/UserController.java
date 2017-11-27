package com.play001.cloud.web.controller;


import com.play001.cloud.web.response.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "user/login";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "user/register";
    }


    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public Response doLogin(String username, String password, HttpServletResponse response){

        return new Response(Response.ERROR, "password wrong");
    }

}
