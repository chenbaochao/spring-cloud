package com.play001.cloud.web.service;

import com.play001.cloud.common.entity.Category;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.web.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 分类,目录
 */
@FeignClient(name = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface CategoryService {

    @RequestMapping(value = "/common/findAll", method = RequestMethod.GET)
    Response<List<Category>> findAll();
}
