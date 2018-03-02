package com.play001.cloud.cms.mapper;

import com.play001.cloud.support.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("select id, username, email, telephone from os_user where id = #{id}")
    User findById(Long id);
}
