package com.play001.cloud.cms.mapper.advert;

import com.play001.cloud.support.entity.AdvertCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdvertCategoryMapper {

    @Select("select id, name, description from os_advert_category")
    List<AdvertCategory> findAll();

    @Select("select id, name, description from os_advert_category where id = #{id} limit 1")
    AdvertCategory findById(Integer id);
}
