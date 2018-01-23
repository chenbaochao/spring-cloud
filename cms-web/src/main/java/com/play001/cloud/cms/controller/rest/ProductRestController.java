package com.play001.cloud.cms.controller.rest;

import com.play001.cloud.cms.service.ProductService;
import com.play001.cloud.common.entity.Product;
import com.play001.cloud.common.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response<Integer> add(@RequestBody Product product){
        return productService.add(product);
    }
}
