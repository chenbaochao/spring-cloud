package com.play001.cloud.web.service;

import com.play001.cloud.common.entity.Advert;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.web.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface AdvertService {

    /**
     * 获取首页轮播数据
     */
    @RequestMapping(value = "/common/advert/getSliderAdvert", method = RequestMethod.GET)
    Response<List<Advert>> getSliderAdvert();

    /**
     * 获取首页轮播下方数据
     */
    @RequestMapping(value = "/common/advert/getUnderSliderAdvert", method = RequestMethod.GET)
    Response<List<Advert>> getUnderSliderAdvert();

    /**
     * 获取首页明星产品
     */
    @RequestMapping(value = "/common/advert/getStartAdvert", method = RequestMethod.GET)
    Response<List<Advert>> getStartAdvert();

}
