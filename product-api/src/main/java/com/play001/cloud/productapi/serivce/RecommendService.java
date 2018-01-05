package com.play001.cloud.productapi.serivce;

import com.play001.cloud.common.entity.Product;
import com.play001.cloud.productapi.mapper.RecommendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendService {

    /**
     * Recommend_id
     * 1 明星产品
     */
    @Autowired
    private RecommendMapper recommendMapper;

    public List<Product> getStarProduct(){
        return recommendMapper.findRecommendById(1);
    }
}
