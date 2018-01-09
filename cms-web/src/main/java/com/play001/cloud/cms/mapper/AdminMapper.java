package com.play001.cloud.cms.mapper;

import com.play001.cloud.cms.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


@Mapper
public interface AdminMapper {


    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "real_name", property = "realName"),
            @Result(property="role",column="role_id",one=@One(select="com.play001.cloud.cms.mapper.RoleMapper.findById"))
    })
    @Select("select id, username, password, real_name, role_id  from cms_admin where username = #{username} limit 1")
    Admin findByUsername(String username);

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "real_name", property = "realName"),
            @Result(column = "create_time", property = "createTime"),
            @Result(property="role",column="role_id",one=@One(select="com.play001.cloud.cms.mapper.RoleMapper.findById"))
    })
    @Select("select id, username, real_name, age, email, telephone, status, create_time, role_id  from cms_admin limit #{offset}, #{limit}")
    List<Admin> Pagination(@Param("offset") Integer offset, @Param("limit") Integer limit);


    /**
     * 获取用户操作权限
     */
    @Select("select m.menu_code as menuCode, am.flag from cms_admin_menu am, cms_menu m where m.id = am.menu_id and am.admin_id = #{id}")
    List<Map<String, Object>> getMenuPermission(Integer id);

    @Select("insert into cms_admin(username, role_id, password, real_name, email, status, telephone, sex, creator_id) value(" +
            " #{username},#{role.id}, #{password}, #{realName}, #{email}, #{status}, #{telephone}, #{sex}, #{creator.id})")
    void add(Admin admin);

    //删除用户基本信息
    @Delete("delete from cms_admin where id = #{id}")
    void delete(Integer id);
    //删除权限表
    @Delete("delete from cms_admin_menu where admin_id = #{id}")
    void deletePermission(Integer id);

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "real_name", property = "realName"),
            @Result(property="role",column="role_id",one=@One(select="com.play001.cloud.cms.mapper.RoleMapper.findById"))
    })
    @Select("select id, username, password, real_name, email, telephone, age, sex, status, role_id  from cms_admin where id = #{id} limit 1")
    Admin findById(Integer id);


}
