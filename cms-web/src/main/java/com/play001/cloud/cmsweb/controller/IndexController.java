package com.play001.cloud.cmsweb.controller;

import com.play001.cloud.cmsweb.service.AdminService;
import com.play001.cloud.common.util.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class IndexController {

    @Autowired
    private AdminService adminService;

    /**
     * 验证码
     * @param model 验证码的用途 如login,获取时用model+CaptchaCode获取,如loginCaptchaCode
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletResponse response, HttpSession session,String model) throws IOException {
        if(model != null && model.length() > 0){
            /** 需要注意的是这里的captchaCode是全小写的 */
            String captchaCode = Captcha.randCaptchaCode();
            session.setAttribute(model+"CaptchaCode", captchaCode);
            BufferedImage bi = Captcha.createCaptchaImg(captchaCode);
            ImageIO.write(bi, "JPG", response.getOutputStream());
        }
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void index(){
        adminService.findByUsername();
    }
}
