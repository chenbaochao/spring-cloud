package com.play001.cloud.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.play001.cloud"})
public class Application {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Application.class);
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public SpringContextUtils init() {
        return new SpringContextUtils();
    }

     /*
    @Bean(name = "test")
    @Autowired
    RedisTemplate<byte[], byte[]> intRedisTemplate(JedisConnectionFactory connectionFactory) {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
    */
}
