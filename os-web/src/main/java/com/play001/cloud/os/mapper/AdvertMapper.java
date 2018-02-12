package com.play001.cloud.os.mapper;

import com.play001.cloud.support.entity.Advert;
import com.play001.cloud.support.entity.product.Product;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface AdvertMapper {

    /**
     * 获取首页轮播数据
     */
    @RequestMapping(value = "/common/advert/getSliderAdvert", method = RequestMethod.GET)
    ResponseEntity<List<Advert>> getSliderAdvert();

    /**
     * 获取首页轮播下方数据
     */
    @RequestMapping(value = "/common/advert/getUnderSliderAdvert", method = RequestMethod.GET)
    ResponseEntity<List<Advert>> getUnderSliderAdvert();

    /**
     * 获取首页明星产品
     */
    @RequestMapping(value = "/common/advert/getStarProduct", method = RequestMethod.GET)
    ResponseEntity<List<Product>> getStarProduct();

}
