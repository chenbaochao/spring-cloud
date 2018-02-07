package com.play001.cloud.cms.HandleAdvice;


import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常统一返回
 * 此拦截器针对的是@RestController注解
 */
@RestControllerAdvice(assignableTypes = {RestController.class})
public class RestControllerHandleAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public ResponseEntity<Integer> handler(HttpServletRequest request, HttpServletResponse response, Exception e){
        e.printStackTrace();
        logger.info("拦截到异常:"+e.getMessage());
        ResponseEntity<Integer> responseEntityMsg = new ResponseEntity<>(ResponseEntity.ERROR);
        if(e instanceof IException){
            responseEntityMsg.setErrMsg(e.getMessage());
        }else{
            responseEntityMsg.setErrMsg("操作失败");
        }
        return responseEntityMsg;
    }
}
