package com.play001.cloud.os.mapper;

import com.play001.cloud.support.entity.Advert;
import com.play001.cloud.support.entity.Product;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "ZUUL", fallback = AdvertMapper.AdvertFallback.class)
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
    @Component
    static class AdvertFallback implements AdvertMapper{
        @Override
        public ResponseEntity<List<Advert>> getSliderAdvert() {
            return new ResponseEntity<List<Advert>>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<List<Advert>> getUnderSliderAdvert() {
            return new ResponseEntity<List<Advert>>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<List<Product>> getStarProduct() {
            return new ResponseEntity<List<Product>>().setErrMsg("网络繁忙");
        }
    }

}
