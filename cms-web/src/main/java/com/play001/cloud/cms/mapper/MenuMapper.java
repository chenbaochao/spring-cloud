package com.play001.cloud.cms.mapper;


import com.play001.cloud.common.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper {


    @Select("select id, menu_name, href, icon, menu_code, remarks, #{adminId} as adminId from cms_menu cm where parent_id = #{parentId} and id  = " +
            "(select menu_id from cms_admin_menu cam where admin_id = ${adminId} and cam.menu_id = cm.id limit 1)")
    @ResultMap("com.play001.cloud.cms.mapper.MenuMapper.adminMenuResult")
    List<Menu> getAdminMenus(@Param("adminId") Integer adminId, @Param("parentId") Integer parentId);

    @Select("select id, menu_name, href, icon, menu_code, remarks from cms_menu cm where parent_id = #{parentId}")
    @ResultMap("com.play001.cloud.cms.mapper.MenuMapper.allMenuResult")
    List<Menu> getAllMenus(Integer parentId);
}
