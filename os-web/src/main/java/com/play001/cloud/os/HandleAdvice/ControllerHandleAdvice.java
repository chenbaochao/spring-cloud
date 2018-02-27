package com.play001.cloud.os.HandleAdvice;


import com.play001.cloud.os.controller.*;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常统一返回
 * 此拦截器针对的是@Controller注解
 */
//@ControllerAdvice(assignableTypes = {UserController.class, ProductController.class, UserController.class, OrderController.class, CartController.class,
//        AddressController.class, CategoryController.class})
@ControllerAdvice(basePackages = "com.play001.cloud.os.controller")
public class ControllerHandleAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public String handler(HttpServletRequest request, HttpServletResponse response, Exception e, Model model){
        e.printStackTrace();
        logger.info("ControllerHandleAdvice拦截到异常:"+e.getMessage());
        ResponseEntity<String> responseEntityMsg = new ResponseEntity<>();
        responseEntityMsg.setStatus(ResponseEntity.ERROR);
        if(e instanceof IException){
            model.addAttribute("message", e.getMessage());
        }else{
            model.addAttribute("message", "出错啦");
        }
        return "common/message";
    }
}
