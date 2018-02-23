package com.play001.cloud.os.service;

import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.User;
import com.play001.cloud.os.mapper.UserMapper;
import com.play001.cloud.support.entity.user.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public ResponseEntity<String> getCredential(String username, String password, Long expiryDate) throws IException {
        ResponseEntity<String> responseEntity = userMapper.getCredential(username, password, expiryDate);
        if(responseEntity.getStatus().equals(ResponseEntity.ERROR)) throw new IException(responseEntity.getErrMsg());
        return responseEntity;
    }


    public void setCaptcha(HttpServletResponse response) throws Exception {
        ResponseEntity<byte[]> responseEntityMsg = userMapper.getCaptcha();
        if(!ResponseEntity.SUCCESS.equals(responseEntityMsg.getStatus())){
            throw new IException(responseEntityMsg.getErrMsg());
        }
        byte[] data = responseEntityMsg.getMessage();
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
    public ResponseEntity register(User user, String code, String registerCookie){
        if(registerCookie == null || registerCookie.length() < 1){
            return new ResponseEntity(ResponseEntity.ERROR, "验证码无效, 请刷新验证码");
        }
        return userMapper.register(user, code, registerCookie);
    }

    /**
     * 获取用户基本信息
     */
    public User getInfo(String userJwt) throws IException {
        ResponseEntity<User> responseEntity = userMapper.getInfo(userJwt);
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())) throw new IException(responseEntity.getErrMsg());
        return responseEntity.getMessage();
    }

}
