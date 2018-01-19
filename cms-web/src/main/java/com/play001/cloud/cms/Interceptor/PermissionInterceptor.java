package com.play001.cloud.cms.Interceptor;

import com.play001.cloud.cms.entity.AdminSessionData;
import com.play001.cloud.common.entity.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * 权限验证拦截器
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String NOT_LOGIN_MESSAGE = new Response<Integer>().setErrMsg("你还没有登陆!").toJson();
    private final String NO_PERMISSION_MESSAGE = new Response<Integer>().setErrMsg("你没有权限进行此操作!").toJson();

    public PermissionInterceptor() {
        super();
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登陆页面和获取验证码不做验证
        String uri = request.getRequestURI();
        if("/admin/login".equals(uri) || "/captcha".equals(uri)) return true;
        HttpSession session = request.getSession();
        AdminSessionData adminSessionData = (AdminSessionData) session.getAttribute("admin");
        //验证是否通过标识符
        String result;
        if(adminSessionData == null){
            result = "Not Logged In";
            logger.info("未登录,URI="+ uri);
        }else {
            //已登陆,进行权限验证
            String menuCode = uri.replace("/", "_").substring(1);
            Boolean flag = adminSessionData.getPermission().get(menuCode);
            if (flag == null || flag) {
                //logger.warn("出错了,URI不存在:URI="+uri+", menuCode="+menuCode);
                /*
                 * URI不存在的都放行,避免出现太多的"例外",而写过多的if
                 */
                result = "Pass";
                logger.info("验证通过, username=" + adminSessionData.getUsername() + ",URI=" + uri);
            } else {
                result = "No Permission";
                logger.info("没有权限进行此操作, username=" + adminSessionData.getUsername() + ",URI=" + uri);
            }
        }
        //验证不通过
        if(!result.equals("Pass") && false){
            /*
             * 判断是@restController还是controller
             * 如果是@restController则返回json格式的错误信息
             * 如果是@controller则跳转到登陆页面/或者错误提示页面
             */

            HandlerMethod handlerMethod = (HandlerMethod)handler;
            /*
             * 由于@restController和@controller是注解在类上的,所以要先获取bean然后再获取注解
             */
            if(handlerMethod.getBeanType().getAnnotation(RestController.class) != null){
                logger.info("RestController方法");
                response.setCharacterEncoding("UTF-8");
                if(result.equals("Not Logged In")){
                    response.getWriter().print(NOT_LOGIN_MESSAGE);
                }else{
                    response.getWriter().print(NO_PERMISSION_MESSAGE);
                }
            }
            if(handlerMethod.getBeanType().getAnnotation(Controller.class) != null){
                logger.info("Controller方法");
                //未登录重定向到登陆页面, 其它情况重定向到错误页面
                if(result.equals("Not Logged In")){
                    response.sendRedirect("/admin/login");
                }else{
                    response.sendRedirect("/message?message="+ URLEncoder.encode("你没有权限进行此操作", "UTF-8"));
                }
            }
            return false;
        }
        return super.preHandle(request, response, handler);
    }


}
