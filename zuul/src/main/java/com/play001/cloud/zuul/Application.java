package com.play001.cloud.zuul;

import com.play001.cloud.zuul.filter.SimpleFliter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class Application {

    public static void main(String []args){
        SpringApplication.run(Application.class, args);
    }

    /**
     * 过滤器
     * @return
     */
    @Bean
    public SimpleFliter simpleFliter() {
        return new SimpleFliter();
    }

    /**
     * 容错保护
     * @return
     */
    @Bean
    public ZuulFallbackProvider fallBackProvider() {
        return new DefaultFallback();
    }
}
