package com.play001.cloud.userapi.Controller;

import com.play001.cloud.userapi.service.UserService;
import com.play001.cloud.userapi.response.LoginResponse;
import com.play001.cloud.userapi.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Response test(){
        LoginResponse response = new LoginResponse();
        response.setStatus(Response.SUCCESS);
        response.setErrMsg("1111");
        return response;
    }
}
