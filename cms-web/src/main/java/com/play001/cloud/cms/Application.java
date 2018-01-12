package com.play001.cloud.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan(basePackages = {"com.play001.cloud"})
public class Application {
    public static void main(String []args){
        SpringApplication.run(Application.class, args);
    }

}
