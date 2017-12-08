package com.play001.cloud.credentialapi.mapper;

import com.play001.cloud.credentialapi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select distinct id, username, password from jd_user where username = #{key} or telephone = #{key} or email=#{key}")
    User findByUsername(String key);
}
