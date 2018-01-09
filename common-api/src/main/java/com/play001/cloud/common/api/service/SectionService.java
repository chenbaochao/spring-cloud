package com.play001.cloud.common.api.service;

import com.play001.cloud.common.api.mapper.SectionMappr;
import com.play001.cloud.common.entity.Section;
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
