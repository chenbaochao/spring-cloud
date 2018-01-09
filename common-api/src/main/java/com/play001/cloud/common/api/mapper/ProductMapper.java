package com.play001.cloud.common.api.mapper;

import com.play001.cloud.common.api.mapper.fallback.DefaultFallbackFactory;
import com.play001.cloud.common.entity.Product;
import com.play001.cloud.common.entity.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微服务之间相互的调用
 */
@FeignClient(name = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface ProductMapper {

    @RequestMapping(value = "/product/getDetail", method = RequestMethod.GET)
    Response<Product> findById(@RequestParam("id") Long id);
}
