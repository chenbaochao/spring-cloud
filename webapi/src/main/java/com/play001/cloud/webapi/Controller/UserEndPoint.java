package com.play001.cloud.webapi.Controller;

import com.play001.cloud.webapi.service.UserService;
import com.play001.cloud.webapi.response.LoginResponse;
import com.play001.cloud.webapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserEndPoint {

    @Autowired
    private UserService userService;
    /**
     * 登陆成功,生成token并返回
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Response login(String username, String password){
        LoginResponse response = new LoginResponse();
        try {
            userService.login(username, password);
        }catch (Exception e){
            response.setStatus(Response.ERROR);
            response.setErrMsg(e.getMessage());
        }
        return response;
    }
}
