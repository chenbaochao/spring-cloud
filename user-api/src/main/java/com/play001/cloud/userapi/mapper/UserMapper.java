package com.play001.cloud.userapi.mapper;

import com.play001.cloud.common.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper{

    @Select("select id, username, password, telephone, email, created_time as createdTime, " +
            "updated_time as updatedTime from jd_user where username = #{username}")
    User findByUsername(String username);

    @Insert("insert into jd_user(username, password, telephone, email, created_time, updated_time) values(" +
            "#{username}, #{password}, #{telephone}, #{email}, #{createdTime}, #{updatedTime})")
    void insert(User user);

    @Select("select count(id) from jd_user where username = #{username} or telephone = #{telephone} or email = #{email}")
    Integer countByUserInfo(User User);
}
