package com.play001.cloud.cms.controller.rest;

import com.play001.cloud.cms.service.ProductService;
import com.play001.cloud.support.entity.Product;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/product")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    /**
     * 添加
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Integer> create(@Valid @RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        return productService.create(product);
    }

    /**
     * 获取列表
     * @param offset 开始位置
     * @param limit 数据条数
     * @param sort 排序条件
     * @param order 排序方式 desc/asc
     * @param categoryId 目录ID
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Map<String, Object> getList(Long offset, Integer limit, String sort, String order, Integer categoryId){
        return productService.getList(offset, limit, sort, order, categoryId);
    }
    /**
     * 修改产品
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Integer> update(@Valid @RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        return productService.update(product);
    }
}
