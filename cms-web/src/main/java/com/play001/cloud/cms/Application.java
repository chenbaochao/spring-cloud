package com.play001.cloud.cms;

import com.play001.cloud.support.util.SpringContextUtils;
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
        SpringApplication.run(Application.class, args);
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
