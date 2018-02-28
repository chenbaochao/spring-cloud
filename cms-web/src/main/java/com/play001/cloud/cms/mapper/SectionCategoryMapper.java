package com.play001.cloud.cms.mapper;

import com.play001.cloud.support.entity.Section;
import com.play001.cloud.support.entity.user.SectionCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SectionCategoryMapper {

    @Select("select id, name from os_section_category")
    List<SectionCategory> findAll();


    @Select("select id, name from os_section_category where id = #{id} limit 1")
    SectionCategory findById(Integer id);
}
