package com.play001.cloud.webapi.Controller;

import com.play001.cloud.webapi.response.LoginResponse;
import com.play001.cloud.webapi.response.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserEndPoint {

    /**
     * 登陆成功,生成token并返回
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(String username, String password){
        LoginResponse response = new LoginResponse();
        try {

        }catch (Exception e){
            response.setStatus(Response.ERROR);
            response.setErrMsg(e.getMessage());
        }
        return response;
    }
}
