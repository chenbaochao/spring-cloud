package com.play001.cloud.product.api.serivce;


import com.play001.cloud.common.entity.Pagination;
import com.play001.cloud.common.entity.Product;
import com.play001.cloud.product.api.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public Product findById(Long id){
        return productMapper.findById(id);
    }

    public Pagination<Product> search(String keyword, Long start, Integer quantity){
        Pagination<Product> pagination = new Pagination<>();
        pagination.setDataQuantity(productMapper.countSearch(keyword));//数据总条数
        List<Product> products =  productMapper.search(keyword, start, quantity);
        pagination.setData(products);
        return pagination;
    }

    /**
     * 分类列出产品
     * @param categoryId 产品目录ID
     * @param sort 排序方式,1.新品,2.销量,3.价格up,4.价格down
     * @param start 开始位置
     * @param quantity 取数据条数
     * @return
     */
    public Pagination<Product> listByCategoryId(Integer categoryId, Integer sort, Long start, Integer quantity){
        Pagination<Product> pagination = new Pagination<>();
        pagination.setDataQuantity(productMapper.countByCategory(categoryId));//数据总条数
        List<Product> products =  productMapper.listByCategoryId(categoryId, sort, start, quantity);
        pagination.setData(products);
        return pagination;
    }
}
