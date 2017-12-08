package com.play001.cloud.productapi.serivce;

import com.netflix.discovery.converters.Auto;
import com.play001.cloud.productapi.entity.Product;
import com.play001.cloud.productapi.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public Product findById(Long id){
        return productMapper.findById(id);
    }
}
