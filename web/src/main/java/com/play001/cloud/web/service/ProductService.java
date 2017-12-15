package com.play001.cloud.web.service;

import com.play001.cloud.web.entity.Pagination;
import com.play001.cloud.web.entity.Product;
import com.play001.cloud.web.response.Response;
import com.play001.cloud.web.service.fallback.DefaultFallbackFactory;
import com.play001.cloud.web.service.fallback.ProductServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface ProductService {

    @RequestMapping(value = "/product/getDetail", method = RequestMethod.GET)
    Response<Product> getProduct(@RequestParam("id") Long id);

    @RequestMapping(value = "/product/search", method = RequestMethod.GET)
    Response<List<Product>> search(@RequestParam("keyword")String keyword, @RequestParam("pagenation")Pagination pagination);
}
