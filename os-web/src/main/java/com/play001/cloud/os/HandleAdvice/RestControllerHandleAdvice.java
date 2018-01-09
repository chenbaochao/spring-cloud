package com.play001.cloud.os.HandleAdvice;


import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.os.controller.api.UserApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常统一返回
 * 此拦截器针对的是@RestController注解
 */
@RestControllerAdvice(assignableTypes = {UserApi.class})
public class RestControllerHandleAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public Response<String> handler(HttpServletRequest request, HttpServletResponse response, Exception e){
        e.printStackTrace();
        logger.info("RestControllerHandleAdvice拦截到异常:"+e.getMessage());
        Response<String> responseMsg = new Response<>();
        responseMsg.setStatus(Response.ERROR);
        if(e instanceof IException){
            responseMsg.setErrMsg(e.getMessage());
        }else{
            responseMsg.setErrMsg("操作失败");
        }
        return responseMsg;
    }
}
