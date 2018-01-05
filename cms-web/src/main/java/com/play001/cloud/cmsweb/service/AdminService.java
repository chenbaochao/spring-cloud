package com.play001.cloud.cmsweb.service;

import com.play001.cloud.cmsweb.entity.Admin;
import com.play001.cloud.cmsweb.mapper.AdminMapper;
import com.play001.cloud.common.entity.IException;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.zip.GZIPOutputStream;


@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public void  create(Admin admin ){

    }

    /**
     * 登陆
     */
    public void login(String username, String password, String captchaCode, HttpSession session) throws IException {
        /** 转换为小写,session里面存储的已经是小写的验证码了 */
        captchaCode = captchaCode.toLowerCase();
        String trueCode = session.getAttribute("loginCaptchaCode").toString();
        if(trueCode == null || !trueCode.equals(captchaCode)) throw new IException("验证码错误");
        //验证码通过后必须将让验证码失效,否者会有漏洞
        session.setAttribute("loginCaptchaCode", null);
        //md5加密
        password = MD5Encoder.encode(password.getBytes());
        Admin amdin = adminMapper.findByUsername(username);

        if(amdin == null || !amdin.getPassword().equals(password)) throw new IException("用户名或密码错误");
        //登陆成功后,将用户的权限信息读出来放在session中

    }
    public void findByUsername(){
        Admin admin = adminMapper.findByUsername("admin");
        System.out.println("test");
    }
}
