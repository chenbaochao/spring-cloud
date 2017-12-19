package com.play001.cloud.commonapi.mapper;

import com.play001.cloud.common.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select id, name, sort, status, show_in_nav as showInNav from os_category where id = #{id} limit 1")
    Category findById(Integer id);

    @Select("select id, name, sort, status, show_in_nav as showInNav from os_category order by sort")
    List<Category> findAll();
}
