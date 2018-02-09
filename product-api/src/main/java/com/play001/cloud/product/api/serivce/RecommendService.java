package com.play001.cloud.product.api.serivce;

import com.play001.cloud.support.entity.product.Product;
import com.play001.cloud.product.api.mapper.RecommendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.play001.cloud.support.enums.RecommendEnum.STAR_ID;
@Service
public class RecommendService {

    /**
     * Recommend_id
     * 1 明星产品
     */
    @Autowired
    private RecommendMapper recommendMapper;

    public List<Product> getStarProduct(){
        return recommendMapper.findRecommendById(STAR_ID.value());
    }
}
