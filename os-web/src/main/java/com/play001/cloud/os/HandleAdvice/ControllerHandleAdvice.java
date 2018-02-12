package com.play001.cloud.os.HandleAdvice;


import com.play001.cloud.os.controller.CartController;
import com.play001.cloud.os.controller.OrderController;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.os.controller.ProductController;
import com.play001.cloud.os.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常统一返回
 * 此拦截器针对的是@Controller注解
 */
@ControllerAdvice(assignableTypes = {UserController.class, ProductController.class, UserController.class, OrderController.class, CartController.class})
public class ControllerHandleAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public String handler(HttpServletRequest request, HttpServletResponse response, Exception e, Model model){
        e.printStackTrace();
        logger.info("ControllerHandleAdvice拦截到异常:"+e.getMessage());
        ResponseEntity<String> responseEntityMsg = new ResponseEntity<>();
        responseEntityMsg.setStatus(ResponseEntity.ERROR);
        if(e instanceof IException){
            responseEntityMsg.setErrMsg(e.getMessage());
        }else{
            responseEntityMsg.setErrMsg("出错啦");
        }
        model.addAttribute("response", responseEntityMsg);
        return "message";
    }
}
