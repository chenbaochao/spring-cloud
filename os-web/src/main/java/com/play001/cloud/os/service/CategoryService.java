package com.play001.cloud.os.service;

import com.play001.cloud.common.entity.Category;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 分类,目录
 */
@FeignClient(name = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface CategoryService {

    @RequestMapping(value = "/common/category/findAll", method = RequestMethod.GET)
    Response<List<Category>> findAll();

    @RequestMapping(value = "/common/category/findById", method = RequestMethod.GET)
    Response<Category> findById(@RequestParam("categoryId") Integer categoryId);
}
