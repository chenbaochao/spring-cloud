package com.play001.cloud.productapi.controller;


import com.play001.cloud.productapi.entity.IException;
import com.play001.cloud.productapi.entity.Product;
import com.play001.cloud.productapi.entity.Response;
import com.play001.cloud.productapi.serivce.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductEndPoint {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/getDetail", method = RequestMethod.GET)
    public Response<Product> getDetail(Long id) throws Exception {
        if(id == null) throw new IException("参数错误");
        Response<Product> response =  new Response<>(Response.SUCCESS);
        response.setMessage(productService.findById(id));
        return response;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Response<List<Product>> search(String keyword) throws Exception {
        if(keyword == null || "".equals(keyword)) throw new IException("参数错误");
        Response<List<Product>> response =  new Response<>(Response.SUCCESS);
        response.setMessage(productService.search(keyword));
        return response;
    }


}
