package com.play001.cloud.cms.mapper;

import com.play001.cloud.support.entity.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SectionDetailMapper {

    /**
     *
     * @param sectionId 栏目Id
     * @param categoryId 产品分类Id不是栏目分类Id
     */
    @Delete("delete from os_section_detail where section_id = #{sectionId} and category_id = #{categoryId}")
    void deleteOne(@Param("sectionId") Integer sectionId, @Param("categoryId") Integer categoryId);

    @Delete("delete from os_section_detail where section_id = #{sectionId} ")
    void deleteBySectionId(Integer sectionId);

    @Select("select id from os_section_detail where section_id = #{sectionId} and category_id = #{categoryId}")
    Integer count(@Param("sectionId") Integer sectionId, @Param("categoryId") Integer categoryId);

    @Insert("insert into os_section_detail(section_id, category_id) value(#{sectionId},#{categoryId})")
    void insert(@Param("sectionId") Integer sectionId, @Param("categoryId") Integer categoryId);

    //通过section查找category
    @Select("select oc.id, oc.name, oc.status, oc.sort from os_category oc, os_section_detail osd where osd.section_id = #{sectionId} and oc.id = osd.category_id")
    List<Category> listBySectionId(Integer sectionId);
}
