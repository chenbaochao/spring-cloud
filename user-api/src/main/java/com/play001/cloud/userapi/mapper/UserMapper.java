package com.play001.cloud.userapi.mapper;

import com.play001.cloud.userapi.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper{

    @Select("select id, username, password, phone, email, created_time as createdTime, " +
            "updated_time as updatedTime from jd_user where username = #{username}")
    User findByUsername(String username);

    @Insert("insert into(username, password, phone, email, created_time, updated_time) values(" +
            "#{username}, #{password}, #{phone}, #{email}, #{createdTime}, #{updatedTime})")
    void insert(User user);

    @Select("select count(id) where username = #{username} or phone = #{phone} or email = #{email}")
    Integer countByUserInfo(User User);
}
