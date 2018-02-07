package com.play001.cloud.support.api.service;

import com.netflix.discovery.converters.Auto;
import com.play001.cloud.support.entity.Category;
import com.play001.cloud.support.api.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    private final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private CategoryMapper categoryMapper;

    public Category findById(Integer id){
        return categoryMapper.findById(id);
    }

    @SuppressWarnings("unchecked")
    public List<Category> findAll(){
        List<Category> categories = (List<Category>)redisTemplate.opsForValue().get("allCategory");
        if(categories == null){
            categories = categoryMapper.findAll();
        }
        return categories;
    }
    //缓存所有的category
    public void cachingAllCategory(){
        logger.info("缓存所有category------------");
        redisTemplate.delete("allCategory");
        List<Category> categories = categoryMapper.findAll();
        redisTemplate.opsForValue().set("allCategory", categories);
    }
}
