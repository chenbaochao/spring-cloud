package com.play001.cloud.web.service;

import com.play001.cloud.common.entity.Pagination;
import com.play001.cloud.common.entity.Product;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.web.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface ProductService {

    @RequestMapping(value = "/product/getDetail", method = RequestMethod.GET)
    Response<Product> getProduct(@RequestParam("id") Long id);

    @RequestMapping(value = "/product/search", method = RequestMethod.GET)
    Response<Pagination<Product>> search(@RequestParam("keyword")String keyword,
                                         @RequestParam("start")Long start,
                                         @RequestParam("quantity") Integer quantity);
}
