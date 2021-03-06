package com.play001.cloud.support.api.mapper;

import com.play001.cloud.support.entity.Advert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdvertMapper {


    /**
     * 获取多条广告
     * @param advertCategoryId 广告类型
     * @param number 获取数量
     */
    @Select("select id, title, sort, href, show_pic as showPic from os_advert_detail where advert_category_id = #{advertCategoryId} and status = 1 order by sort limit #{number}")
    List<Advert> getAdvertsByCategoryId(@Param("advertCategoryId") Integer advertCategoryId,
                            @Param("number") Integer number);

    /**
     * 获取多条广告
     * @param sectionId 首页栏目ID
     * @param number 获取数量
     */
    @Select("select id, title, sort, href, show_pic as showPic from os_advert_detail where section_id = #{sectionId} and status = 1 order by sort limit #{number}")
    List<Advert> getAdvertsBySectionId(@Param("sectionId") Integer sectionId,
                                        @Param("number") Integer number);


}
