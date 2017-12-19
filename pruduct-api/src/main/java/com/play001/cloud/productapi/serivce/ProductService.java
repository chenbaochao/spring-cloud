package com.play001.cloud.productapi.serivce;


import com.play001.cloud.productapi.entity.Pagination;
import com.play001.cloud.productapi.entity.Product;
import com.play001.cloud.productapi.entity.Response;
import com.play001.cloud.productapi.mapper.ProductMapper;
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
        pagination.setDataQuantity(productMapper.count(keyword));//数据总条数
        List<Product> products =  productMapper.search(keyword, start, quantity);
        pagination.setData(products);
        return pagination;
    }
}
