package com.play001.cloud.cms.HandleAdvice;


import com.play001.cloud.cms.controller.api.AdminApi;
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
 * 此拦截器针对的是@RestController注解
 */
@RestControllerAdvice(assignableTypes = {AdminApi.class})
public class RestControllerHandleAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public Response<Integer> handler(HttpServletRequest request, HttpServletResponse response, Exception e){
        e.printStackTrace();
        logger.info("拦截到异常:"+e.getMessage());
        Response<Integer> responseMsg = new Response<>(Response.ERROR);
        if(e instanceof IException){
            responseMsg.setErrMsg(e.getMessage());
        }else{
            responseMsg.setErrMsg("操作失败");
        }
        return responseMsg;
    }
}
