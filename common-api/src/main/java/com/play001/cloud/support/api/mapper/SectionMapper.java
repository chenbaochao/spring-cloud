package com.play001.cloud.support.api.mapper;

import com.play001.cloud.support.entity.Section;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SectionMapper {


    /**
     * 获取显示在首页的section
     */
    List<Section> getIndexSections();

    /**
     * 获取header中的导航section
     */

    List<Section> getHeaderSections();
}
