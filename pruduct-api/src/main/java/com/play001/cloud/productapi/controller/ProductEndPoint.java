package com.play001.cloud.productapi.controller;


import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Pagination;
import com.play001.cloud.common.entity.Product;
import com.play001.cloud.common.entity.Response;
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
    /**
     *
     * @param keyword 关键词
     * @param start 数据开始索引,从1开始
     * @param quantity 总共数据条数
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Response<Pagination<Product>> search(String keyword, Long start, Integer quantity) throws Exception {
        if(keyword == null || "".equals(keyword)) throw new IException("参数错误");
        Response<Pagination<Product>> response =  new Response<>(Response.SUCCESS);
        response.setMessage(productService.search(keyword, start, quantity));
        return response;
    }


}
