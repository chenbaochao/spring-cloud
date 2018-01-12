package com.play001.cloud.cms.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 权限操作
 */
@Mapper
@Repository
public interface PermissionMapper {

    //删除指定用户的权限
    @Delete("delete from cms_admin_menu where admin_id = #{adminId}")
    void deletePermission(Integer adminId);

    /**
     * 获取用户操作权限
     */
    @Select("select m.menu_code as menuCode, am.flag from cms_admin_menu am, cms_menu m where m.id = am.menu_id and am.admin_id = #{adminId}")
    List<Map<String, Object>> getMenuPermission(Integer adminId);

    /**
     * 根据用户组添加权限
     */
    @Insert("insert into cms_admin_menu(admin_id, menu_id, flag) " +
            "select #{adminId},menu_id, flag from cms_role_menu where role_id = #{roleId} ")
    void add(@Param("adminId") Integer adminId, @Param("roleId") Integer roleId);
}
