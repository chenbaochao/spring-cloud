package com.play001.cloud.web.HandleAdvice;


import com.play001.cloud.common.entity.IException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常统一返回
 * 此拦截器针对的是@RestController注解
 */
@RestControllerAdvice(annotations = RestController.class)
public class RestControllerHandleAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public Map handler(HttpServletRequest request, HttpServletResponse response, Exception e){
        e.printStackTrace();
        logger.info("拦截到异常:"+e.getMessage());
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("status", "ERROR");
        if(e instanceof IException){
            responseMap.put("errmsg", e.getMessage());
        }else{
            responseMap.put("errmsg", "操作失败");
        }
        return responseMap;
    }
}
