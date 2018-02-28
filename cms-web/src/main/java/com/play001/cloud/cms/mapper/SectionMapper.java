package com.play001.cloud.cms.mapper;

import com.play001.cloud.support.entity.Category;
import com.play001.cloud.support.entity.Section;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SectionMapper {

    @Select("select id, name, sort, status from os_section where id = #{id} limit 1")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "categories", many = @Many(select = "com.play001.cloud.cms.mapper.SectionDetailMapper.listBySectionId"))
    })
    Section findById(Integer id);

    @Select("select id, name, sort, status from os_section where section_category_id = #{categoryId} order by sort")
    List<Section> listByCategory(Integer categoryId);

    @Insert("insert into os_section(name, sort, status, section_category_id) value(#{name}, #{sort}, #{status}, #{sectionCategory.id})")
    @Options(useGeneratedKeys = true)
    void add(Section section);

    @Update("update os_section set name = #{name}, sort = #{sort}, status = #{status} where id = #{id}")
    void update(Section section);

    @Delete("delete from os_section where id = #{id}")
    void delete(Integer id);

    @Update("update os_section set status = #{status} where id = #{id}")
    void setStatus(@Param("id")Integer id, @Param("status")Byte status);
}
