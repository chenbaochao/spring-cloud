package com.play001.cloud.cms.mapper.admin;

import com.play001.cloud.cms.entity.MenuPermission;
import com.play001.cloud.cms.entity.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户组
 */
@Mapper
@Repository
public interface RoleMapper {

    /**
     * 检索所有用户组,排除掉root
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "permissions", column = "id", one=@One(select = "findPermissions"))
    })
    @Select("select id, name, status, remarks from cms_role where id != 1")
    List<Role> finAll();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "permissions", column = "id", one=@One(select = "findPermissions"))
    })
    @Select("select id, name, status, remarks from cms_role where id = #{id} limit 1")
    Role findById(Integer id);

    //新增
    @Insert("insert into cms_role(name, status, remarks) value(#{name}, #{status}, #{remarks})")
    @Options(useGeneratedKeys = true)
    Integer add(Role role);

    //添加权限
    @Insert("insert into cms_role_menu(role_id, menu_id, flag) value(#{roleId}, #{menuId}, #{flag})")
    void addPermission(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId, @Param("flag") Byte flag);

    //update
    @Update("update cms_role set name = #{name}, status = #{status}, remarks = #{remarks} where id = #{id}")
    void update(Role role);

    //update permission
    @Update("update cms_role_menu set flag = #{flag} where role_id = #{roleId} and menu_id = #{menuId}")
    void updatePermission(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId, @Param("flag") Byte flag);


    //菜单权限信息
    @Results({
            @Result(column = "id", property = "menu.id"),
            @Result(column = "menu_type", property = "menu.type"),
            @Result(column = "menu_code", property = "menu.code"),
    })
    @Select("select m.id, m.menu_type,  m.menu_code, rm.flag from cms_role_menu rm, cms_menu m where rm.role_id = #{roleId} and m.id = rm.menu_id")
    List<MenuPermission> findPermissions(Integer roleId);

    //删除
    @Delete("delete from cms_role where id = #{id}")
    void delete(Integer id);

    //删除
    @Delete("delete from cms_role_menu where role_id = #{roleId}")
    void deletePermissions(Integer roleId);
}
