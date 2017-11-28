package com.play001.cloud.webapi.service;

import com.play001.cloud.webapi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;



    public String login(String username, String password) throws Exception {
            String token = null;
            redisTemplate.boundHashOps("token").put(1,"tokenvalue");
            token =  (String)redisTemplate.boundHashOps("token").get(1);
            System.out.println(token);
            /*
            User user = userMapper.findByUsername(username);

            if(user.getPassword().equals(password)){

            }else{
                throw new Exception("用户名或密码错误");
            }*/
            return token;
    }

}
