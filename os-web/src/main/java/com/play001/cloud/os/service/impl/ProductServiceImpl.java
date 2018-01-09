package com.play001.cloud.os.service.impl;


import com.play001.cloud.common.entity.*;
import com.play001.cloud.os.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl {

    @Autowired
    private ProductService productService;

    /**
     * 获取商品详情
     * @param id 商品Id
     */
    public Product getDetial(Long id) throws IException {
        Response<Product> response = productService.getProduct(id);
        if(response.getStatus().equals(Response.ERROR)) throw new IException(response.getErrMsg());
        return response.getMessage();
    }

    /**
     * 搜索分页
     * @param keyword 关键字
     * @param pageNo 页面编号,从1开始
     */
    public Pagination<Product> getPaginationBySearch(Integer pageNo, String keyword) throws IException {
        //一页显示二十个
        final Integer pageSize = 20;
        //计算开始位置
        Long start = Long.valueOf((pageNo-1)*pageSize);
        Response<Pagination<Product>> response =  productService.search(keyword, start, pageSize);
        if(Response.ERROR.equals(response.getStatus())){
            throw new IException(response.getErrMsg());
        }
        Pagination<Product> pagination = response.getMessage();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        //计算总共有多少页
        pagination.setPageQuantity((pagination.getDataQuantity().intValue()+pageSize-1)/pageSize.intValue());
        return pagination;
    }

    /**
     * 分类列出产品
     * @param categoryId 产品目录ID
     * @param sort 排序方式,1.新品,2.销量,3.价格up,4.价格down
     * @param pageNo
     * @return 分页数据
     */
    public Pagination<Product> getPaginationByCategoryId(Integer categoryId, Integer sort, Integer pageNo) throws IException {
        //一页显示二十个
        final Integer pageSize = 20;
        Long start = Long.valueOf((pageNo-1)*pageSize);
        Response<Pagination<Product>> response =  productService.listByCategoryId(categoryId, sort, start, pageSize);
        if(Response.ERROR.equals(response.getStatus())){
            throw new IException(response.getErrMsg());
        }
        Pagination<Product> pagination = response.getMessage();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        //计算总共有多少页
        pagination.setPageQuantity((pagination.getDataQuantity().intValue()+pageSize-1)/pageSize.intValue());
        return pagination;
    }
    /**
     * 首页明星产品
     */
    public List<Product> getStarProduct() throws IException {
        Response<List<Product>> response = productService.getStarProduct();
        if(Response.ERROR.equals(response.getStatus())) throw new IException(response.getErrMsg());
        return response.getMessage();
    }
}
