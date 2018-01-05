package com.play001.cloud.commonapi.service;

import com.play001.cloud.common.entity.Section;
import com.play001.cloud.commonapi.mapper.SectionMappr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionMappr sectionMappr;

    public List<Section> getIndexSections(){
        return sectionMappr.getIndexSections();
    }
}
