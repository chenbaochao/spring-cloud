package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.section.SectionCategoryMapper;
import com.play001.cloud.support.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionCategoryService {

    @Autowired
    private SectionCategoryMapper sectionCategoryMapper;

    //分类列表
    public List<SectionCategory> getCategoryList(){
        return sectionCategoryMapper.findAll();
    }
    //分类
    public SectionCategory findCategoryById(Integer categoryId) throws IException {
        if(categoryId == null){
            throw new IException("栏目分类不存在");
        }
        return sectionCategoryMapper.findById(categoryId);
    }
}
