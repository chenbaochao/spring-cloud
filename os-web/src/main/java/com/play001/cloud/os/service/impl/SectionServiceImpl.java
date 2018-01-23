package com.play001.cloud.os.service.impl;

import com.play001.cloud.common.entity.*;
import com.play001.cloud.os.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectionServiceImpl {

    @Autowired
    private SectionService sectionService;

    /**
     * 首页的产品显示
     */
    public List<Section> getIndexSections() throws IException {
        Response<List<Section>> response = sectionService.getIndexSections();
        if(Response.ERROR.equals(response.getStatus())) throw new IException(response.getErrMsg());
        return response.getMessage();
    }

    /**
     * header导航
     */
    public List<Category> getHeaderSections() throws IException {
        Response<List<Section>> response = sectionService.getHeaderSections();
        if(Response.ERROR.equals(response.getStatus())) throw new IException(response.getErrMsg());
        List<Section> sections = response.getMessage();
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
