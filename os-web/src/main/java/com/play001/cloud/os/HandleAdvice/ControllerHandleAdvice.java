package com.play001.cloud.os.HandleAdvice;


import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Response;
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
@ControllerAdvice(assignableTypes = {UserController.class, ProductController.class, UserController.class})
public class ControllerHandleAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public String handler(HttpServletRequest request, HttpServletResponse response, Exception e, Model model){
        e.printStackTrace();
        logger.info("ControllerHandleAdvice拦截到异常:"+e.getMessage());
        Response<String> responseMsg = new Response<>();
        responseMsg.setStatus(Response.ERROR);
        if(e instanceof IException){
            responseMsg.setErrMsg(e.getMessage());
        }else{
            responseMsg.setErrMsg("出错啦");
        }
        model.addAttribute("response", responseMsg);
        return "message";
    }
}
