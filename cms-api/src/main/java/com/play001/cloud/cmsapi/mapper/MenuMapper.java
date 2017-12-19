package com.play001.cloud.cmsapi.mapper;


import com.play001.cloud.common.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {


    @Select(" select p_menu.id p_id, p_menu.menu_name p_name, p_menu.href p_href, " +
            " p_menu.icon p_icon, child_menu.id c_id, child_menu.menu_name c_name, child_menu.href c_href,  child_menu.icon c_icon " +
            " from cms_menu p_menu LEFT JOIN cms_menu child_menu on child_menu.parent_id = p_menu.id " +
            " WHERE p_menu.parent_id = 1 order by p_menu.sort")
    @ResultMap("com.play001.cloud.cmsapi.mapper.menuResult")
    List<Menu> getMenus();

}
