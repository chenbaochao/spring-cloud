package com.play001.cloud.zuul;

import com.play001.cloud.zuul.filter.SimpleFliter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
@RibbonClient(configuration = RibbonConfig.class)
public class Application {

    public static void main(String []args){
        SpringApplication.run(Application.class, args);
    }

    /**
     * 过滤器
     */
    @Bean
    public SimpleFliter simpleFliter() {
        return new SimpleFliter();
    }

    /**
     * 容错保护
     */
    @Bean
    public ZuulFallbackProvider fallBackProvider() {
        return new DefaultFallback();
    }

}
