package com.play001.cloud.cms.mapper;

import com.play001.cloud.common.entity.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {

    @Select("select id, name, status, sort, show_in_top as showInTop from os_category order by id")
    List<Category> findAll();

    /**
     * 设置状态
     */
    @Update("update os_category set status = #{status} where id = #{id}")
    int setStatus(@Param("id")Integer id, @Param("status") Byte status);
    /**
     * 删除
     */
    @Delete("delete from os_category where id = #{id}")
    int delete(Integer id);

    /**
     * findById
     */
    @Select("select id, name, status, sort, show_in_top as showInTop from os_category where id = #{id} limit 1")
    Category findById(Integer id);

    @Insert("insert into os_category(name, status, sort, show_in_top) value(#{name}, #{status}, #{sort}, #{showInTop} )")
    int create(Category category);

    @Update("update os_category set name = #{name}, sort = #{sort}, status = #{status}, show_in_top = #{showInTop} where id = #{id}")
    int update(Category category);
}
