package com.play001.cloud.support.interceptor;


import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.util.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserPermissionHandler extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        ResponseEntity<String> errResponseEntity = new ResponseEntity<>();
        errResponseEntity.setStatus(ResponseEntity.SUCCESS);
        errResponseEntity.setErrMsg("请先登录");
        //处理Permission Annotation，实现方法级权限控制
        //HandlerMethod 需要对应Jar包的位置，否则会一直为false
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
             /*
             * 1、确认当前的controller是否需要进行权限判定，如果需要则进行验证。
             * 2、当controller不需要验证，则验证当前的方法是否需要权限验证，需要则进行验证，不需要则跳出
             * */
            //获取方法注解，方法检查是否需要验证权限控制
            UserPermissionVerify permission = handlerMethod.getMethod().getAnnotation(UserPermissionVerify.class);
            if (permission != null) //需要验证权限
            {
                String jwt = request.getHeader("userCredential");
                if(!JwtUtil.verify(jwt)){
                    response.getWriter().print(errResponseEntity);
                    return false;
                }
            }
        }

        return super.preHandle(request, response, handler);
    }

}
