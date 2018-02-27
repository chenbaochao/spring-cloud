package com.play001.cloud.os.mapper;

import com.play001.cloud.support.entity.Category;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 分类,目录
 */
@FeignClient(name = "ZUUL", fallback = CategoryMapper.CategoryFallback.class)
public interface CategoryMapper {

    @RequestMapping(value = "/common/category/findAll", method = RequestMethod.GET)
    ResponseEntity<List<Category>> findAll();

    @RequestMapping(value = "/common/category/findById", method = RequestMethod.GET)
    ResponseEntity<Category> findById(@RequestParam("categoryId") Integer categoryId);

    @Component
    static class CategoryFallback implements CategoryMapper{
        @Override
        public ResponseEntity<List<Category>> findAll() {
            return new  ResponseEntity<List<Category>>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<Category> findById(Integer categoryId) {
            return new ResponseEntity<Category>().setErrMsg("网络繁忙");
        }
    }
}
