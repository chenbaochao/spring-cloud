package com.play001.cloud.web.service;

import com.play001.cloud.web.entity.Product;
import com.play001.cloud.web.response.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ZUUL")
public interface ProductService {

    @RequestMapping(value = "/product/getDetail", method = RequestMethod.GET)
    Response<Product> getProduct(@RequestParam("id") Long id);
}
