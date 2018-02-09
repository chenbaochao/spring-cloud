package com.play001.cloud.user.api;

import com.play001.cloud.support.interceptor.UserPermissionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserPermissionHandler()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
