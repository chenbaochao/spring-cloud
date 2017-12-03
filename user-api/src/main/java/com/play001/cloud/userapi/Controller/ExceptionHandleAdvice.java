package com.play001.cloud.userapi.Controller;

import com.play001.cloud.userapi.response.Response;
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
    public Response handler(HttpServletRequest request, HttpServletResponse response, Exception e){
        return new Response(Response.ERROR, e.getMessage());
    }
}
