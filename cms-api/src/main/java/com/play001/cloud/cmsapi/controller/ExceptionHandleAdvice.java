package com.play001.cloud.cmsapi.controller;


import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 异常统一返回
 */
@RestControllerAdvice
public class ExceptionHandleAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public Response<String> handler(HttpServletRequest request, HttpServletResponse response, Exception e){
        e.printStackTrace();
        logger.info("拦截到异常:"+e.getMessage());
        if(e instanceof IException){
            return new Response<>(Response.ERROR, e.getMessage());
        }else{
            return new Response<>(Response.ERROR, "未知异常");
        }


    }
}
