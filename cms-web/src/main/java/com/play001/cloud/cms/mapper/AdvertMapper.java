package com.play001.cloud.cms.mapper;

import com.play001.cloud.support.entity.Advert;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdvertMapper {

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "section_id",property = "section", one = @One(select = "com.play001.cloud.cms.mapper.SectionMapper.findById")),
            @Result(column = "advert_category_id",property = "advertCategory", one = @One(select = "com.play001.cloud.cms.mapper.AdvertCategoryMapper.findById"))
    })
    @Select("select id, section_id, advert_category_id, title, sort, href, show_pic as showPic, status from os_advert_detail where advert_category_id = #{advertCategoryId} order by sort")
    List<Advert> listByCategoryId(Integer categoryId);

    @Insert("insert into os_advert_detail(section_id, advert_category_id, title, sort, href, show_pic, status)" +
            "value(#{section.id}, #{advertCategory.id}, #{title}, #{sort}, #{href}, #{showPic}, #{status})")
    void add(Advert advert);

    @Update("update os_advert_detail set section_id = #{section.id},advert_category_id = #{advertCategory.id}, title=#{title}, sort=#{sort}, href=#{href}, show_pic=#{showPic}, status = #{status}" +
            " where id = #{id}")
    void update(Advert advert);

    @Delete("delete from os_advert_detail where id = #{id}")
    void delete(Integer id);

    @Update("update os_advert_detail set  status = #{status} where id = #{id}")
    void setStatus(@Param("id") Integer id, @Param("status")Byte status);

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "section_id",property = "section", one = @One(select = "com.play001.cloud.cms.mapper.SectionMapper.findById")),
            @Result(column = "advert_category_id",property = "advertCategory", one = @One(select = "com.play001.cloud.cms.mapper.AdvertCategoryMapper.findById"))
    })
    @Select("select id, section_id, advert_category_id, title, sort, href, show_pic as showPic, status from os_advert_detail where id = #{id} limit 1")
    Advert findById(Integer id);


}
