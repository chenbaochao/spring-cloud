package com.play001.cloud.userapi.mapper;

import com.play001.cloud.userapi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper{

    @Select("select id, username, password, phone, email, created_time, updated_time from jd_user where username = #{username}")
    User findByUsername(String username);
}
