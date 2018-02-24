package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.SectionMapper;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionMapper sectionMapper;


    public Section findById(Integer id) throws IException {
        if(id == null){
            throw new IException("参数错误");
        }
        Section section = sectionMapper.findById(id);
        if(section == null){
            throw new IException("数据不存在");
        }
        return section;
    }
    public List<Section> listByCategory(Integer categoryId){
        return sectionMapper.listByCategory(categoryId);
    }

}
