package com.play001.cloud.support.api.mapper;

import com.play001.cloud.support.api.mapper.fallback.DefaultFallbackFactory;
import com.play001.cloud.support.entity.Category;
import com.play001.cloud.support.entity.Product;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 微服务之间相互的调用
 */
@FeignClient(name = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface ProductMapper {

    @RequestMapping(value = "/product/getDetail", method = RequestMethod.GET)
    ResponseEntity<Product> findById(@RequestParam("id") Long id);

    @RequestMapping(value = "/product/getByCategory", method = RequestMethod.POST)
    ResponseEntity<List<Category>> getByCategory(@RequestBody List<Category> categories, @RequestParam("limit") Integer limit);
}
