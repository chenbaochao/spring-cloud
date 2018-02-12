package com.play001.cloud.os.service;

import com.play001.cloud.support.entity.Advert;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.product.Product;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.os.mapper.AdvertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertService {
    @Autowired
    private AdvertMapper advertMapper;


    /**
     * 首页轮播
     */
    public List<Advert> getSliderAdvert() throws IException {
        ResponseEntity<List<Advert>> responseEntity = advertMapper.getSliderAdvert();
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())) throw new IException(responseEntity.getErrMsg());
        return responseEntity.getMessage();
    }

    /**
     * 首页轮播下方广告
     */
    public List<Advert> getUnderSliderAdvert() throws IException {
        ResponseEntity<List<Advert>> responseEntity = advertMapper.getUnderSliderAdvert();
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())) throw new IException(responseEntity.getErrMsg());
        return responseEntity.getMessage();
    }


    public List<Product> getStarProduct() throws IException {
        ResponseEntity<List<Product>> responseEntity = advertMapper.getStarProduct();
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())) throw new IException(responseEntity.getErrMsg());
        return responseEntity.getMessage();
    }
}
