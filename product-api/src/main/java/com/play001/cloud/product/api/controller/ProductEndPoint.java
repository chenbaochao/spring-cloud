package com.play001.cloud.product.api.controller;


import com.play001.cloud.support.entity.*;
import com.play001.cloud.product.api.serivce.RecommendService;
import com.play001.cloud.product.api.serivce.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Product>> getStarProduct(){
        return new ResponseEntity<>(recommendService.getStarProduct());
    }

    /**
     * 产品详细信息
     */
    @RequestMapping(value = "/getDetail", method = RequestMethod.GET)
    public ResponseEntity<Product> getDetail(Long id) throws Exception {

        return productService.findById(id);
    }
    /**
     *
     * @param keyword 关键词
     * @param start 数据开始索引,从1开始
     * @param quantity 总共数据条数
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<Pagination<Product>> search(String keyword, Long start, Integer quantity) throws Exception {
        if(keyword == null || "".equals(keyword)) throw new IException("参数错误");
        ResponseEntity<Pagination<Product>> responseEntity =  new ResponseEntity<>(ResponseEntity.SUCCESS);
        responseEntity.setMessage(productService.search(keyword, start, quantity));
        return responseEntity;
    }

    /**
     * 根据分类列出商品
     * @param categoryId 分类ID
     * @param sort 排序方式
     * @param start 开始位置
     * @param quantity 数据条数
     */
    @RequestMapping(value = "/listByCategoryId", method = RequestMethod.GET)
    public ResponseEntity<Pagination<Product>> listByCategoryId(Integer categoryId, Integer sort , Long start, Integer quantity) throws Exception {
        if(categoryId == null || categoryId < 0) throw new IException("目录ID错误");
        if(start == null || start < 0) throw new IException("起始位置错误");
        if(quantity == null || quantity < 1) throw new IException("数量错误");
        ResponseEntity<Pagination<Product>> responseEntity =  new ResponseEntity<>(ResponseEntity.SUCCESS);
        responseEntity.setMessage(productService.listByCategoryId(categoryId, sort,  start, quantity));
        return responseEntity;
    }

    /**
     * 根据分类查找商品
     * @param categories 分类信息
     * @param limit 排序方式 每个分类查询产品最大数据条数
     */
    @RequestMapping(value = "/getByCategory", method = RequestMethod.POST)
    public ResponseEntity<List<Category>> getByCategory(@RequestBody List<Category> categories, @RequestParam("limit") Integer limit) throws Exception {
        return productService.findByCategory(categories, limit);

    }
}
