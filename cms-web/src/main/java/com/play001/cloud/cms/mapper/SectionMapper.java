package com.play001.cloud.cms.mapper;

import com.play001.cloud.support.entity.Section;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SectionMapper {

    @Select("select id, name, sort, status from os_section where id = #{id} limit 1")
    Section findById(Integer id);

    @Select("select id, name, sort, status from os_section where section_category_id = #{categoryId}")
    List<Section> listByCategory(Integer categoryId);
}
