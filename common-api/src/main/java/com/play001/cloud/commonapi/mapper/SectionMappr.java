package com.play001.cloud.commonapi.mapper;

import com.play001.cloud.common.entity.Section;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SectionMappr {


    /**
     * 获取显示在首页的section
     */
    List<Section> getIndexSections();
}
