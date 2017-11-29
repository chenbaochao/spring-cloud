package com.play001.cloud.userapi.service;

import com.play001.cloud.userapi.entity.User;
import com.play001.cloud.userapi.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;

    //@Autowired
   // RedisTemplate<Object, Object> redisTemplate;

    public String login(String username, String password) throws Exception {
            String token;
            User user = userMapper.findByUsername(username);
            if(user != null && user.getPassword().equals(password)){
                token = UUID.randomUUID().toString();
                //Credential userCredential = new Credential();
                //userCredential.setId(user.getId());
                //userCredential.setToken(token);
                //redisTemplate.boundHashOps("token").put(user.getId(),token);
                logger.info("用户:"+user.getUsername()+"登陆成功,token="+token);
            }else{
                throw new Exception("用户名或密码错误");
            }
            return token;
    }

}
