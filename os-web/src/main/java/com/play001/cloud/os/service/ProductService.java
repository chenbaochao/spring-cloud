package com.play001.cloud.os.service;


import com.play001.cloud.support.entity.*;
import com.play001.cloud.os.mapper.ProductMapper;
import com.play001.cloud.support.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 获取商品详情
     * @param id 商品Id
     */
    public Product getDetail(Long id) throws IException {
        ResponseEntity<Product> responseEntity = productMapper.getProduct(id);
        if(responseEntity.getStatus().equals(ResponseEntity.ERROR)) throw new IException(responseEntity.getErrMsg());
        return responseEntity.getMessage();
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
        Long start = (long)(pageNo-1)*pageSize;
        ResponseEntity<Pagination<Product>> responseEntity =  productMapper.search(keyword, start, pageSize);
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())){
            throw new IException(responseEntity.getErrMsg());
        }
        Pagination<Product> pagination = responseEntity.getMessage();
        pagination.setPageNo(pageNo);
        //计算总共有多少页
        pagination.setTotalPage((pagination.getTotalData().intValue()+pageSize-1)/pageSize);
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
        Long start = (long)((pageNo-1)*pageSize);
        ResponseEntity<Pagination<Product>> responseEntity =  productMapper.listByCategoryId(categoryId, sort, start, pageSize);
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())){
            throw new IException(responseEntity.getErrMsg());
        }
        Pagination<Product> pagination = responseEntity.getMessage();
        pagination.setPageNo(pageNo);
        //计算总共有多少页
        pagination.setTotalPage((pagination.getTotalData().intValue()+pageSize-1)/pageSize);
        return pagination;
    }
    /**
     * 首页明星产品
     */
    public List<Product> getStarProduct() throws IException {
        ResponseEntity<List<Product>> responseEntity = productMapper.getStarProduct();
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())) throw new IException(responseEntity.getErrMsg());
        return responseEntity.getMessage();
    }
}
