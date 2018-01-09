package com.play001.cloud.os.service.impl;

import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.User;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.os.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Service
public class UserServiceImpl {

    @Autowired
    private UserService userService;

    public Response<String> getCredential(String username, String password, Long expiryDate) throws IException {
        Response<String> response = userService.getCredential(username, password, expiryDate);
        if(response.getStatus().equals(Response.ERROR)) throw new IException(response.getErrMsg());
        return response;
    }


    public void setCaptcha(HttpServletResponse response) throws Exception {
        Response<byte[]> responseMsg = userService.getCaptcha();
        if(!Response.SUCCESS.equals(responseMsg.getStatus())){
            throw new IException(responseMsg.getErrMsg());
        }
        byte[] data = responseMsg.getMessage();
        byte byteCookie[] = new byte[36];
        //获得输出流,将图片输出并设置cookie
        OutputStream os = response.getOutputStream();
        for(int i=0; i< data.length-36;i++){//除开后36位,前面为验证码图片
            os.write(data[i]);
        }
        //后36位为验证码的cookie,post注册时需要带上
        for(int i = data.length-36, j=0;i< data.length;i++, j++){
            byteCookie[j] = data[i];
        }
        String value = new String(byteCookie);
        response.addCookie(new Cookie("registerCookie", value));
        os.flush();
        os.close();
    }

    /**
     * 注册
     * @param user 用户信息
     * @param code 验证码
     * @param registerCookie 确认验证码是否正确的cookie
     */
    public Response register(User user, String code, String registerCookie){
        if(registerCookie == null || registerCookie.length() < 1){
            return new Response(Response.ERROR, "验证码无效, 请刷新验证码");
        }
        return userService.register(user, code, registerCookie);
    }

    /**
     * 获取用户基本信息
     */
    public User getInfo(String userJwt) throws IException {
        Response<User> response = userService.getInfo(userJwt);
        if(Response.ERROR.equals(response.getStatus())) throw new IException(response.getErrMsg());
        return response.getMessage();
    }
}
