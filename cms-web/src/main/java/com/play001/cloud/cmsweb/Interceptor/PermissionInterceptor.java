package com.play001.cloud.cmsweb.Interceptor;

import com.play001.cloud.cmsweb.entity.AdminSessionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 权限验证拦截器
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter{

    Logger logger = LoggerFactory.getLogger(this.getClass());
    public PermissionInterceptor() {
        super();
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("uri=="+request.getRequestURI());
        //登陆页面不过处理
        if(request.getRequestURI().equals("/admin/login")) return true;

        HttpSession session = request.getSession();
        AdminSessionData adminSessionData = (AdminSessionData) session.getAttribute("admin");
        if(adminSessionData == null){
            logger.info("未登录");

            //重定向到登陆页面
            //response.sendRedirect("/admin/login");
           // return false;
        }else{
            logger.info("已登录");
        }

        return super.preHandle(request, response, handler);
    }


}
