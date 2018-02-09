package com.play001.cloud.user.api.mapper;

import com.play001.cloud.support.entity.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper{

    @Select("select distinct id, username, password from os_user where username = #{key} or telephone = #{key} or email=#{key}")
    User findByKey(String key);

    @Select("select id, username, profile_photo as profilePhoto, telephone, email, created_time as createdTime from os_user where id = #{id}")
    User findById(Long id);

    @Insert("insert into jd_user(username, password, telephone, email, created_time, updated_time) values(" +
            "#{username}, #{password}, #{telephone}, #{email}, #{createdTime}, #{updatedTime})")
    void insert(User user);

    @Select("select count(id) from jd_user where username = #{username} or telephone = #{telephone} or email = #{email}")
    Integer countByUserInfo(User User);
}
