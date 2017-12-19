package com.play001.cloud.cmsweb.HandleAdvice;


import com.play001.cloud.common.entity.IException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常统一返回
 * 此拦截器针对的是@RestController注解
 */
@ControllerAdvice(annotations = Controller.class)
public class ControllerHandleAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public String handler(HttpServletRequest request, HttpServletResponse response, Exception e, Model model){
        e.printStackTrace();
        logger.info("拦截到异常:"+e.getMessage());
        model.addAttribute("manJump",true);
        model.addAttribute("url", request.getHeader("referer"));
        if(e instanceof IException){
            model.addAttribute("message",e.getMessage());
        }else{
            model.addAttribute("message","未知错误");
        }
        return "message";
    }
}
