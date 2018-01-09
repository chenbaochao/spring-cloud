package com.play001.cloud.common.api.controller;

import com.play001.cloud.common.api.service.AdvertService;
import com.play001.cloud.common.entity.Advert;
import com.play001.cloud.common.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 各种广告,推荐
 */
@RestController
@RequestMapping(value = "/advert")
public class AdvertEndPoint {


    @Autowired
    private AdvertService advertService;

    /**
     * 获取首页图片轮播数据
     */
    @RequestMapping(value = "/getSliderAdvert", method = RequestMethod.GET)
    public Response<List<Advert>> getSliderAdvert(){
        return new Response<>(advertService.getSliderAdvert());
    }

    /**
     * 获取首页图片轮播下面的advert
     */
    @RequestMapping(value = "/getUnderSliderAdvert", method = RequestMethod.GET)
    public Response<List<Advert>> getUnderSliderAdvert(){
        return new Response<>(advertService.getUnderSliderAdvert());
    }
    /**
     * 获取首页明星产品
     */
    @RequestMapping(value = "/getStartAdvert", method = RequestMethod.GET)
    public Response<List<Advert>> getStartAdvert(){
        return new Response<>(advertService.getStartAdvert());
    }


}
