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

    public void insert(User user) throws Exception {
        if(userMapper.countByUserInfo(user) > 0){
            throw new Exception("用户名已存在");
        }

    }

}
