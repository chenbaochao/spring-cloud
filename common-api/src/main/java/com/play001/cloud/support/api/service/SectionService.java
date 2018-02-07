package com.play001.cloud.support.api.service;

import com.play001.cloud.support.api.mapper.ProductMapper;
import com.play001.cloud.support.api.mapper.SectionMapper;
import com.play001.cloud.support.entity.Category;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 首页栏目
     */
    public ResponseEntity<List<Section>> getIndexSections(){
        ResponseEntity<List<Section>> responseEntity = new ResponseEntity<>();
        List<Section> sections = sectionMapper.getIndexSections();
        //请求product-api,查找产品
        for(Section section:sections){
            List<Category> categories = section.getCategories();
            ResponseEntity<List<Category>> result = productMapper.getByCategory(categories, 18);
            if(result.getStatus().equals(ResponseEntity.ERROR)){
                return responseEntity.setErrMsg("查找产品失败");
            }
            section.setCategories(result.getMessage());
        }
        responseEntity.setMessage(sections);
        return responseEntity;
    }

    /**
     * 页面header快捷导航
     */
    public ResponseEntity<List<Section>> getHeaderSections(){
        ResponseEntity<List<Section>> responseEntity = new ResponseEntity<>();
        List<Section> sections = sectionMapper.getHeaderSections();
        //请求product-api,查找产品
        for(Section section:sections){
            List<Category> categories = section.getCategories();
            ResponseEntity<List<Category>> result = productMapper.getByCategory(categories, 18);
            if(result.getStatus().equals(ResponseEntity.ERROR)){
                return responseEntity.setErrMsg("查找产品失败");
            }
            section.setCategories(result.getMessage());
        }
        responseEntity.setMessage(sections);
        return responseEntity;
    }
}
