package com.play001.cloud.product.api;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.play001.cloud")
@MapperScan(basePackages = "com.play001.cloud")
public class Application {
    public static void  main(String []args){
        SpringApplication.run(Application.class, args);
    }
}
