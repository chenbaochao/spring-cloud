package com.play001.cloud.common.api.service;

import com.play001.cloud.common.api.mapper.SectionMapper;
import com.play001.cloud.common.entity.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionMapper sectionMapper;

    /**
     * 首页栏目
     */
    public List<Section> getIndexSections(){
        return sectionMapper.getIndexSections();
    }

    /**
     * 页面header快捷导航
     */
    public List<Section> getHeaderSections(){
        return sectionMapper.getHeaderSections();
    }
}
