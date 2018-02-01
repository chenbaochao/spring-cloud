package com.play001.cloud.cms.controller;

import com.play001.cloud.common.util.Captcha;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class CommonController {


    /**
     * 验证码
     * @param model 验证码的用途 如login,获取时用model+CaptchaCode获取,如loginCaptchaCode
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletResponse response, HttpSession session,String model) throws IOException {
        if(model != null && model.length() > 0){
            String captchaCode = Captcha.randCaptchaCode();
            /* 需要注意的是这里存入的captchaCode是全小写的 */
            session.setAttribute(model+"CaptchaCode", captchaCode.toLowerCase());
            BufferedImage bi = Captcha.createCaptchaImg(captchaCode);
            ImageIO.write(bi, "JPG", response.getOutputStream());
        }
    }
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String message(Model model, String message){
        message = message == null?"":message;
        model.addAttribute("message", message);
        return "common/message";
    }
}
