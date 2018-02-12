package com.play001.cloud.os.service;

import com.play001.cloud.support.entity.*;
import com.play001.cloud.os.mapper.SectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionMapper sectionService;

    /**
     * 首页的产品显示
     */
    public List<Section> getIndexSections() throws IException {
        ResponseEntity<List<Section>> responseEntity = sectionService.getIndexSections();
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())) throw new IException(responseEntity.getErrMsg());
        return responseEntity.getMessage();
    }

    /**
     * header导航
     */
    public List<Category> getHeaderSections() throws IException {
        ResponseEntity<List<Section>> responseEntity = sectionService.getHeaderSections();
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())) throw new IException(responseEntity.getErrMsg());
        List<Section> sections = responseEntity.getMessage();
        /*
            将section转化为category,header导航的显示需要判断一个分类下有多少个数据,section无法办到
            转化规则一个section对应一个category, 将对应section下的category下的产品移动到新category下
         */
        List<Category> categories = new ArrayList<>(sections.size());
        for(Section section : sections){
            Category category = new Category();
            category.setName(section.getName());
            for(Category c1 : section.getCategories()){
                category.setProducts(c1.getProducts());
            }
            categories.add(category);
        }
        return categories;
    }
}
