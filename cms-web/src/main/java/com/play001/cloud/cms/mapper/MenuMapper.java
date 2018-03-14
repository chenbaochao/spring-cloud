package com.play001.cloud.cms.mapper;


import com.play001.cloud.support.entity.Menu;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper {


    @Select("select id, menu_name, href, icon, menu_code, remarks, #{roleId} as roleId from cms_menu cm where parent_id = #{parentId} and id  = " +
            "(select menu_id from cms_role_menu crm where role_id = #{roleId} and crm.menu_id = cm.id limit 1)")
    //@ResultMap("com.play001.cloud.cms.mapper.MenuMapper.roleMenuResult")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "menu_name"),
            @Result(property = "childMenus", column = "{roleId=roleId, parentId = id}", many = @Many(select = "getMenusByRoleId"))
    })
    List<Menu> getMenusByRoleId(@Param("roleId") Integer roleId, @Param("parentId") Integer parentId);

    /**
     * 递归遍历菜单.....
     */
    @Select("select id, menu_name, href, icon, menu_code, remarks from cms_menu cm where parent_id = #{parentId}")
    //@ResultMap("com.play001.cloud.cms.mapper.MenuMapper.allMenuResult")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "menu_name"),
            @Result(property = "childMenus", column = "id", many = @Many(select = "findAll"))
    })
    List<Menu> findAll(Integer parentId);
}
