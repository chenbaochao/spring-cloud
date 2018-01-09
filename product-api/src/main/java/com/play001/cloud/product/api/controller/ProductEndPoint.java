package com.play001.cloud.product.api.controller;


import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Pagination;
import com.play001.cloud.common.entity.Product;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.product.api.serivce.RecommendService;
import com.play001.cloud.product.api.serivce.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductEndPoint {

    @Autowired
    private ProductService productService;

    @Autowired
    private RecommendService recommendService;
    /**
     * 明星产品
     */
    @RequestMapping(value = "/getStarProduct", method = RequestMethod.GET)
    public Response<List<Product>> getStarProduct(){
        return new Response<>(recommendService.getStarProduct());
    }

    /**
     * 产品详细信息
     */
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
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Response<Pagination<Product>> search(String keyword, Long start, Integer quantity) throws Exception {
        if(keyword == null || "".equals(keyword)) throw new IException("参数错误");
        Response<Pagination<Product>> response =  new Response<>(Response.SUCCESS);
        response.setMessage(productService.search(keyword, start, quantity));
        return response;
    }

    /**
     * 根据分类列出商品
     * @param categoryId 分类ID
     * @param sort 排序方式
     * @param start 开始位置
     * @param quantity 数据条数
     */
    @RequestMapping(value = "/listByCategoryId", method = RequestMethod.GET)
    public Response<Pagination<Product>> listByCategoryId(Integer categoryId, Integer sort ,Long start, Integer quantity) throws Exception {
        if(categoryId == null || categoryId < 0) throw new IException("目录ID错误");
        if(start == null || start < 0) throw new IException("起始位置错误");
        if(quantity == null || quantity < 1) throw new IException("数量错误");
        Response<Pagination<Product>> response =  new Response<>(Response.SUCCESS);
        response.setMessage(productService.listByCategoryId(categoryId, sort,  start, quantity));
        return response;
    }

}
