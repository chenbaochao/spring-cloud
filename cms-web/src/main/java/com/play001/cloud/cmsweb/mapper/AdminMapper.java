package com.play001.cloud.cmsweb.mapper;

import com.play001.cloud.cmsweb.entity.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;


@Mapper
public interface AdminMapper {


    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "real_name", property = "realName"),
            @Result(property="role",column="role_id",one=@One(select="com.play001.cloud.cmsweb.mapper.RoleMapper.findById"))
    })
    @Select("select id, username, real_name, role_id  from cms_admin where username = #{username} limit 1")
    Admin findByUsername(String username);
}
