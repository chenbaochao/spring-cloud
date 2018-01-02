package com.play001.cloud.commonapi.controller;

import com.netflix.discovery.converters.Auto;
import com.play001.cloud.common.entity.Advert;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.commonapi.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 各种广告
 */
@RestController
@RequestMapping(value = "/advert")
public class AdvertEndPoint {


    @Autowired
    private AdvertService advertService;

    /**
     * 获取首页图片轮播数据
     * @return
     */
    @RequestMapping(value = "/getSliderAdvert", method = RequestMethod.GET)
    public Response<List<Advert>> getSliderAdvert(){
        return new Response<>(advertService.getSliderAdvert());
    }
}
