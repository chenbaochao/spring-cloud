package com.play001.cloud.cmsweb.mapper;

import com.play001.cloud.cmsweb.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户组
 */
@Mapper
public interface RoleMapper {

    /**
     * 检索所有用户组,排除掉root
     */
    @Select("select id, name, status, remarks from cms_role where id != 1")
    List<Role> finAll();

    @Select("select id, name, status, remarks from cms_role where id = #{id} limit 1")
    Role findById(Integer id);
}
